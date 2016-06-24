package com.inf.sys.user.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inf.base.BaseController;
import com.inf.sys.user.service.TbSysLoginLogService;

//登录日志管理
@Controller
@RequestMapping("sysLoginLog/")
public class SysLoginLogCollection  extends BaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(SysLoginLogCollection.class);
	
	@Autowired
	private TbSysLoginLogService tbSysLoginLogService;
	
	/**
	 * 分页查询
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getLoginLogListPage.do")
	public void getLoginLogListPage(HttpServletRequest request, HttpServletResponse response){
		@SuppressWarnings("unchecked")
		Map<String,Object> paramMap = getMap(request);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String json;
		try {
			json = tbSysLoginLogService.getLoginLogListPage(paramMap, request);
			PrintWriter out = response.getWriter();
			out.print(json);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取登录日志信息失败",e);
		}
	} 
	
  
	
	/**
	 * 详情
	 * @param request
	 * @param response
	 * @throws Exception 
	 * @throws Exception
	 */
	@RequestMapping("getLoginLog.do")
	public void getLoginLog(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String json = tbSysLoginLogService.getLoginLog(request);
		PrintWriter out = response.getWriter();
	    out.print(json);
		
	}
	
}
