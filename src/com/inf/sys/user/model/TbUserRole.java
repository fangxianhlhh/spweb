package com.inf.sys.user.model;

import java.util.Date;

//用户角色关系
public class TbUserRole {
    
	private String turId; //主键id

    private String userId; //用户id

    private String roleId;//角色id

    private String createUser;//创建用户

    private Date createTime;//创建时间

    private String updataUser;//修改用户

    private Date updataTime;//修改时间

    private Integer status;//是否有效， 0-有效1-无效

    public String getTurId() {
        return turId;
    }

    public void setTurId(String turId) {
        this.turId = turId == null ? null : turId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
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

    public String getUpdataUser() {
        return updataUser;
    }

    public void setUpdataUser(String updataUser) {
        this.updataUser = updataUser == null ? null : updataUser.trim();
    }

    public Date getUpdataTime() {
        return updataTime;
    }

    public void setUpdataTime(Date updataTime) {
        this.updataTime = updataTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public TbUserRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TbUserRole(String userId, String roleId, String createUser) {
		super();
		this.userId = userId;
		this.roleId = roleId;
		this.createUser = createUser;
	}
    
    
}