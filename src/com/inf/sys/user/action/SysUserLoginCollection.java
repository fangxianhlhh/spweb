package com.inf.sys.user.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.inf.sys.user.model.SessionSysUser;
import com.inf.sys.user.model.TbSysUser;
import com.inf.sys.user.service.TbSysUserService;
import com.inf.system.utiles.Constant;
import com.inf.system.utiles.MD5Util;


@Controller
@RequestMapping("sysUserLogin/")
public class SysUserLoginCollection {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysUserLoginCollection.class);
	
	@Autowired
	private TbSysUserService tbSysUserService;
	
	
	// 用户登录
	@RequestMapping(value="SysUserLogin.do", method=RequestMethod.POST)
	public ModelAndView  SysUserLogin(@RequestParam(required = false ) String loginName,
			@RequestParam(required=false) String password, @RequestParam(required=false) String code,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ModelAndView mv = new ModelAndView("index");
		
		// 验证码
		if (request.getSession().getAttribute(Constant.PIC_CODE_SESSION) == null) {
			request.setAttribute("errorMessage", "验证码失效，请重新输入!");
			return new ModelAndView("../login");
		}
		
		String sessioncode = (String) request.getSession().getAttribute(Constant.PIC_CODE_SESSION);
		
		if (!sessioncode.equalsIgnoreCase(code)) {
			request.setAttribute("errorMessage", "请正确输入验证码！");
			return new ModelAndView("../login");
		}
		
		// 获取session 中用户信息
		SessionSysUser sessionUser = (SessionSysUser) request.getSession().getAttribute(Constant.USER_SESSION);
		
		 
		if(null != sessionUser){
			LOGGER.info("用户   loginuasename{} 已经登录",sessionUser.getLoginName());
			
			TbSysUser tbSysUser= new  TbSysUser();
			 tbSysUser.setLoginName(loginName);
			 tbSysUser.setUserName(sessionUser.getUserName());
			 
			Map<String, Object> objMaps= new HashMap<String, Object>();
			objMaps.put("tbSysUser", tbSysUser);
			objMaps.put("resList", tbSysUserService.getUserPrivices(sessionUser.getUserId(), sessionUser.getIsAdmin()));
			mv.addObject("result",objMaps);
			return mv;
		}		
		Map<String, Object> mapper= new HashMap<String, Object>();
		mapper.put(Constant.USER_LOGIN_NAME, loginName);
		mapper.put(Constant.USER_PASSWORD, MD5Util.convertMD5(password));
		
		//登录
		Map<String ,Object> objMap = tbSysUserService.getLoginUser(request, mapper);
		
		if(CollectionUtils.isEmpty(objMap)){
			request.setAttribute("errorMessage", "手机号码/用户名或登录密码不正确!");
			return new ModelAndView("../login");
		}
		
		TbSysUser tbSysUser=(TbSysUser) objMap.get("tbSysUser");
		
		
		
		if ( null !=tbSysUser && StringUtils.isNotBlank(tbSysUser.getUserId())) {
			request.setAttribute("errorMessage", "登录成功!");
			LOGGER.info("用户：loginName={},登录成功",loginName);
			mv.addObject("result",objMap);
			return mv;
		} else {
			request.setAttribute("errorMessage", "手机号码/用户名或登录密码不正确!");
			return new ModelAndView("../login");
		}
	}
	
	
	/**
	 * 用户注销:
	 */
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.getSession().invalidate();
		return new ModelAndView("../login");
	}
	

}
