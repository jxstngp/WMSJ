package com.samton.platform.framework.cache;

/**
 * 
 * @Description:缓存方法接口类
 * @author:     Alex
 * @date:        2017年2月22日 下午1:40:38
 * Copyright (c) 2017, Samton. All rights reserved
 */
public interface ICache {
	
	/**
	 * 
	 * @Title:        add 
	 * @Description:  添加缓存
	 * @param:        @param key
	 * @param:        @param value
	 * @param:        @return    
	 * @return:       boolean    
	 * @author        Alex
	 * @Date          2017年2月22日 下午1:42:25
	 */
    boolean add(Object key, Object value);
    
    /**
     * 
     * @Title:        set 
     * @Description:  设置缓存
     * @param:        @param key
     * @param:        @param value
     * @param:        @return    
     * @return:       boolean    
     * @author        Alex
     * @Date          2017年2月22日 下午1:42:41
     */
    boolean set(Object key, Object value);
    
    /**
     * 
     * @Title:        remove 
     * @Description:  移除缓存
     * @param:        @param key
     * @param:        @return    
     * @return:       boolean    
     * @author        Alex
     * @Date          2017年2月22日 下午1:43:36
     */
    boolean remove(Object key);
    
    /**
     * 
     * @Title:        get 
     * @Description:  获得缓存值
     * @param:        @param key
     * @param:        @return    
     * @return:       Object    
     * @author        Alex
     * @Date          2017年2月22日 下午1:43:46
     */
    Object get(Object key);
    
    /**
     * 
     * @Title:        get 
     * @Description:  获得缓存值(是否刷新)
     * @param:        @param key
     * @param:        @param refresh	是否刷新之前缓存
     * @param:        @return    
     * @return:       Object    
     * @author        Alex
     * @Date          2017年2月22日 下午1:44:03
     */
    Object get(Object key,boolean refresh);
    
    /**
     * 
     * @Title:        isAlive 
     * @Description:  判断该缓存是否存在
     * @param:        @param key
     * @param:        @return    
     * @return:       boolean    
     * @author        Alex
     * @Date          2017年2月22日 下午1:45:00
     */
	boolean isAlive(String key);
	
	/**
	 * 
	 * @Title:        flush 
	 * @Description:  是否开启缓存
	 * @param:        @return    
	 * @return:       boolean    
	 * @author        Alex
	 * @Date          2017年2月22日 下午1:46:13
	 */
	boolean flush();
    
}