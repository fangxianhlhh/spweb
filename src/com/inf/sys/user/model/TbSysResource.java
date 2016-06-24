package com.inf.sys.user.model;

import java.util.Date;

public class TbSysResource {
    private String resId; //资源id

    private String resName; //资源名称

    private Integer resType; //资源类型， 1-菜单， 2按钮

    private String resUrl; // 资源地址

    private String parendResId;// 父资源id

    private Long resLevel;//资源深度

    private Long resOrder;//资源顺序

    private String remarkDesc;// 资源描述

    private String createUser;// 创建用户

    private Date createTime;// 创建时回见

    private String updateUser;//修改用户

    private Date updateTime;//修改时间

    private Integer status;// 状态 0-有效  1-无效
    

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId == null ? null : resId.trim();
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName == null ? null : resName.trim();
    }

    public Integer getResType() {
        return resType;
    }

    public void setResType(Integer resType) {
        this.resType = resType;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl == null ? null : resUrl.trim();
    }

    public String getParendResId() {
        return parendResId;
    }

    public void setParendResId(String parendResId) {
        this.parendResId = parendResId == null ? null : parendResId.trim();
    }

    public Long getResLevel() {
        return resLevel;
    }

    public void setResLevel(Long resLevel) {
        this.resLevel = resLevel;
    }

    public Long getResOrder() {
        return resOrder;
    }

    public void setResOrder(Long resOrder) {
        this.resOrder = resOrder;
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
}