package com.inf.sys.user.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.inf.base.BaseController;
import com.inf.sys.user.mapper.TbSysActionLogMapper;
import com.inf.sys.user.model.SessionSysUser;
import com.inf.sys.user.model.TbSysActionLog;
import com.inf.sys.user.model.TbSysUser;
import com.inf.sys.user.service.TbSysUserService;
import com.inf.system.utiles.Constant;
import com.inf.system.utiles.JsonBean;
import com.inf.system.utiles.MD5Util;

//系统用户
@Controller
@RequestMapping("sysUser/")
public class SysUserCollection extends BaseController{


	private static final Logger LOGGER = LoggerFactory.getLogger(SysUserCollection.class);
	
	//系统用户
	@Autowired
	private TbSysUserService tbSysUserService;
	
	//系统操作日志
	@Autowired
	private TbSysActionLogMapper tbSysActionLogMapper;
	
	//系统管理员信息展示
	@SuppressWarnings("unchecked")
	@RequestMapping(value="getSysUserListPage.do", method=RequestMethod.POST)
	public void getSysUserListPage(HttpServletRequest request,HttpServletResponse response) {

		// 返回一个Json字符串
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//获取查询参数
		Map<String, Object> mapper = getMap(request);
		
		String jsonString=null;
		try {
			jsonString = tbSysUserService.getSysUserListPage(request,mapper);
			PrintWriter out = response.getWriter();
			out.print(jsonString);
		} catch (Exception e) {
			LOGGER.error("获取系统管理员列表信息失败！",e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 验证用户添加用户名是否
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("checkValidLoginName.do")
	public void checkValidLoginName(@RequestParam(required=true) String loginName,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		Map<String, Object> reponseMessage = new HashMap<String, Object>();
		boolean inValidLoginName = tbSysUserService.checkValidLoginName(loginName);
		//用户名已存在
		if (inValidLoginName) {
			reponseMessage.put("success", true);
			response.getWriter().print(false);
			
		} else {
			reponseMessage.put("success", false);
			response.getWriter().print(true);
		}
	}
	
	//添加人员
	@RequestMapping("sysUserAdd.do")
	public void sysUserAdd(@RequestParam(required=false) String[] roleId, HttpServletRequest request,
			HttpServletResponse response, TbSysUser tbSysUser) throws Exception {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		Map<String, Object> reponseMessage = new HashMap<String, Object>();
		try {
		int result=	tbSysUserService.insertTbSysUser(request, tbSysUser,roleId);
		if(result==Constant.DEFAULT_RESULT_1){
			reponseMessage.put("success", "true");
		}else{
			reponseMessage.put("success", "false");
		}
			
			reponseMessage.put("msg", "添加成功");
			
		} catch (Exception ex) {
			LOGGER.error("新增管理人员错误 用户名={}，error={}",tbSysUser.getLoginName(),ex);
			reponseMessage.put("success", "false");
			reponseMessage.put("msg", ex.getMessage());
		}
		
		String json = JsonBean.toJson(reponseMessage);
		PrintWriter out = response.getWriter();
		out.print(json);
		
		//系统日志
		SessionSysUser  sysusersession =  (SessionSysUser) request.getSession().getAttribute(Constant.USER_SESSION);
		 if(null == sysusersession){
			 return;
		 }else{
			 tbSysActionLogMapper.insertTbSysActionLog(new TbSysActionLog(
					 sysusersession.getUserId(),
					 Constant.USER_SYS_ADDUSER,
					 Constant.USER_SYS_ADDUSER+",用户名："+tbSysUser.getLoginName()
					 ));
		}
		 
		
	}
	
	

	/**
	 * 更新人员信息,修改密码
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("updateSysUser.do")
	public void updateSysUser(@RequestParam(required=false) String pwd,
			@RequestParam(required=false) String[] roleId,
			HttpServletRequest request, HttpServletResponse response, TbSysUser tbSysUser) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		if(StringUtils.isNotBlank(pwd)){
			tbSysUser.setPassword(MD5Util.convertMD5(pwd));
		}

		Map<String, Object> reponseMessage = new HashMap<String, Object>();
		try {
			tbSysUserService.updateSysUser(request, tbSysUser,roleId);
			reponseMessage.put("success", "true");
			reponseMessage.put("msg", "更新成功");
		} catch (Exception ex) {
			LOGGER.error("更新人员信息失败！loginName={}",tbSysUser.getLoginName(),ex);
			reponseMessage.put("success", "false");
			reponseMessage.put("msg", ex.getMessage());
		}

		String json = JsonBean.toJson(reponseMessage);
		PrintWriter out = response.getWriter();
		out.print(json);

		//系统日志
		SessionSysUser  sysusersession =  (SessionSysUser) request.getSession().getAttribute(Constant.USER_SESSION);
		if(null == sysusersession){
			return;
		}else{
			tbSysActionLogMapper.insertTbSysActionLog(new TbSysActionLog(
					sysusersession.getUserId(),
					Constant.USER_SYS_UPDATEUSER,
					Constant.USER_SYS_UPDATEUSER+",用户名："+tbSysUser.getLoginName()));
		}
	}
		/**
		 *获取单个用户信息
		 * @param request
		 * @param response
		 * @throws Exception
		 */
		@RequestMapping("sysUserDetail.do")
		public void sysUserDetail(@RequestParam(required=true) String userId,
				HttpServletRequest request, HttpServletResponse response) throws Exception{
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			
			String json = tbSysUserService.getSysUserDetail(userId);
			PrintWriter out = response.getWriter();
			out.print(json);
		}
		
		
		
		/**
		 * 冻结人员
		 * @param request
		 * @param response
		 * @throws Exception
		 */
		@RequestMapping("sysUserDelete.do")
		public void sysUserDelete(@RequestParam(required=true) String userId,
				HttpServletRequest request, HttpServletResponse response) throws Exception {

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");

			Map<String, Object> reponseMessage = new HashMap<String, Object>();
			try {
				tbSysUserService.deleteSysUser(request,userId);
				reponseMessage.put("success", "true");
				reponseMessage.put("msg", "删除成功");
			} catch (Exception ex) {
				reponseMessage.put("success", "false");
				reponseMessage.put("msg", ex.getMessage());
			}
			
			String json = JsonBean.toJson(reponseMessage);
			PrintWriter out = response.getWriter();
			out.print(json);
			
			//系统日志操作
			tbSysActionLogMapper.insertTbSysActionLog(new TbSysActionLog(
					userId,
					Constant.USER_SYS_DELETEUSER,
					Constant.USER_SYS_DELETEUSER));
			
		}
		
		
		/**
		 * 首页修改密码
		 * @param request
		 * @param response
		 * @throws Exception
		 */
		@RequestMapping("indexChangePwd.do")
		public void indexChangePwd(@RequestParam(required=true) String oldPwd,
				@RequestParam(required=true) String newPwd,
				HttpServletRequest request,HttpServletResponse response)throws Exception{
			//获取原密码
			String oldPwdMd5 = MD5Util.convertMD5(oldPwd);

			//定义一个返回参数
			String msg = "0";
			
			SessionSysUser sessionUser=null;
			try {


				//获取session值用户信息
				 sessionUser = (SessionSysUser)(request.getSession().getAttribute(Constant.USER_SESSION));
				if(sessionUser.getPassword().equals(oldPwdMd5)){

					TbSysUser tbSysUser = new TbSysUser();
					tbSysUser.setPassword(MD5Util.convertMD5(newPwd));
					tbSysUser.setUserId(sessionUser.getUserId());
					tbSysUserService.updateSysUser(request, tbSysUser,null);
					msg = "1";
					sessionUser.setPassword(MD5Util.convertMD5(newPwd));
					request.getSession().removeAttribute(Constant.USER_SESSION);
					request.getSession().setAttribute(Constant.USER_SESSION, sessionUser);
				}

			} catch (Exception e) {
             LOGGER.error("修改密码失败！",e);
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(msg);
			
			//系统日志操作
			tbSysActionLogMapper.insertTbSysActionLog(new TbSysActionLog(
					sessionUser.getUserId(),
					Constant.USER_SYS_UPDATEPSWD,
					Constant.USER_SYS_UPDATEPSWD+"用户名："+sessionUser.getLoginName()));
		}
	
}
