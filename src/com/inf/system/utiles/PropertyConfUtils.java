package com.inf.system.utiles;

import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 读取property文件工具
 * @author Gary.Zhao
 */
public class PropertyConfUtils {
	private final static String FILE_NAME = "config.properties";
	private static Properties property = null;

	private PropertyConfUtils() {
	}

	static {
		if (null == property) {
			property = new Properties();
		}
		try {
			InputStreamReader is = new InputStreamReader(PropertyConfUtils.class.getClassLoader().getResourceAsStream(FILE_NAME),"UTF-8");
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