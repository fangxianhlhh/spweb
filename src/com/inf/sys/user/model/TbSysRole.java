package com.inf.sys.user.model;

import java.util.Date;
//系统角色
public class TbSysRole {
    private String roleId; //主键id

    private String roleName; // 角色名称

    private String remarkDesc;// 角色描述

    private String createUser;// 创建用户

    private Date createTime;// 创建时间

    private String updateUser;//修改用户

    private Date updateTime;//修改时间

    private Integer status;// 是否 有效 0 - 有效 1- 无效， 默认 0 

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }


    public String getRemarkDesc() {
        return remarkDesc;
    }

    public void setRemarkDesc(String remarkDesc) {
        this.remarkDesc = remarkDesc == null ? null : remarkDesc.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
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
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public TbSysRole(String roleName, String remarkDesc, String createUser) {
		super();
		this.roleName = roleName;
		this.remarkDesc = remarkDesc;
		this.createUser = createUser;
	}

	
	public TbSysRole(String roleId, String roleName, String remarkDesc,
			String updateUser) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.remarkDesc = remarkDesc;
		this.updateUser = updateUser;
	}

	public TbSysRole() {
		super();
	}
    
    
}