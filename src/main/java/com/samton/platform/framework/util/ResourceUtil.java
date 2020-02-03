package com.samton.platform.framework.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 
 * @Description:读取配置文件类
 * @author:     Alex
 * @date:        2017年2月23日 上午10:43:00
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class ResourceUtil {

	/**
	 * 
	 * @Title:        getResourcePath 
	 * @Description:  获得resource路径
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:43:43
	 */
	public static String getResourcePath() {
		String path = ResourceUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile();
		int position = path.indexOf("WEB-INF/");
		if (position >= 0) {
			path = path.substring(0, position);
			path += "WEB-INF/classes";
			try {
				return URLDecoder.decode(path, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 
	 * @Title:        getResourceConfPath 
	 * @Description:  获得conf文件路径
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:43:55
	 */
	public static String getResourceConfPath() {
		String confPath = ResourceUtil.getResourcePath();
		if (confPath == null)
			return null;
		return confPath + "/conf";
	}

}