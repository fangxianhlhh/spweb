package com.inf.sys.user.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.inf.sys.user.model.Menu;
import com.inf.sys.user.model.TbSysUser;

/**
 * 系统管理员
 * @author Administrator
 *
 */
public interface TbSysUserService {
	 
		//新增管理员信息
	     int insertTbSysUser(HttpServletRequest request,TbSysUser tbSysUser,String[] roleId) throws Exception;

	     
	     //分页获取系统管理员信息
	     String  getSysUserListPage(HttpServletRequest request,Map<String, Object> mapper) throws Exception;
	    
	    //管理员登录
	    Map<String,Object> getLoginUser(HttpServletRequest request ,Map<String, Object> mapper);
	    
	    //获取管理员的权限
	    List<Menu>  getUserPrivices(String userId,boolean isAdmin);

	    /**
		 * 验证用户添加用户名是否使用
		 */
		public boolean checkValidLoginName(String loginName)throws Exception;
		
		
		/**
		 * 更新人员修改信息,修改密码
		 * @param type 
		 */
		public void updateSysUser(HttpServletRequest request, TbSysUser tbSysUse,String[] roleId)throws Exception;
		
		/**
		 * 获得单个人员信息
		 */
		public String getSysUserDetail(String userId)throws Exception;
		
		/**
		 * 删除人员信息，可批量删除
		 */
		public void deleteSysUser(HttpServletRequest request,String userId)throws Exception;

	    
	  
}
