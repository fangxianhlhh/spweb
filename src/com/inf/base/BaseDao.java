package com.inf.base;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.inf.system.utiles.PropertyUtils;
import com.inf.system.utiles.page.PageBean;

/**
 * 顶层接口
 * @author zongxin
 * 2014年6月13日
 */
public  class BaseDao extends SqlSessionDaoSupport{

	/*
	 // 使用 SqlSessionTemplate
	 @Autowired
	 private SqlSessionTemplate sqlSessionTemplate;
	
	 @Autowired
	 public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate)
     {
		super.setSqlSessionTemplate(sqlSessionTemplate);
     }*/
	
	//使用sqlSessionFactory
	@Autowired 
	private  SqlSessionFactory sqlSessionFactory;
		
	@Autowired
	 public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory)
	  {
	      super.setSqlSessionFactory(sqlSessionFactory);
	  }
	
	/**
	 * 执行insert操作
	 * @param statement
	 * @return
	 */
	public int insert(String statement) {
		return getSqlSession().insert(statement);
	}

	/**
	 * 执行insert操作
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public int insert(String statement, Object parameter) {
		return getSqlSession().insert(statement, parameter);
	}
	
	public int update(String statement) {
		return getSqlSession().update(statement);
	}

	public int update(String statement, Object parameter) {
		return getSqlSession().update(statement, parameter);
	}
	
	public int delete(String statement) {
		return getSqlSession().delete(statement);
	}
	
	public int delete(String statement, Object parameter) {
		return getSqlSession().delete(statement, parameter);
	}
	
	/**
	 * 获取一个list集合
	 * @param statement
	 * @return
	 */
	public List<?> selectList(String statement) {
		return getSqlSession().selectList(statement);
	}
	
	/**
	 * 根据参数 获取一个list集合
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public List<?> selectList(String statement, Object parameter) {
		return getSqlSession().selectList(statement, parameter);
	}
	
	public Map<?, ?> selectMap(String statement, String mapKey) {
		return getSqlSession().selectMap(statement, mapKey);
	}

	public Map<?, ?> selectMap(String statement, Object parameter, String mapKey) {
		return getSqlSession().selectMap(statement, parameter, mapKey);
	}
	
	/**
	 * 获取Object对象
	 * @param statement
	 * @return
	 */
	public Object selectOne(String statement) {
		return getSqlSession().selectOne(statement);
	}
	
	/**
	 * 获取Object对象
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public Object selectOne(String statement, Object parameter) {
		return getSqlSession().selectOne(statement, parameter);
	}
	
	
	/**
	 * 未实现  待完善
	 * 根据传入的参数, 返回List分页
	 * @param request
	 * @param viewsize
	 * @param sql
	 * @param pageFormat
	 * @return
	 */
	@SuppressWarnings("unused")
	public List<?> selectListForPager(HttpServletRequest request, int viewsize, String pageFormat, String statement, Map map) {
		PageBean pageBean = new PageBean(request);
		
		int currentpage = pageBean.getCurrentPage();
		int nextviewsize = viewsize*(currentpage - 1);
		int currentviewsize = viewsize*currentpage;
		
		//String statementAll=" limit " + nextviewsize +", "+ viewsize;
		String statementAll="";
		
		List<?> list = selectList(statementAll);
		int count = (Integer)selectOne(statement);
		
		//String strParam =" and num > "+nextviewsize+" and num<= "+currentviewsize ;
		//List list = queryForList(sql+strParam);
		//int count = queryForList(sql).size();
		
		pageBean.setRscount(count);
		pageBean.setviewsize(viewsize);
		pageBean.getPageCount();

		String pagetool = pageBean.pagetool(pageFormat);
		request.setAttribute("pagetool", pagetool);
		return list;
	}
	
	/**
	 * 分页，默认设置好的分页格式和分页数
	 * @param request
	 * @param statement
	 * @return
	 */
	public List<?> selectListForPager(HttpServletRequest request,  String statement, Map<?, ?> map){
			int viewsize = Integer.parseInt(PropertyUtils.getProperty("viewsize"));
			String pageFormat = PropertyUtils.getProperty("pageFormat");
		return selectListForPager(request, viewsize, pageFormat, statement, map);
	}
	
	/**
	 * 获取connection, 以便执行较为复杂的用法
	 * @return
	 */
	public Connection getConnection() {
		return getSqlSession().getConnection();
	}
	
}
