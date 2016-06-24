package com.inf.sys.user.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;


//登录日志
public interface TbSysLoginLogService {

	/**
	   * 登录日志分页查询
	   * @param paramMap
	   * @param request
	   * @return
	   * @throws Exception
	   */
		String getLoginLogListPage(Map<String ,Object> paramMap,HttpServletRequest request) throws Exception;
		
		 /**
		  * 详情
		  * @param request
		  * @return
		  */
		String getLoginLog(HttpServletRequest request);
}
