package com.samton.platform.framework.cache.memcached;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**
 * 
 * @Description:Memcached管理类
 * @author:     Alex
 * @date:        2017年2月22日 下午2:53:41
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class MemcachedManager {

    private static MemCachedClient mcc;

    // 设置与缓存服务器的连接池
    static {
        initMemcached();
    }
    
    public static MemCachedClient getClient(){
    	return mcc;
    }

    /**
     * 
     * @Title:        initMemcached 
     * @Description:  Memcached初始化
     * @param:            
     * @return:       void    
     * @author        Alex
     * @Date          2017年2月22日 下午2:55:39
     */
    public static void initMemcached() {
        // 服务器列表和其权重
    	MemcachedContainer memcachedContainer=MemcachedContainer.getInstance();
    	if(!memcachedContainer.isMemcachedSwitch()) return;
    	
        Map<String, Integer> serverMap=memcachedContainer.getServerMap();
        
        Set<Entry<String, Integer>> serverEntries=serverMap.entrySet();

        int size = serverEntries.size();
        
        String[] servers=new String[size];
        
        Integer[] weights=new Integer[size];
        
        int i = 0;
        
        for (Entry<String, Integer> entry : serverEntries) {
            servers[i]=entry.getKey();
            weights[i++]=entry.getValue();
        }
        
        // 获取socke连接池的实例对象
        SockIOPool pool = SockIOPool.getInstance();

        // 设置服务器信息
        pool.setServers(servers);

        pool.setWeights(weights);

        // 设置初始连接数、最小和最大连接数以及最大处理时间
        pool.setInitConn(5);

        pool.setMinConn(5);

        pool.setMaxConn(4000);

        pool.setMaxIdle(1000 * 60 * 60 * 6);

        // 设置主线程的睡眠时间
        pool.setMaintSleep(30);

        // 设置TCP的参数，连接超时等
        pool.setNagle(false);
        
        pool.setAliveCheck(false);
        
        pool.setFailover(false);

        pool.setSocketTO(3000);

        pool.setSocketConnectTO(0);

        pool.setHashingAlg(SockIOPool.NEW_COMPAT_HASH);

//        pool.setBufferSize(600000000);

        // 初始化连接池

        pool.initialize();

        // 压缩设置，超过指定大小（单位为K）的数据都会被压缩

//        mcc.setCompressEnable( true );
//        mcc.setCompressThreshold( 256 * 1024 );
        
        mcc = new MemCachedClient();
    }

}
