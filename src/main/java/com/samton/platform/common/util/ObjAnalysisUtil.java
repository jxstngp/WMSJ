package com.samton.platform.common.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @Description:对象分析工具类
 * @author:     shenchu
 * @date:        2017年2月22日 上午11:47:04
 * Copyright (c) 2016, Samton. All rights reserved
 */
public class ObjAnalysisUtil {

	/**
	 * @Title:        convertObjToUnderlineMap 
	 * @Description:  将一个对象中的属性名称驼峰转下划线 ，并封装至一个Map对象中。
	 * @param:        @param obj
	 * @param:        @return    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 上午11:41:15
	 */
	public static Map<String, Object> convertObjToUnderlineMap(Object obj) {
		Map<String, Object> reMap = new HashMap<String, Object>();
		if (obj == null)
			return null;
		Field[] fields = obj.getClass().getDeclaredFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				try {
					Field f = obj.getClass().getDeclaredField(fields[i].getName());
					f.setAccessible(true);
					Object o = f.get(obj);
					reMap.put(CamelUtil.camelToUnderline(fields[i].getName()), o);
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return reMap;
	}

}
