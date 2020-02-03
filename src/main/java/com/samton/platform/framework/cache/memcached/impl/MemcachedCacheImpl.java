package com.samton.platform.framework.cache.memcached.impl;

import java.util.Date;

import com.samton.platform.framework.cache.AbstractCache;
import com.samton.platform.framework.cache.memcached.MemcachedContainer;
import com.samton.platform.framework.cache.memcached.MemcachedManager;
import com.whalin.MemCached.MemCachedClient;

/**
 * 
 * @Description:Memcached的缓存实体类
 * @author:     Alex
 * @date:        2017年2月22日 下午1:52:38
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class MemcachedCacheImpl extends AbstractCache {

	// 缓存单一实例
	private MemCachedClient mcc = MemcachedManager.getClient();

	@Override
	public boolean add(Object key, Object value) {
		// 判断是否开启缓存
		if (!MemcachedContainer.getInstance().isMemcachedSwitch()) {
			return false;
		}
		int exp = this.getTimeout();
		if (exp == 0) {
			return mcc.add(this.getBasename() + key, value);
		} else {
			// Date date = new Date(System.currentTimeMillis()+exp * 1000);
			Date date = new Date(exp * 1000);
			return mcc.add(this.getBasename() + key, value, date);
		}
	}

	@Override
	public Object get(Object key) {
		if (!MemcachedContainer.getInstance().isMemcachedSwitch()) {
			return null;
		}
		return this.get(key, false);
	}

	@Override
	public Object get(Object key, boolean refresh) {
		if (!MemcachedContainer.getInstance().isMemcachedSwitch()) {
			return null;
		}
		Object o = mcc.get(this.getBasename() + key);
		if (refresh && o != null) {
			this.set(key, o);
		}
		return o;
	}

	@Override
	public boolean remove(Object key) {
		if (!MemcachedContainer.getInstance().isMemcachedSwitch()) {
			return false;
		}
		return mcc.delete(this.getBasename() + key);
	}

	@Override
	public boolean set(Object key, Object value) {
		if (!MemcachedContainer.getInstance().isMemcachedSwitch()) {
			return false;
		}
		int exp = this.getTimeout();
		if (exp == 0) {
			return mcc.set(this.getBasename() + key, value);
		} else {
			// Date date = new Date(System.currentTimeMillis()+exp * 1000);
			Date date = new Date(exp * 1000);
			return mcc.set(this.getBasename() + key, value, date);
		}
	}

	@Override
	public boolean isAlive(String key) {
		if (!MemcachedContainer.getInstance().isMemcachedSwitch()) {
			return false;
		}
		return mcc.keyExists(this.getBasename() + key);
	}

	@Override
	public boolean flush() {
		if (!MemcachedContainer.getInstance().isMemcachedSwitch()) {
			return false;
		}
		return mcc.flushAll();
	}

}