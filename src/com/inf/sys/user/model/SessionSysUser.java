package com.inf.sys.user.model;

import java.util.List;

//登录管理员 sesson中的内容
public class SessionSysUser {
	
	private  String userId;  // 管理员id
	private String  loginName; // 管理员登录名
	private String  password;//
	private String userName; // 管理员名称
	private Boolean isAdmin;// 是否是超级管理员 true 是。false-不是
	private List<String> roleIds;// 用户与角色ids
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public List<String> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
