package com.inf.system.utiles;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * 关于集合的操作
 */
public class CollecUtils {
	
	/**
	 * 判断Map中Key是否存在
	 * @param map
	 * @param key
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean keyIsNull(Map map,String key){
		Iterator keys = map.keySet().iterator();
		while(keys.hasNext()){
			if(key.equals((String)keys.next())){
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, String> getRequestMap(HttpServletRequest request) {
		Map<String, String> resultMap = new HashMap<String, String>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement().trim();
			resultMap.put(name, request.getParameter(name).trim());
		}
		return resultMap;
	}
	
	
	//list<String> 转String
	public static String listTranString(List<String> lists){
		String strs="";
		for(int i = 0; i < lists.size(); i++) {
			if(StringUtils.isNotBlank(lists.get(i))){
				strs+=lists.get(i)+",";
			}
		}
		return strs.substring(0, strs.length()-1);
	}
	
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		CollecUtils cn= new CollecUtils();
		List<String> lists = new ArrayList<String>();
		lists.add("qwqe");
		lists.add("21313123");
		lists.add("iojlknk");
		System.out.println(cn.listTranString(lists));
	}
}
