package com.inf.system.utiles;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings({"rawtypes", "unused"})
public class Page {
	
	private int curPageNO;//当前页
	
	private int currentPage;//当前页数

	private int pageSize;//每页显示的记录数

	private int rowsCount;//记录行数

	private int pageCount;//页数
	
	private List list;//记录集

	private String url;//url地址

	private String toolBar;//生成一个分页工具条
	
	private String customToolBar;//生成一个定制的分页工具条

	/**
	 * @param list，记录集
	 * @param rowsCount，记录行数
	 */
	public Page(List list, int pageSize, HttpServletRequest request) {
		int rowsCount = list.size();
		int currentPage = request.getParameter("currentPage") != null ? Integer
				.parseInt(request.getParameter("currentPage")) : 1;
		list = list
				.subList((currentPage - 1) * pageSize,
						currentPage * pageSize > list.size() ? list.size()
								: currentPage * pageSize);
		this.list = list;
		this.rowsCount = rowsCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.pageCount = (int) Math.ceil((double) rowsCount / pageSize);
		this.url = getFullUrl(request);
	}
	
	
	/**
	 * @param list，记录集
	 * @param rowsCount，记录行数
	 * @param currentPage，当前页数
	 * @param pageSize，每页显示的记录数
	 */
	public Page(List list, int rowsCount, int currentPage, int pageSize,
			HttpServletRequest request) {
		this.list = list;
		this.rowsCount = rowsCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.pageCount = (int) Math.ceil((double) rowsCount / pageSize);
		this.url =getFullUrl(request);
	}
	/**
	 * 该构造器，要替代上面带有request的构造器
	 * Creates a new instance of Page.
	 * @param list
	 * @param rowsCount
	 * @param currentPage
	 * @param pageSize
	 * @param url
	 */
	public Page(List list, int rowsCount, int currentPage, int pageSize,
			String url) {
		this.list = list;
		this.rowsCount = rowsCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.pageCount = (int) Math.ceil((double) rowsCount / pageSize);
		this.url =url;//getFullUrl(request);
	}
	/**
	 * @param list，记录集
	 * @param rowsCount，记录行数
	 * @param currentPage，当前页数
	 * @param pageSize，每页显示的记录数
	 */
	public Page(List list, int rowsCount, int currentPage, int pageSize
			) {
		this.list = list;
		this.rowsCount = rowsCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.pageCount = (int) Math.ceil((double) rowsCount / pageSize);
	}
	
	public Page() {
	}

