package com.inf.system.utiles.page;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.PropertyException;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.apache.log4j.Logger;

/**
 * mybatis拦截器,用来mybatis的调用 拦截的类对象为StatementHandler
 * 拦截该类的prepare，指明要拦截的方法参数集合为Connection 当前拦截类只支持mysql和oracle的分页
 * **/
@SuppressWarnings("unused")
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PagePlugin implements Interceptor {
	
	private Logger log = Logger.getLogger(PagePlugin.class);

	// 该参数指明当前数据库用的是什么,
	private static String dialect = "";

	// 指明要拦截的方法名称
	private static String pageSqlId = "";
	
	
	public Object intercept(Invocation ivk) throws Throwable {
		if (ivk.getTarget() instanceof RoutingStatementHandler) {
			
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
			
			BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler, "delegate");
			
			MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate, "mappedStatement");

			// 获取方法名称,获取匹配的方法做分页操作
			if (mappedStatement.getId().matches(pageSqlId)) {
				
				BoundSql boundSql = delegate.getBoundSql();
				
				Object parameterObject = boundSql.getParameterObject();
				
				if (parameterObject == null) {
					throw new NullPointerException("parameterObject error");
				} else {
					// 获取数据库连接
					Connection connection = (Connection) ivk.getArgs()[0];

					// 分页的关键方法
					String preSql = boundSql.getSql();

					int count = getResultSetCount(mappedStatement, boundSql,
							parameterObject, connection, preSql);
					PageInfo page = getRefreshPageInfo(parameterObject, count);
					
					String pageSql = generatePageSql(preSql, page);
					//log.info("page sql->" + pageSql);
					ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql);
				}
			}
		}
		return ivk.proceed();
	}

	/**
	 * 获取更新后的pageInfo实例，同时更新该实例中的count(总记录数)
	 */
	@SuppressWarnings("unchecked")
	private PageInfo getRefreshPageInfo(Object parameterObject, int count)
			throws NoSuchFieldException, IllegalAccessException {
		PageInfo page = null;
		if (parameterObject instanceof PageInfo) {
			page = (PageInfo) parameterObject;
			page.setTotalResult(count);
		} else if (parameterObject instanceof Map) {
			Map<String, Object> map = (Map<String, Object>) parameterObject;
			page = (PageInfo) map.get("page");
			if (page == null)
				page = new PageInfo();
			page.setTotalResult(count);
		} else {
			Field pageField = ReflectHelper.getFieldByFieldName(
					parameterObject, "page");
			if (pageField != null) {
				page = (PageInfo) ReflectHelper
						.getValueByFieldName(parameterObject,"page");
				if (page == null)
					page = new PageInfo();
				page.setTotalResult(count);
				ReflectHelper.setValueByFieldName(parameterObject,
						"page", page);
			} else {
				throw new NoSuchFieldException(parameterObject
						.getClass().getName());
			}
		}
		return page;
	}

	/**
	 * 获取结果集的数据条数
	 */
	private int getResultSetCount(MappedStatement mappedStatement, BoundSql boundSql,
			Object parameterObject, Connection connection, String preSql) throws SQLException {
		// begin
		StringBuilder sb = new StringBuilder(
				"select count(*) from (");
		sb.append(preSql);
		sb.append(") myCount");
		//log.info("sql count->" + sb.toString());
		PreparedStatement countStmt = connection
				.prepareStatement(sb.toString());
		BoundSql countBS = new BoundSql(
				mappedStatement.getConfiguration(), sb.toString(),
				boundSql.getParameterMappings(), parameterObject);
		MetaObject countBsObject = SystemMetaObject.forObject(countBS);
		MetaObject boundSqlObject = SystemMetaObject.forObject(boundSql);
		countBsObject.setValue("metaParameters",boundSqlObject.getValue("metaParameters"));
		setParameters(countStmt, mappedStatement, countBS,
				parameterObject);
		ResultSet rs = countStmt.executeQuery();
		int count = 0;
		if (rs.next()) {
			count = rs.getInt(1);
		}
		rs.close();
		countStmt.close();
		// end
		return count;
	}

	/**
	 * 填充PreparedStatement sql和映射关系,查询参数填充
	 * **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setParameters(PreparedStatement ps,
			MappedStatement mappedStatement, BoundSql boundSql,
			Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters")
				.object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql
				.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration
					.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null
					: configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry
							.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName
							.startsWith(ForEachSqlNode.ITEM_PREFIX)
							&& boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value)
									.getValue(
											propertyName.substring(prop
													.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject
								.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException(
								"There was no TypeHandler found for parameter "
										+ propertyName + " of statement "
										+ mappedStatement.getId());
					}
					typeHandler.setParameter(ps, i + 1, value,
							parameterMapping.getJdbcType());
				}
			}
		}
	}

	/**
	 * 分页sql
	 * */
	private String generatePageSql(String sql, PageInfo page) {
		
		if (page != null && (dialect != null || !dialect.equals(""))) {
			StringBuffer pageSql = new StringBuffer();
			// 根据数据库来来决定用什么方式来做分页,mysql和oracle使用分页是不同的
			if ("mysql".equals(dialect)) {
				pageSql.append(sql);
				if (StringUtils.isNotEmpty(page.getSortField())) {
					pageSql.append(" order by ");
					pageSql.append(page.getSortField());
					if (StringUtils.isNotEmpty(page.getOrder())) {
						pageSql.append(" " + page.getOrder());
					}
				}
				pageSql.append(" limit " + page.getCurrentResult() + ","
						+ page.getPageSize());
			} else if ("oracle".equals(dialect)) {
				pageSql.append("select * from (select tmp_tb.*,ROWNUM row_id from (");
				pageSql.append(sql);
				if (StringUtils.isNotEmpty(page.getSortField())) {
					pageSql.append(" order by ");
					pageSql.append(page.getSortField());
					if (StringUtils.isNotEmpty(page.getOrder())) {
						pageSql.append(" " + page.getOrder());
					}
				}
				pageSql.append(")  tmp_tb where ROWNUM<=");
				pageSql.append(page.getCurrentResult() + page.getPageSize());
				pageSql.append(") where row_id>");
				pageSql.append(page.getCurrentResult());
			}
			return pageSql.toString();
		} else {
			return sql;
		}
	}

	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	/**
	 * 设置初始化参数
	 * */
	public void setProperties(Properties p) {
		dialect = p.getProperty("dialect");
		if (dialect == null || dialect.equals("")) {
			try {
				throw new PropertyException("dialect property is not found!");
			} catch (PropertyException e) {
				e.printStackTrace();
			}
		}
		pageSqlId = p.getProperty("pageSqlId");
		if (pageSqlId == null || pageSqlId.equals("")) {
			try {
				throw new PropertyException("pageSqlId property is not found!");
			} catch (PropertyException e) {
				e.printStackTrace();
			}
		}
	}

}