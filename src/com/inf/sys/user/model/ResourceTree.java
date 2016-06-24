package com.inf.sys.user.model;

import java.util.List;

// 系统资源树
public class ResourceTree {
	 	private String resId; //资源id

	    private String resName; //资源名称

	    private Integer resType; //资源类型， 1-菜单， 2按钮

	    private String resUrl; // 资源地址

	    private String parendResId;// 父资源id

	    private Long resLevel;//资源深度

	    private Long resOrder;//资源顺序

	    private String remarkDesc;// 资源描述

	    private List<ResourceTree> children; // 子节点
	    
	    

		public String getResId() {
			return resId;
		}

		public void setResId(String resId) {
			this.resId = resId;
		}

		public String getResName() {
			return resName;
		}

		public void setResName(String resName) {
			this.resName = resName;
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
			this.resUrl = resUrl;
		}

		public String getParendResId() {
			return parendResId;
		}

		public void setParendResId(String parendResId) {
			this.parendResId = parendResId;
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
			this.remarkDesc = remarkDesc;
		}

		public List<ResourceTree> getChildren() {
			return children;
		}

		public void setChildren(List<ResourceTree> children) {
			this.children = children;
		}

}
