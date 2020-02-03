package com.samton.platform.framework.cache.memcached.thread;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimerTask;

import com.samton.platform.framework.cache.memcached.MemcachedContainer;
import com.samton.platform.framework.cache.memcached.MemcachedManager;
import com.samton.platform.framework.log.util.LogUtil;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**
 * 
 * @Description:Mamcached缓存定时器初始化类
 * @author:     Alex
 * @date:        2017年2月22日 下午2:09:49
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class InitMemcachedConnectionTimerTask extends TimerTask {

	private final static String CHECK_POOL_NAME = "checkPool";

	@Override
	public void run() {
		LogUtil.infoSystemLog("InitMemcachedConnectionTimerTask start.... " + new Date());
		Map<String, Integer> allServerMap = MemcachedContainer.getInstance().getAllServerMap();
		// 设置服务器信息
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (Entry<String, Integer> entry : allServerMap.entrySet()) {
			String server = entry.getKey();
			Integer weight = entry.getValue();
			if (checkNodeStatus(server, weight)) {
				map.put(server, weight);
			}
		}
		// 判断缓存是否正在使用中，刷新缓存
		if (map.size() > 0 && map.size() != MemcachedContainer.getInstance().getServerMap().size()) {
			Set<Entry<String, Integer>> entrys = map.entrySet();
			Iterator<Entry<String, Integer>> iterator = entrys.iterator();
			String[] newServers = new String[map.size()];
			Integer[] newWeights = new Integer[map.size()];
			int j = 0;
			while (iterator.hasNext()) {
				Entry<String, Integer> entry = iterator.next();
				newServers[j] = entry.getKey();
				newWeights[j++] = entry.getValue();
			}
			MemcachedContainer.getInstance().setServerMap(map);
			MemcachedManager.initMemcached();
			LogUtil.infoSystemLog("InitMemcachedConnectionTimerTask have change!!!!");
			LogUtil.infoSystemLog("IP:");
			for (String newServer : newServers) {
				LogUtil.infoSystemLog(newServer);
			}
		}
		LogUtil.infoSystemLog("InitMemcachedConnectionTimerTask over.... " + new Date());
	}

	/**
	 * 
	 * @Title:        checkNodeStatus 
	 * @Description:  校验Memcached是否可以连接
	 * @param:        @param server		memcached服务
	 * @param:        @param weight		连接数
	 * @param:        @return    
	 * @return:       boolean    
	 * @author        Alex
	 * @Date          2017年2月22日 下午2:34:09
	 */
	private boolean checkNodeStatus(String server, int weight) {
		SockIOPool pool = SockIOPool.getInstance(CHECK_POOL_NAME);
		pool.setServers(new String[] { server });
		pool.setWeights(new Integer[] { weight });
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(4000);
		pool.setMaxIdle(1000 * 60 * 60 * 6);
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setAliveCheck(false);
		pool.setFailover(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);
		pool.setHashingAlg(SockIOPool.NEW_COMPAT_HASH);
		pool.initialize();
		MemCachedClient memCachedClient = new MemCachedClient(CHECK_POOL_NAME);
		memCachedClient.set("isConnected", true, new Date(3000));
		Object o = memCachedClient.get("isConnected");
		if (o != null && (Boolean) o) {
			return true;
		}
		return false;
	}

}
