package com.inf.system.utiles.page;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class PageUtil {
	/**
	 * 分页前奏方法 逻辑计算,在调用dao层之前执行
	 * */
	@SuppressWarnings("unchecked")
	public static void buildPageBeforeDao(PageInfo page, String requestURI,
			int currentPage) throws UnsupportedEncodingException {
		// 组成上一页,下一页和跳页的url前缀部分
		if (requestURI != "" && requestURI != null) {
			page.setRequestUrl(buildUrl(requestURI, page.getParams()));
		}
		if (currentPage <= 0) {
			currentPage = 1;
		}

		int currentResult = (currentPage - 1) * page.getPageSize();

		page.setCurrentResult(currentResult);
	}

	/**
	 * 分页后续方法 逻辑计算,在调用dao层之后执行
	 * */
	public static void buildPageAfterDao(PageInfo page, int currentPage) {
		int totalCount = page.getTotalResult();
		
		int lastPage = 0;

		if (totalCount % page.getPageSize() == 0) {
			lastPage = totalCount / page.getPageSize();
		} else {
			lastPage = 1 + totalCount / page.getPageSize();
		}

		if (lastPage == 0) {
			lastPage = 1;
		}

		if (currentPage >= lastPage) {
			currentPage = lastPage;
		}

		page.setCurrentPage(currentPage);
		page.setTotalPage(lastPage);
	}

	/**
	 * 用来组成上一页,下一页的工具
	 * */
	public static String buildUrl(String headUrl, Map<String, String> params)
			throws UnsupportedEncodingException {
		StringBuilder urlBuilder = new StringBuilder(headUrl);
		urlBuilder.append("?");
		if (params != null && params.size() > 0) {

			for (String str : params.keySet()) {
				if (StringUtils.isNotEmpty(params.get(str))) {
					urlBuilder.append(str);
					urlBuilder.append("=");
					urlBuilder.append(URLEncoder.encode((params.get(str)),
							"UTF-8"));
					urlBuilder.append("&");
				}
			}
		}

		return urlBuilder.toString();
	}
}
