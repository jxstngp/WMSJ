package com.samton.platform.framework.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Description:网络工具类
 * @author: Alex
 * @date: 2017年2月23日 上午10:36:35
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class NetworkUtil {

	private static Logger logger = LoggerFactory.getLogger(NetworkUtil.class);

	/**
	 * 
	 * @Title: getIpAddress
	 * @Description: 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
	 * @param: @param request
	 * @param: @return
	 * @return: String
	 * @author Alex
	 * @Date 2017年2月23日 上午10:37:29
	 */
	public static String getIpAddress(HttpServletRequest request) {
		// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

		// String ip = request.getHeader("X-Forwarded-For");
		// printLoggerMsg("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip="
		// + ip);
		//
		// if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		// {
		// if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		// {
		// ip = request.getHeader("Proxy-Client-IP");
		// printLoggerMsg("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip="
		// + ip);
		// }
		// if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		// {
		// ip = request.getHeader("WL-Proxy-Client-IP");
		// printLoggerMsg("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip="
		// + ip);
		// }
		// if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		// {
		// ip = request.getHeader("HTTP_CLIENT_IP");
		// printLoggerMsg("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip="
		// + ip);
		// }
		// if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		// {
		// ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		// printLoggerMsg("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip="
		// + ip);
		// }
		// if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		// {
		// ip = request.getRemoteAddr();
		// printLoggerMsg("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip="
		// + ip);
		// }
		// } else if (ip.length() > 15) {
		// String[] ips = ip.split(",");
		// for (int index = 0; index < ips.length; index++) {
		// String strIp = (String) ips[index];
		// if (!("unknown".equalsIgnoreCase(strIp))) {
		// ip = strIp;
		// break;
		// }
		// }
		// }
		// return ip;
		String fromSource = "X-Real-IP";
		String ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
			fromSource = "X-Forwarded-For";
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			fromSource = "Proxy-Client-IP";
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			fromSource = "WL-Proxy-Client-IP";
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			fromSource = "request.getRemoteAddr";
		}
		printLoggerMsg("App Client IP: " + ip + ", fromSource: " + fromSource);
		return ip;
	}

	private static void printLoggerMsg(String msg) {
		if (logger.isDebugEnabled()) {
			logger.debug(msg);
		}
	}
}
