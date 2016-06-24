package com.inf.sys.user.model;

import java.util.Date;
/**
 * 系统用户信息表
 * @author Administrator
 *
 */
public class TbSysUser {
   
	private String userId; //用户id

    private String loginName; //登录名

    private String password;//登录密码

    private String userName;//姓名

    private String phone;//联系电话

    private String email;//联系邮箱

    private Date lastLoginTime;// 最后登录时间


    private String createUser;//创建用户

    private Date createTime;//创建时间

    private String updateUser;//修改用户

    private Date updateTimes;//修改时间

    private String descs;//描述

    private Integer status;//是否有效 0-有效 1-无效 默认0

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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

    public Date getUpdateTimes() {
        return updateTimes;
    }

    public void setUpdateTimes(Date updateTimes) {
        this.updateTimes = updateTimes;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs == null ? null : descs.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public TbSysUser(String userId, String updateUser) {
		super();
		this.userId = userId;
		this.updateUser = updateUser;
	}

	public TbSysUser() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}