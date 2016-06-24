package com.inf.system.utiles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Gson工具类
 * 封装方法返回值
 * @author 彭震宇 
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class GsonUtil {
	
	/**
	 * 转换为Json字符串
	 * 注：日期格式默认为长日期格式
	 * 如果需要短日期格式，
	 * 请调用带有日期格式参数的toJson方法
	 * @param list
	 * @return
	 */
	public static String toJson(List<Map> list){
		Gson gson = new GsonBuilder()
		.serializeNulls()
		.setDateFormat(Constant.FORMAT_DATE_TIME)
		.create();
		return gson.toJson(list);
	}
	
	/**
	 * 转换为Json字符串
	 * @param list
	 * @param dateFormat 
	 * 长日期格式：DateUtil.LONG_DATE_FORMAT
	 * 短日期格式：DateUtil.SHORT_DATE_FORMAT
	 * @return
	 */
	public static String toJson(List<Map> list, String dateFormat) {
		if (!Constant.FORMAT_DATE_TIME.equals(dateFormat) && !Constant.FORMAT_DATE.equals(dateFormat)) {
			return GsonUtil.toJson(list);
		} else {
			Gson gson = new GsonBuilder()
			.serializeNulls()
			.setDateFormat(dateFormat)
			.create();
			return gson.toJson(list);
		}
	}
	
	/**
	 * 转换为Json字符串
	 * 注：日期格式默认为长日期格式
	 * 如果需要短日期格式，
	 * 请调用带有日期格式参数的toJson方法，
	 * @param total
	 * @param list
	 * @return
	 */
	public static String toJson(String total, List list) {
		Gson gson = new GsonBuilder()
		.serializeNulls()
		.setDateFormat(Constant.FORMAT_DATE_TIME)
		.create();
		Map resultMap = new HashMap();
		resultMap.put("total", total);
		resultMap.put("rows", list);
		return gson.toJson(resultMap);
	}
	
	/**
	 * 转换为Json字符串
	 * 注：日期格式默认为长日期格式
	 * 如果需要短日期格式，
	 * 请调用带有日期格式参数的toJson方法，
	 * @param success
	 * @param msg
	 * @param total
	 * @param list
	 * @return
	 */
	public static String toJson(String success, String msg, String total, List list) {
		Gson gson = new GsonBuilder()
		.serializeNulls()
		.setDateFormat(Constant.FORMAT_DATE_TIME)
		.create();
		Map resultMap = new HashMap();
		resultMap.put("success", success);
		resultMap.put("msg", msg);
		resultMap.put("total", total);
		resultMap.put("rows", list);
		return gson.toJson(resultMap);
	}
	
	/**
	 * 转换为Json字符串
	 * @param total
	 * @param list
	 * @param dateFormat
	 * 长日期格式：DateUtil.LONG_DATE_FORMAT
	 * 短日期格式：DateUtil.SHORT_DATE_FORMAT
	 * @return
	 */
	public static String toJson(String total, List list, String dateFormat) {
		if (!Constant.FORMAT_DATE_TIME.equals(dateFormat) && !Constant.FORMAT_DATE.equals(dateFormat)) {
			return GsonUtil.toJson(total, list);
		} else {
			Gson gson = new GsonBuilder()
			.serializeNulls()
			.setDateFormat(dateFormat)
			.create();
			Map resultMap = new HashMap();
			resultMap.put("total", total);
			resultMap.put("rows", list);
			return gson.toJson(resultMap);
		}
	}
	
	/**
	 * 转换为Json字符串
	 * @param success
	 * @param msg
	 * @param total
	 * @param list
	 * @param dateFormat
	 * 长日期格式：DateUtil.LONG_DATE_FORMAT
	 * 短日期格式：DateUtil.SHORT_DATE_FORMAT
	 * @return
	 */
	public static String toJson(String success, String msg, String total, List list, String dateFormat) {
		if (!Constant.FORMAT_DATE_TIME.equals(dateFormat) && !Constant.FORMAT_DATE.equals(dateFormat)) {
			return GsonUtil.toJson(success, msg, total, list);
		} else {
			Gson gson = new GsonBuilder()
			.serializeNulls()
			.setDateFormat(dateFormat)
			.create();
			Map resultMap = new HashMap();
			resultMap.put("success", success);
			resultMap.put("msg", msg);
			resultMap.put("total", total);
			resultMap.put("rows", list);
			return gson.toJson(resultMap);
		}
	}
	
	/**
	 * 转换为Json字符串
	 * 注：日期格式默认为长日期格式
	 * 如果需要短日期格式，
	 * 请调用带有日期格式参数的toJson方法
	 * @param map
	 * @return
	 */
	public static String toJson(Map map){
		Gson gson = new GsonBuilder()
		.serializeNulls()
		.setDateFormat(Constant.FORMAT_DATE_TIME)
		.create();
		return gson.toJson(map);
	}
	
	/**
	 * 转换为Json字符串
	 * @param map
	 * @param dateFormat
	 * 长日期格式：DateUtil.LONG_DATE_FORMAT
	 * 短日期格式：DateUtil.SHORT_DATE_FORMAT
	 * @return
	 */
	public static String toJson(Map map, String dateFormat){
		if (!Constant.FORMAT_DATE_TIME.equals(dateFormat) && !Constant.FORMAT_DATE.equals(dateFormat)) {
			return GsonUtil.toJson(map);
		} else {
			Gson gson = new GsonBuilder()
			.serializeNulls()
			.setDateFormat(dateFormat)
			.create();
			return gson.toJson(map);
		}
	}
	
	/**
	 * 转换为Json字符串
	 * 注：日期格式默认为长日期格式
	 * @param success 
	 * 方法执行是否成功的标记 ：“true”表示成功；“false”表示失败
	 * @param msg
	 * @param list
	 * @return
	 */
	public static String toJson(String success, String msg, List list) {
		Gson gson = new GsonBuilder()
		.serializeNulls()
		.setDateFormat(Constant.FORMAT_DATE_TIME)
		.create();
		Map resultMap = new HashMap();
		resultMap.put("success", success);
		resultMap.put("msg", msg);
		//resultMap.put("data", GsonUtil.toJson(list, DateUtil.LONG_DATE_FORMAT));
		resultMap.put("data", list);
		return gson.toJson(resultMap);
	}
	
	/**
	 * 转换为Json字符串
	 * @param success
	 * 方法执行是否成功的标记 ：“true”表示成功；“false”表示失败
	 * @param msg
	 * @param list
	 * @param dateFormat
	 * 长日期格式：DateUtil.LONG_DATE_FORMAT
	 * 短日期格式：DateUtil.SHORT_DATE_FORMAT
	 * @return
	 */
	public static String toJson(String success, String msg, List list, String dateFormat) {
		if (!Constant.FORMAT_DATE_TIME.equals(dateFormat) && !Constant.FORMAT_DATE.equals(dateFormat)) {
			return GsonUtil.toJson(success, msg, list);
		} else {
			Gson gson = new GsonBuilder()
			.serializeNulls()
			.setDateFormat(dateFormat)
			.create();
			Map resultMap = new HashMap();
			resultMap.put("success", success);
			resultMap.put("msg", msg);
			//resultMap.put("data", GsonUtil.toJson(list, dateFormat));
			resultMap.put("data", list);
			return gson.toJson(resultMap);
		}
	}
	
	/**
	 * 转换为Json字符串
	 * 注：日期格式默认为长日期格式
	 * @param success 
	 * 方法执行是否成功的标记 ：“true”表示成功；“false”表示失败
	 * @param msg
	 * @param map
	 * @return
	 */
	public static String toJson(String success, String msg, Map map) {
		Gson gson = new GsonBuilder()
		.serializeNulls()
		.setDateFormat(Constant.FORMAT_DATE_TIME)
		.create();
		Map resultMap = new HashMap();
		resultMap.put("success", success);
		resultMap.put("msg", msg);
		//resultMap.put("data", GsonUtil.toJson(map, DateUtil.LONG_DATE_FORMAT));
		resultMap.put("data", map);
		return gson.toJson(resultMap);
	}
	
	/**
	 * 转换为Json字符串
	 * @param success
	 * 方法执行是否成功的标记 ：“true”表示成功；“false”表示失败
	 * @param msg
	 * @param map
	 * @param dateFormat
	 * 长日期格式：DateUtil.LONG_DATE_FORMAT
	 * 短日期格式：DateUtil.SHORT_DATE_FORMAT
	 * @return
	 */
	public static String toJson(String success, String msg, Map map, String dateFormat) {
		if (!Constant.FORMAT_DATE_TIME.equals(dateFormat) && !Constant.FORMAT_DATE.equals(dateFormat)) {
			return GsonUtil.toJson(success, msg, map);
		} else {
			Gson gson = new GsonBuilder()
			.serializeNulls()
			.setDateFormat(dateFormat)
			.create();
			Map resultMap = new HashMap();
			resultMap.put("success", success);
			resultMap.put("msg", msg);
			//resultMap.put("data", GsonUtil.toJson(map, dateFormat));
			resultMap.put("data", map);
			return gson.toJson(resultMap);
		}
	}
	
	/**
	 * 转换为Json字符串
	 * @param success 
	 * 方法执行是否成功的标记 ：“true”表示成功；“false”表示失败
	 * @param msg
	 * @return
	 */
	public static String toJson(String success, String msg) {
		Gson gson = new GsonBuilder()
		.serializeNulls()
		.create();
		Map resultMap = new HashMap();
		resultMap.put("success", success);
		resultMap.put("msg", msg);
		return gson.toJson(resultMap);
	}

}
