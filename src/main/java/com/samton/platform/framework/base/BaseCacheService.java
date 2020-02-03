package com.samton.platform.framework.base;

/**
 * 
 * @Description:缓存工具类
 * @author:     Alex
 * @date:        2017年2月22日 上午10:35:36
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class BaseCacheService {
	
	/**
	 * 
	 * @Title:        getCacheKey 
	 * @Description:  返回缓存key
	 * @param:        @param constant	
	 * @param:        @param key		只能为基础类型，通常用对象的主键，绝对不可把对象往里面丢
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月22日 上午10:36:02
	 */
	protected String getCacheKey(String constant,Object key){
		return constant+key;
	}

}
