package com.samton.platform.framework.util;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * @Description:公用类
 * @author: shenchu
 * @date: 2017年1月18日 下午4:29:19
 * Copyright (c) 2016, Samton. All rights reserved
 */
@SuppressWarnings({"rawtypes"})
public class Public {
	
	/**
	 * 
	 * @Title:        getParamValue 
	 * @Description:  得到系统参数表中对应的值
	 * @param:        @param param_name
	 * @param:        @param servletContext
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:38:22
	 */
	public static String getParamValue(String param_name, ServletContext servletContext) {
		JdbcTemplate jdbc = (JdbcTemplate) WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean("jdbcTemplate");
		List list = jdbc.queryForList("SELECT param_value FROM t_sys_param WHERE state = 1 AND param_name = ? ", param_name);
		if (list != null && list.size() > 0) {
			Map map = (Map) list.get(0);
			return map.get("param_value").toString();
		}
		return null;
	}
}
