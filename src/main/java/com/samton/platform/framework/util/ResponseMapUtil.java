package com.samton.platform.framework.util;

import java.util.HashMap;
import java.util.Map;

import com.samton.platform.framework.mybatis.pagination.Pagination;

/**
 * 
 * @Description:构建返回map
 * @author:     shenchu
 * @date:        2017年8月7日 上午11:17:05
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class ResponseMapUtil {
	

	/**
	 * 
	 * @Title:        toPageMap 
	 * @Description:  构建jqgrid所需数据结构
	 * @param:        @param pagination
	 * @param:        @return    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年8月7日 上午11:18:42
	 */
	public  Map<String,Object> toPageMap(Pagination<?> pagination){
		Map<String,Object> retMap =new HashMap<String, Object>();
		if(pagination==null){
			retMap.put("records", 0);
			return retMap;
		}
		retMap.put("total",   pagination.getTotalPages());
		retMap.put("records", pagination.getTotalRows());
		retMap.put("rows",    pagination.getData());
		return retMap;
	}
	

}

