package com.inf.sys.user.model;

import java.util.Date;

/**
 * 系统登录日志
 * @author Administrator
 *
 */
public class TbSysLoginLog {
    private String lid; // 主键id

    private String userId; // 登录用户id

    private String loginIp; //登录ip

    private Date loginTime; //登录时间

    private String remarkDesc; //登录描述

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid == null ? null : lid.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getRemarkDesc() {
        return remarkDesc;
    }

    public void setRemarkDesc(String remarkDesc) {
        this.remarkDesc = remarkDesc == null ? null : remarkDesc.trim();
    }

	public TbSysLoginLog(String userId, String loginIp, String remarkDesc) {
		super();
		this.userId = userId;
		this.loginIp = loginIp;
		this.remarkDesc = remarkDesc;
	}

	public TbSysLoginLog() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
    
}