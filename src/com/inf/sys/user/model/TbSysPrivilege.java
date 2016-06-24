package com.inf.sys.user.model;

import java.util.Date;

public class TbSysPrivilege {
	
	private String privId; // 主键id
	private String roleId;// 角色id
	private String resId;// 资源id
	private String createUser;// 创建用户
	private Date createTime;// 创建时间
	private String updateUser;// 修改用户
	private Date updateTime;// 修改时间
	private String remarkDesc;  // 描述
	private Integer status;// 是否有效 0- 有效1-无效
	public String getPrivId() {
		return privId;
	}
	public void setPrivId(String privId) {
		this.privId = privId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getRemarkDesc() {
		return remarkDesc;
	}
	public void setRemarkDesc(String remarkDesc) {
		this.remarkDesc = remarkDesc;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public TbSysPrivilege() {
		super();
	}
	
	public TbSysPrivilege(String roleId, String resId, String createUser,
			String remarkDesc) {
		super();
		this.roleId = roleId;
		this.resId = resId;
		this.createUser = createUser;
		this.remarkDesc = remarkDesc;
	}
	
	
	
	

}
