package com.inf.sys.user.model;

import java.util.Date;

//系统操作日志
public class TbSysActionLog {
    private String actionId; // 主键id

    private String userId; // 用户id

    private String actionLogs; // 操作日志

    private Date createTime; // 操作时间

    private String remarkDesc; //操作描述

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId == null ? null : actionId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getActionLogs() {
        return actionLogs;
    }

    public void setActionLogs(String actionLogs) {
        this.actionLogs = actionLogs == null ? null : actionLogs.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemarkDesc() {
        return remarkDesc;
    }

    public void setRemarkDesc(String remarkDesc) {
        this.remarkDesc = remarkDesc == null ? null : remarkDesc.trim();
    }

	public TbSysActionLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TbSysActionLog(String userId, String actionLogs, String remarkDesc) {
		super();
		this.userId = userId;
		this.actionLogs = actionLogs;
		this.remarkDesc = remarkDesc;
	}
    
    
    
}