package com.samton.platform.framework.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * @Description:框架配置文件全局化类
 * @author: Alex
 * @date: 2017年2月23日 上午10:29:23
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class FkPropertiesUtil {

	private static Map<String, Properties> props = new HashMap<String, Properties>();

	public static String getProperty(String propKey, String key, String filePath) {
		Properties prop = props.get(propKey);
		if (prop == null) {
			prop = new Properties();
			try (InputStream in = new FileInputStream(ResourceUtil.getResourcePath() + filePath)) {
				prop.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
			props.put(propKey, prop);
		}
		return prop.getProperty(key);
	}

	public static String getFkProperty(String key) {
		return getProperty("fk", key, "/conf/framework/fk.properties");
	}

}
