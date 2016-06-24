package com.inf.system.utiles;

import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 读取property文件工具
 * @author Gary.Zhao
 */
public class PropertyUtils {
	private final static String FILE_NAME = "applicationContext.properties";
	private static Properties property = null;

	private PropertyUtils() {
	}

	static {
		if (null == property) {
			property = new Properties();
		}
		try {
			InputStreamReader is = new InputStreamReader(PropertyUtils.class.getClassLoader().getResourceAsStream(FILE_NAME),"UTF-8");
			property.load(is);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return property.getProperty(key);
	}

	
}