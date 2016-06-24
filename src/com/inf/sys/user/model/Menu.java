package com.inf.sys.user.model;

import java.util.List;

public class Menu {

	private String resId;
	private String resParentId;
	private String resName;
	private String resUrl;
	private List<Menu> resChildren;
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public String getResParentId() {
		return resParentId;
	}
	public void setResParentId(String resParentId) {
		this.resParentId = resParentId;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getResUrl() {
		return resUrl;
	}
	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}
	public List<Menu> getResChildren() {
		return resChildren;
	}
	public void setResChildren(List<Menu> resChildren) {
		this.resChildren = resChildren;
	}
	
	
	
	
}
