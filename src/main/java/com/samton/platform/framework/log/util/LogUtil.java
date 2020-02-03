package com.samton.platform.framework.log.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.samton.platform.framework.exception.BaseException;
import com.samton.platform.framework.util.FkPropertiesUtil;

/**
 * 
 * @Description:日志工具类
 * @author:     Alex
 * @date:        2017年2月22日 下午5:22:57
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class LogUtil {

	private static boolean isDebug = false;

	private static Logger baseDaoLog = null;
	private static Logger systemLog = null;
	private static Logger accessLog = null;

	private static final String VALUES_SEP_LEFT = "[";
	private static final String VALUES_SEP_RIGHT = "]";

	static {
		if ("true".equals(FkPropertiesUtil.getFkProperty("isDebug"))) {
			isDebug = true;
		}
		if (baseDaoLog == null) {
			baseDaoLog = Logger.getLogger("BaseDao");
		}
		if (systemLog == null) {
			systemLog = Logger.getLogger("System");
		}
		if (accessLog == null) {
			accessLog = Logger.getLogger("Access");
		}
	}

	private static void errorLog(Logger logger, String msg, Exception exp) {
		if (exp == null) {
			logger.error(msg);
		} else {
			if (exp instanceof BaseException) {
				BaseException baseExp = (BaseException) exp;
				if (StringUtils.isEmpty(baseExp.getBgMsg()))
					return;
			}
			logger.error("=================ERROR====================");
			if (StringUtils.isEmpty(msg)) {
				msg = exp.getMessage();
			}
			logger.error(msg, exp);
			StringWriter stringWriter = new StringWriter();
			PrintWriter writer = new PrintWriter(stringWriter);
			exp.printStackTrace(writer);
			StringBuffer buffer = stringWriter.getBuffer();
			logger.error(buffer.toString());
			logger.error("==========================================");
		}
	}

	private static void debugLog(Logger logger, String msg) {
		logger.debug(msg);
		if (isDebug) {
			System.out.println(msg);
		}
	}

	private static void infoLog(Logger logger, String msg) {
		logger.info(msg);
		if (isDebug) {
			System.out.println(msg);
		}
	}

	private static void warnLog(Logger logger, String msg) {
		logger.warn(msg);
		if (isDebug) {
			System.out.println(msg);
		}
	}

	/**
	 * 
	 * @Title:        debugBaseDaoLog 
	 * @Description:  debug基本日志
	 * @param:        @param msg    
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年2月22日 下午5:29:22
	 */
	public static void debugBaseDaoLog(String msg) {
		debugLog(baseDaoLog, msg);
	}

	/**
	 * 
	 * @Title:        infoBaseDaoLog 
	 * @Description:  信息基本日志
	 * @param:        @param msg    
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年2月22日 下午5:29:46
	 */
	public static void infoBaseDaoLog(String msg) {
		infoLog(baseDaoLog, msg);
	}

	/**
	 * 
	 * @Title:        infoAccessLog 
	 * @Description:  TODO(这里用一句话描述这个方法的作用) 
	 * @param:        @param params    
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年2月22日 下午7:25:43
	 */
	public static void infoAccessLog(List<String> params) {
		StringBuffer sb = new StringBuffer();
		for (String param : params) {
			sb.append(VALUES_SEP_LEFT + (param == null ? "" : param) + VALUES_SEP_RIGHT);
		}
		infoLog(accessLog, sb.toString());
	}

	/**
	 * 
	 * @Title:        warnBaseDaoLog 
	 * @Description:  警告日志
	 * @param:        @param msg    
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年2月22日 下午5:30:09
	 */
	public static void warnBaseDaoLog(String msg) {
		warnLog(baseDaoLog, msg);
	}

	/**
	 * 
	 * @Title:        errorBaseDaoLog 
	 * @Description:  异常日志(异常信息)
	 * @param:        @param msg    
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年2月22日 下午5:30:25
	 */
	public static void errorBaseDaoLog(String msg) {
		errorBaseDaoLog(msg, null);
	}

	/**
	 * 
	 * @Title:        errorBaseDaoLog 
	 * @Description:  异常日志(异常信息和错误信息)
	 * @param:        @param msg
	 * @param:        @param t    
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年2月22日 下午7:26:26
	 */
	public static void errorBaseDaoLog(String msg, Exception t) {
		errorLog(baseDaoLog, msg, t);
	}

	/**
	 * 
	 * @Title:        debugSystemLog 
	 * @Description:  debug系统日志
	 * @param:        @param msg    
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年2月22日 下午7:29:46
	 */
	public static void debugSystemLog(String msg) {
		debugLog(systemLog, msg);
	}

	/**
	 * 
	 * @Title:        infoSystemLog 
	 * @Description:  系统信息日志
	 * @param:        @param msg    
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年2月22日 下午7:30:01
	 */
	public static void infoSystemLog(String msg) {
		infoLog(systemLog, msg);
	}

	/**
	 * 
	 * @Title:        warnSystemLog 
	 * @Description:  警告系统日志
	 * @param:        @param msg    
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年2月22日 下午7:30:35
	 */
	public static void warnSystemLog(String msg) {
		warnLog(systemLog, msg);
	}

	/**
	 * 
	 * @Title:        errorSystemLog 
	 * @Description:  异常系统日志(异常信息)
	 * @param:        @param msg    
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年2月22日 下午7:30:50
	 */
	public static void errorSystemLog(String msg) {
		errorSystemLog(msg, null);
	}

	/**
	 * 
	 * @Title:        errorSystemLog 
	 * @Description:  异常系统日志(异常信息和错误信息)
	 * @param:        @param msg
	 * @param:        @param t    
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年2月22日 下午7:31:49
	 */
	public static void errorSystemLog(String msg, Exception t) {
		errorLog(systemLog, msg, t);
	}

}