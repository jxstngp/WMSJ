package com.samton.platform.framework.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @Description:非法字符过滤器
 * @author:     Alex
 * @date:        2017年2月22日 下午4:54:18
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class IllegalCharacterFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 设置不需要过滤的路径
		Set<String> passPath = new HashSet<String>(0);
		passPath.add("/api/notice/addNotice");
		passPath.add("/api/notice/editNotice");
		//后台接口
		passPath.add("/platform/pm/login");
		passPath.add("/platform/pm/getVaildImg");//验证码
		//前台页面
		passPath.add("/resources/platform/views/login");
		// 获得当前路径
		HttpServletRequest req=(HttpServletRequest)request;
		String servletPath=req.getServletPath();
		
		int index=servletPath.indexOf(".");
		if(index!=-1){			
			servletPath=servletPath.substring(0,index);
		}
		
		if(passPath.contains(servletPath)){
			chain.doFilter(request, response);
			return;
		}
		request = new Request((HttpServletRequest) request);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}

class Request extends HttpServletRequestWrapper {
	public Request(HttpServletRequest request) {
		super(request);
	}

	private String escapeValue(String value) {
		// return StringEscapeUtils.escapeHtml(super.getParameter(name));
		if (StringUtils.isEmpty(value))
			return value;
		return value.replaceAll("<", "&lt;").replaceAll(">", "&gt;").trim();
	}

	// @Override
	// public String getParameter(String name) {
	// return escapeValue(super.getParameter(name));
	// }

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if (values == null)
			return values;
		for (int j = 0; j < values.length; j++) {
			values[j] = escapeValue(values[j]);
		}
		return values;
	}
}