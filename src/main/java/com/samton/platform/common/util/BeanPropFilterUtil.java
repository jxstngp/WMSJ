package com.samton.platform.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description:JAVABEAN对象中属性过滤帮助类
 * @author: shenchu
 * @date: 2017年2月22日 上午10:00:30 Copyright (c) 2016, Samton. All rights reserved
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class BeanPropFilterUtil {

	/**
	 * @Title:        convertListByPersistProps 
	 * @Description:  判断带泛型的集合，如果集合包含是map对象，则移除指定键集合所包含属性，
	 * 				      否则对属性为 PRIVATE和PROTECTED且不在指定键集合中的属性设置可访问，并且赋值null
	 * @param:        @param beans
	 * @param:        @param persistProps    
	 * @return:       void    
	 * @author        shenchu
	 * @Date          2017年2月22日 上午10:36:50
	 */
	public static <T> void convertListByPersistProps(List<T> beans, List<String> persistProps) {
		if (beans == null || beans.size() == 0)
			return;
		List<Field> setNullFields = new ArrayList<Field>();
		if (beans.get(0) instanceof Map) {
			for (T bean : beans) {
				convertMapByPersistProps((Map) bean, persistProps);
			}
			return;
		}
		Field[] fields = ReflectUtils.getAllField(beans.get(0).getClass());
		for (Field field : fields) {
			// getModifiers返回值：2-PRIVATE；4-PROTECTED
			if ((field.getModifiers() == 2 || field.getModifiers() == 4)&& !persistProps.contains(field.getName())) {
				// 如果取得的Field是private的，那么就要调用setAccessible(true)，否则会报IllegalAccessException
				field.setAccessible(true);
				setNullFields.add(field);
			}
		}
		for (T bean : beans) {
			for (Field setNullField : setNullFields) {
				try {
					//	对这个bean对象中的属性复null值
					setNullField.set(bean, null);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * @Title:        convertBeanByPersistProps 
	 * @Description:  判断JAVABEAN对象，如果是map对象，则移除指定键集合中的属性，
	 * 				      否则对属性为 PRIVATE和PROTECTED且不在指定键集合中的属性设置可访问，并且赋值null
	 * @param:        @param bean			需要处理的对象
	 * @param:        @param persistProps	指定键的集合    
	 * @return:       void    
	 * @author        shenchu
	 * @Date          2017年2月22日 上午10:51:12
	 */
	public static <T> void convertBeanByPersistProps(T bean, List<String> persistProps) {
		if (bean == null)
			return;
		if (bean instanceof Map) {
			convertMapByPersistProps((Map) bean, persistProps);
			return;
		}
		Field[] fields = ReflectUtils.getAllField(bean.getClass());
		for (Field field : fields) {
			if ((field.getModifiers() == 2 || field.getModifiers() == 4)&& !persistProps.contains(field.getName())) {
				field.setAccessible(true);
				try {
					field.set(bean, null);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @Title: convertMapByPersistProps
	 * @Description: 判断一个Map对象中是否含有指定的键，没有则删除。
	 * @param: @param map 需要处理的map对象
	 * @param: @param persistProps 需要的键的集合
	 * @return: void
	 * @author shenchu
	 * @Date 2017年2月22日 上午10:05:00
	 */
	private static void convertMapByPersistProps(Map<String, Object> map, List<String> persistProps) {
		if (map == null || map.size() == 0)
			return;
		Set<String> keys = map.keySet();
		Iterator<String> keyIterator = keys.iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			if (!persistProps.contains(key)) {
				keyIterator.remove();
			}
		}
	}

}