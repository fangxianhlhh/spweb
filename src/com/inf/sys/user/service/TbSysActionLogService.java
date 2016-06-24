package com.inf.sys.user.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

// 操作日志
public interface TbSysActionLogService {

	/**
	   * 操作日志分页查询
	   * @param paramMap
	   * @param request
	   * @return
	   * @throws Exception
	   */
		String getSystemlogListPage(Map<String ,Object> paramMap,HttpServletRequest request) throws Exception;
		
		 /**
		  * 详情
		  * @param request
		  * @return
		  */
		String getSystemLog(String actionId);
}
