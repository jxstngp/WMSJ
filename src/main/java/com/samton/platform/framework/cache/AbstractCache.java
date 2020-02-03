package com.samton.platform.framework.cache;

/**
 * 
 * @Description:缓存扩展类
 * @author: Alex
 * @date: 2017年2月22日 下午1:51:00
 * Copyright (c) 2017, Samton. All rights reserved
 */
public abstract class AbstractCache implements ICache {

	// 缓存模块名称
	protected String basename;
	// 缓存时间
	protected int timeout;

	public String getBasename() {
		return basename;
	}

	public void setBasename(String basename) {
		this.basename = basename;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

}