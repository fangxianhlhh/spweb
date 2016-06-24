package com.inf.sys.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.inf.sys.user.model.TbSysLoginLog;
import com.inf.system.utiles.page.PageInfo;
/**
 * 系统用户登录日志
 * @author Administrator
 *
 */
public interface TbSysLoginLogMapper {

	/**
	 * 新增登录日志
	 * @param request
	 */
	int insertTbSysLoginLog(TbSysLoginLog tbSysLoginLog);

	/**
	 * 登录日志分页查询
	 * @param page
	 * @param paramMap
	 * @return
	 */
	List<Map<String,Object>>   getLoginLogListPage(@Param("page") PageInfo page, @Param("paramMap") Map<String,Object> paramMap);
	/**
	 * 登录日志详情
	 * @param lid
	 * @return
	 */
	Map<String,Object> getLoginLogDetail(String lid);
}