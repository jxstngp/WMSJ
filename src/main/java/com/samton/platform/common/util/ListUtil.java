package com.samton.platform.common.util;

import java.util.List;

/**
 * 
 * @Description:List判断工具类
 * @author:     Alex
 * @date:        2017年2月21日 下午4:50:48
 * Copyright (c) 2016, Samton. All rights reserved
 */
public class ListUtil {
	
	/**
	 * 
	 * @Title:        isEmpty 
	 * @Description:  判断List是否为空
	 * @param:        @param list
	 * @param:        @return    
	 * @return:       boolean    
	 * @author        Alex
	 * @Date          2017年2月21日 下午4:51:10
	 */
	public static<T> boolean isEmpty(List<T> list){
		return list==null||list.size()==0;
	}
	
	/**
	 * 
	 * @Title:        isNotEmpty 
	 * @Description:  判断List是否为空
	 * @param:        @param list
	 * @param:        @return    
	 * @return:       boolean    
	 * @author        Alex
	 * @Date          2017年2月21日 下午4:51:55
	 */
	public static<T> boolean isNotEmpty(List<T> list){
		return !isEmpty(list);
	}

}
