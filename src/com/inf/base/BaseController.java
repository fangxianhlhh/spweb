package com.inf.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inf.system.utiles.Constant;
import com.inf.system.utiles.PropertyUtils;

/**
 * 顶层Action 
 * @author zongxin
 */
@SuppressWarnings("rawtypes")
public class BaseController extends MultiActionController {

	
	
	public static Map map = new HashMap();
	public static List list = new ArrayList();
	private static Logger logger = Logger.getLogger(BaseController.class.getName());

	/**
	 * 获取登录用户Map信息
	 * 
	 * @param request
	 * @return
	 */
	public Map getCurrentUser(HttpServletRequest request) {
		return (Map) request.getSession().getAttribute(Constant.USER_SESSION);
	}

	/**
	 * 获取页面封装好的Map
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map getMap(HttpServletRequest request) {
		String key = request.getParameter("key");
		if (StringUtils.isEmpty(key)) {
			return null;
		}
		String[] params = key.split(",");
		int length = params.length;
		Map map = new HashMap(length);
		for (int i = 0; i < length; i++) {
			String str = "";
			if (request.getParameter(params[i]) != null) {
				try {
					str = new String(request.getParameter(params[i]).trim().getBytes("ISO8859_1"), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
				str = request.getParameter(params[i].trim());
				map.put(params[i], str.trim());
			}
		}
		return map;
	}

	/**
	 * 获取页面封装好的参数数组
	 * 
	 * @param request
	 * @param customKey
	 * @return
	 */
	public Object[] getArray(HttpServletRequest request, String customKey) {
		String key = request.getParameter(customKey);
		if (StringUtils.isEmpty(key)) {
			return null;
		}
		String[] params = key.split(",");
		int length = params.length;
		Object array[] = new Object[length];

		for (int i = 0; i < length; i++) {
			String str = "";
			if (request.getParameter(params[i]) != null) {
				try {
					str = new String(request.getParameter(params[i]).trim()
							.getBytes("ISO8859_1"), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
				str = request.getParameter(params[i].trim());
				array[i] = str;
			}
		}
		return array;
	}

	/**
	 * 使用GSON将List、Map、Object 转换成要输出的字符串
	 * 
	 * @param list
	 * @return
	 */
	public String getJsonResult(Object obj) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.serializeNulls();
		Gson gson = gsonBuilder.create();
		return gson.toJson(obj);
	}

	public void outJson(HttpServletResponse response, String jsonResult) {
		logger.debug(jsonResult);
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(jsonResult);
		out.flush();
		out.close();
	}

	/**
	 * 获取默认的分页数量
	 * 
	 * @return
	 */
	public int getDefaultviewsize() {
		return Integer.parseInt(PropertyUtils.getProperty("viewsize"));
	}

	/**
	 * 获取默认的分页格式
	 * 
	 * @return
	 */
	public String getDefaultPageFormat() {
		return PropertyUtils.getProperty("pageFormat");
	}

}
