package com.samton.platform.framework.constant;

/**
 * 
 * @Description:WebConstant(web系统环境相关常量)
 * @author:     Alex
 * @date:        2017年2月22日 下午2:57:19
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class WebConstant {
	
	/** 当前登入用户session id */
	public static final String USER_SESSION = "us_";
	
	/** 当前登入用户权限MenuCode集合*/
	public static final String AUTH_CODE_SET = "acs_";
	
	/** 访问路径url页面后缀*/
	public static final String  PAGE_SUFFIX = ".do";
	
	/** 访问路径url功能后缀*/
	public static final String  BUSINESS_SUFFIX = ".json";
	
	/** 访问路径url无所权限后缀*/
	public static final String  NO_AUTH_SUFFIX = ".exjson";
	
}