	//返回记录集
	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}


	public int getCurPageNO() {
		return curPageNO;
	}

	//getPageSize：返回分页大小
	public int getPageSize() {
		return pageSize;
	}

	//getRowsCount：返回总记录行数
	public int getRowsCount() {
		return rowsCount;
	}
	
	public void setRowsCount(int rowsCount) {
		this.rowsCount=rowsCount;
	}

	//getPageCount：返回总页数
	public int getPageCount() {
		return pageCount;
	}

	//第一页
	public int first() {
		return 0;
	}

	//最后一页
	public int last() {
		return pageCount;
	}

	//上一页
	public int previous() {
		return currentPage-1;
	}

	//下一页
	public int next() {
		return currentPage+1;
	}

	//第一页
	public boolean isFirst() {
		return (currentPage == 1) ? true : false;
	}
	

	//最后页
	public boolean isLast() {
		return (currentPage*pageSize >= rowsCount) ? true : false;
	}

	public void setCurPageNO(int curPageNO) {
		this.curPageNO = curPageNO;
	}

	/**
	 * 获取工具条
	 * 
	 * @return String
	 */
	public void setToolBar(String url) {
		this.url = url;
	}
	
	public void setCustomToolBar(String customToolBar) {
		this.customToolBar = customToolBar;
	}
	
	public String getToolBar() {
		String temp = "";
		if (url.indexOf("?") == -1) {
			temp = "?";
		} else {
			temp = "&";
		}
		String str = "";
		str += "";
		if (isFirst())
			str += "首页 上一页&nbsp;";
		else {
			str += "<a href='" + url + temp + "currentPage=1'>首页</a>&nbsp;";
			str += "<a href='" + url + temp + "currentPage=" + previous()
					+ "'>上一页</a>&nbsp;";
		}
		if (isLast() || rowsCount == 0)
			str += "下一页 尾页&nbsp;";
		else {
			str += "<a href='" + url + temp + "currentPage=" + next()
					+ "'>下一页</a>&nbsp;";
			str += "<a href='" + url + temp + "currentPage=" + getPageCount()
					+ "'>尾页</a>&nbsp;";
		}
		str += "&nbsp;共<b>" + rowsCount + "</b>条记录&nbsp;&nbsp;"
				+ currentPage + "/" + pageCount + "页&nbsp; ";
		str += "&nbsp;转到第<select name='page'style='width:50px;height:20px;margin:0' onChange=\"location='" + url + temp
				+ "&currentPage='+this.options[this.selectedIndex].value\">";
		int begin = 1;
		int end=pageCount;
		for (int i = begin; i <= end; i++) {
			if (i == currentPage){
				str += "<option value='" +i+ "' selected>"
						+ i + "</option>";
			}
			else
			{
				str += "<option value='" +i+ "'>" + i
						+ "</option>";
			}
		}
		str += "</select>页";
		return str;
	}

	public String getToolBar1() {
		String temp = "";
		if (url.indexOf("?") == -1) {
			temp = "?";
		} else {
			temp = "&";
		}
		String str = "";
		str += "";
		if (isFirst())
			str += "首页 上一页&nbsp;";
		else {
			str += "<a href='../" + url + temp + "currentPage=1'>首页</a>&nbsp;";
			str += "<a href='../" + url + temp + "currentPage=" + previous()
					+ "'>上一页</a>&nbsp;";
		}
		if (isLast() || rowsCount == 0)
			str += "下一页 尾页&nbsp;";
		else {
			str += "<a href='../" + url + temp + "currentPage=" + next()
					+ "'>下一页</a>&nbsp;";
			str += "<a href='../" + url + temp + "currentPage=" + getPageCount()
					+ "'>尾页</a>&nbsp;";
		}
		str += "&nbsp;共<b>" + rowsCount + "</b>条记录&nbsp;&nbsp;"
				+ currentPage + "/" + pageCount + "页&nbsp; ";
		str += "&nbsp;转到第<select name='page'style='width:50px;height:20px;margin:0' onChange=\"location='../" + url + temp
				+ "&currentPage='+this.options[this.selectedIndex].value\">";
		int begin = 1;
		int end=pageCount;
		for (int i = begin; i <= end; i++) {
			if (i == currentPage){
				str += "<option value='" +i+ "' selected>"
						+ i + "</option>";
			}
			else
			{
				str += "<option value='" +i+ "'>" + i
						+ "</option>";
			}
		}
		str += "</select>页";
		return str;
	}
	
	public String getCustomToolBar() {
		String temp = "";
		if (url.indexOf("?") == -1) {
			temp = "?";
		} else {
			temp = "&";
		}
		String str = "";
		str += "";
		if (isFirst())
			str += "首页 上一页&nbsp;";
		else {
			str += "<a href='" + url + temp + "currentPage=1'>首页</a>&nbsp;";
			str += "<a href='" + url + temp + "currentPage=" + previous()
					+ "'>上一页</a>&nbsp;";
		}
		if (isLast() || rowsCount == 0)
			str += "下一页 尾页&nbsp;";
		else {
			str += "<a href='" + url + temp + "currentPage=" + next()
					+ "'>下一页</a>&nbsp;";
			str += "<a href='" + url + temp + "currentPage=" + getPageCount()
					+ "'>尾页</a>&nbsp;";
		}
		str += "&nbsp;共<b>" + rowsCount + "</b>条";
		return str;
	}
	
	// 获取整个请求路径，包括参数
	public String getFullUrl(HttpServletRequest request) {
		Enumeration parameterEnumer = request.getParameterNames();
		StringBuffer url = new StringBuffer();
		String parameter = "", vaule = "";
		while (parameterEnumer.hasMoreElements()) {
			parameter = (String) parameterEnumer.nextElement();
			if (parameter.equals("currentPage")) {// 若为分页参数跳出本次循环
				continue;
			} else {
				if (parameter != null) {
					url.append("&").append(parameter).append("=");
					// delete+ modify encode method puyufeng 20140505
					try {
						vaule = URLEncoder.encode(request.getParameter(parameter),"UTF-8");//对传入参数值进行编码解决中文乱码问题
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					// delete- modify encode method puyufeng 20140505
					// add+ modify encode method puyufeng 20140505
//					if(request.getMethod().equals("GET")){
//						vaule=CommonUtil.getURIEncoding(request.getParameter(parameter));
//					}else{
//						vaule =request.getParameter(parameter);
//					}
					// add- modify encode method puyufeng 20140505
					if (vaule != null)
						url.append(vaule);
				}
			}
		}
		if (!url.equals("") & url.indexOf("&") > -1) {
			url = url.replace(0, 1, "?");// 把第一个"&"替换成"?"
		}
		return request.getRequestURI() + url.toString();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public String getPageBar() {
		String str = "";
		if (isFirst())
			str += "首页 上一页&nbsp;";
		else {
			str += "<a href='javascript:void(0);'onclick='jump(1);'>首页</a>&nbsp;";
			str += "<a href='javascript:void(0);'onclick='jump("+previous()+");'>上一页</a>&nbsp;";
		}
		if (isLast() || rowsCount == 0)
			str += "下一页 尾页&nbsp;";
		else {
			str += "<a href='javascript:void(0);'onclick='jump("+next()+");'>下一页</a>&nbsp;";
			str += "<a href='javascript:void(0);'onclick='jump("+getPageCount()+");'>尾页</a>&nbsp;";
		}
		str += "&nbsp;共<b>" + rowsCount + "</b>条记录&nbsp;&nbsp;"
				+ currentPage + "/" + pageCount + "页&nbsp; ";
		str += "&nbsp;转到第<select name='page' onChange='jump(this.options[this.selectedIndex].value)'>";
		int begin = 1;
		int end=pageCount;
		for (int i = begin; i <= end; i++) {
			if (i == currentPage){
				str += "<option value='" +i+ "' selected>"
						+ i + "</option>";
			}
			else
			{
				str += "<option value='" +i+ "'>" + i
						+ "</option>";
			}
		}
		str += "</select>页";
		return str;
	}
	
	public String getOnClickPageBar(String count) {
		String str = "";
		if (isFirst())
			str += "首页 上一页&nbsp;";
		else {
			str += "<a href='javascript:void(0);'onclick='jump("+count+",1);'>首页</a>&nbsp;";
			str += "<a href='javascript:void(0);'onclick='jump("+count+","+previous()+");'>上一页</a>&nbsp;";
		}
		if (isLast() || rowsCount == 0)
			str += "下一页 尾页&nbsp;";
		else {
			str += "<a href='javascript:void(0);'onclick='jump("+count+","+next()+");'>下一页</a>&nbsp;";
			str += "<a href='javascript:void(0);'onclick='jump("+count+","+getPageCount()+");'>尾页</a>&nbsp;";
		}
		str += "&nbsp;共<b>" + rowsCount + "</b>条记录&nbsp;&nbsp;"
				+ currentPage + "/" + pageCount + "页&nbsp; ";
		str += "&nbsp;转到第<select name='page' onChange='jump("+count+",this.options[this.selectedIndex].value)'>";
		int begin = 1;
		int end=pageCount;
		for (int i = begin; i <= end; i++) {
			if (i == currentPage){
				str += "<option value='" +i+ "' selected>"
						+ i + "</option>";
			}
			else
			{
				str += "<option value='" +i+ "'>" + i
						+ "</option>";
			}
		}
		str += "</select>页";
		return str;
	}
}
