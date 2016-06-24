package com.inf.system.utiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * 获取接口数据
 * @author zongxin
 * 下午2:10:28
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class JsonBean {
	
	public static final String key = "infinitt";
	
	/**
	 * 获取解密后的字符串
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String getJsonString(String str) throws Exception{
		return DesEncrypt.getDecrypt(str,key);
	}
	/**
	 * 只有操作结果的Json
	 * @return
	 * @throws Exception 
	 */
	
	public static String toJson(String result,String desc) throws Exception{
		Map map = new HashMap();
		map.put("result", result);
		map.put("desc", desc);
		Gson gson = new Gson();
		String jsonString = gson.toJson(map);
		return DesEncrypt.getEncrypt(jsonString, key);
	}
	
	
	/**
	 * 返回有查询结果的Json
	 * @return
	 * @throws Exception 
	 */
	public static String toJson(List list, String pageTotal, String page, String rowsTotal, String desc) throws Exception{
		Map map = new HashMap();
		Gson gson = new GsonBuilder()
		.serializeNulls()
		.setDateFormat("yyyy-MM-dd HH:mm:ss")
		.create();
		map.put("result", Constant.DELETE_FALG_DELETED);
		map.put("pageTotal", pageTotal);
		map.put("page", page);
		map.put("rowsTotal", rowsTotal);
		map.put("data", list);
		map.put("desc", desc);
		String jsonString = gson.toJson(map);
		return DesEncrypt.getEncrypt(jsonString, key);
	}
	
	
	/**
	 * 返回一个list结果集
	 * @param str
	 * @return
	 * @throws Exception 
	 */
	public static String toJson(String result,List list,String desc) throws Exception{
		Gson gson = new GsonBuilder()
		.serializeNulls()
		.setDateFormat("yyyy-MM-dd HH:mm:ss")
		.create();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("data", list);
		map.put("desc", desc);
		
		String jsonString = gson.toJson(map);
		return DesEncrypt.getEncrypt(jsonString, key);
	}
	
	/**
	 * 返回一个Map结果集
	 * @param str
	 * @return
	 * @throws Exception 
	 */
	public static String toJson(String result,Map<?, ?> resultMap,String desc) throws Exception{
		Gson gson = new GsonBuilder()
		.serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss")
		.create();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<?, ?>> list = new ArrayList<Map<?, ?>>();
		list.add(resultMap);
		map.put("result", result);
		map.put("data", list);
		map.put("desc", desc);
		String jsonString = gson.toJson(map);
		return DesEncrypt.getEncrypt(jsonString, key);
	}
	
	/**
	 * 数据类型为实体的结果集
	 * @param result
	 * @param desc
	 * @return
	 * @throws Exception
	 */
	public static String toJson(String result,Object object,String desc) throws Exception{
		Gson gson = new GsonBuilder()
		.excludeFieldsWithoutExposeAnnotation()
		.create();
		List<Object> list = new ArrayList<Object>();
		list.add(object);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("data", list);
		map.put("desc", desc);
		String jsonString = gson.toJson(map);
		return DesEncrypt.getEncrypt(jsonString, key);
	}
	
	/**
	 * 将页面分页数据封装成Json格式
	 * @param total
	 * @param list
	 * @return
	 */
	public static String toJson(String total,List list){
		Gson gson = new GsonBuilder()
		.serializeNulls()
		.setDateFormat("yyyy-MM-dd HH:mm:ss")
		.create();
		Map resultMap = new HashMap();
		resultMap.put("total", total);
		resultMap.put("rows", list);
		return gson.toJson(resultMap);
	}
	
	public static String toJson(List<Map> list){
		Gson gson = new GsonBuilder()
		.serializeNulls()
		.setDateFormat("yyyy-MM-dd")
		.create();
		return gson.toJson(list);
	}
	
	public static String toJson(List<Map> list, String dateFormat) {
		if (dateFormat == null || dateFormat.isEmpty()) {
			Gson gson = new GsonBuilder()
			.serializeNulls()
			.create();
			return gson.toJson(list);
		} else {
			Gson gson = new GsonBuilder()
			.serializeNulls()
			.setDateFormat(dateFormat)
			.create();
			return gson.toJson(list);
		}
	}
	
	public static String toJson(Map map){
		Gson gson = new GsonBuilder()
		.serializeNulls()
		.setDateFormat("yyyy-MM-dd")
		.create();
		return gson.toJson(map);
	}
	
	public static String toJson(Map map, String dateFormat){
		Gson gson = new GsonBuilder()
		.serializeNulls()
		.setDateFormat(dateFormat)
		.create();
		return gson.toJson(map);
	}
	
	public static List<Map<String, String>> fromJson2ListMap(String json) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            Gson gson = new Gson();
            list = gson.fromJson(json,
                    new TypeToken<List<Map<String, String>>>() {
                    }.getType());
        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
	}
	
	public static String toJsonWithDataFormat(String total,List list, String dataFormat){
		Gson gson = new GsonBuilder()
		.serializeNulls()
		.setDateFormat(dataFormat)
		.create();
		Map resultMap = new HashMap();
		resultMap.put("total", total);
		resultMap.put("rows", list);
		return gson.toJson(resultMap);
	}
	
	public static String toJson1(Map map){
		Gson gson = new GsonBuilder()
		.serializeNulls()
		.setDateFormat("yyyy-MM-dd HH:mm:ss")
		.create();
		return gson.toJson(map);
	}
	
	public static String fromListToJson(List list){
		Gson gson = new GsonBuilder()
		.serializeNulls()
		.create();
		return gson.toJson(list);
	}
	
	public static String toJson(Object src){
		Gson gson = new Gson();
		return gson.toJson(src);
	}
	
	
	/** 
     * 根据json字符串返回Map对象 
     * @param json 
     * @return 
     */  
    public static Map<String,Object> toMap(String json){  
    	Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<Map<String, String>>() {}.getType()); 
    }  
}
