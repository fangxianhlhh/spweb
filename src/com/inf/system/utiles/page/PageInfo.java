package com.inf.system.utiles.page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
@SuppressWarnings("rawtypes")
public class PageInfo implements Serializable {

	private static final long serialVersionUID = 587754556498974978L;

	// 每一页显示多少
	private int pageSize = 10;
	// 总页数
	private int totalPage;
	// 总记录数
	private int totalResult;
	// 当前页数
	private int currentPage;
	// 当前显示到的ID, 在mysql limit 中就是第一个参数.
	private int currentResult;

	private String sortField;

	private String requestUrl;// 存放分页时的请求URL

	//desc or asc
	private String order;

	
	private List data;

	// 查询参数,用于拼接上一页,下一页链接
	private Map params;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCurrentResult() {
		return currentResult;
	}

	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public Map getParams() {
		return params;
	}

	public void setParams(Map params) {
		this.params = params;
	}

	

}
