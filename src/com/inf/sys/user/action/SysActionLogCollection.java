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
import org.springframework.web.bind.annotation.RequestParam;

import com.inf.base.BaseController;
import com.inf.sys.user.service.TbSysActionLogService;


@Controller
@RequestMapping("sysActionLog/")
public class SysActionLogCollection  extends BaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(SysActionLogCollection.class);
	

	@Autowired
	private  TbSysActionLogService tbSysActionLogService;
	
	/**
	 * 分页查询
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getSystemLogListPage.do")
	public void getSystemLogListPage(HttpServletRequest request, HttpServletResponse response){
		@SuppressWarnings("unchecked")
		Map<String ,Object> paramMap = getMap(request);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String json;
		try {
			json = tbSysActionLogService.getSystemlogListPage(paramMap, request);
			PrintWriter out = response.getWriter();
			out.print(json);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取系统操作日志错误",e);
		}
	} 
	
  
	
	/**
	 * 详情
	 * @param request
	 * @param response
	 * @throws Exception 
	 * @throws Exception
	 */
	@RequestMapping("getSystemLog.do")
	public void getSystemLog(@RequestParam(required=true) String actionId,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String json = tbSysActionLogService.getSystemLog(actionId);
		PrintWriter out = response.getWriter();
	    out.print(json);
		
	}
}
