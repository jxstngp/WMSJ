package com.samton.platform.framework.context;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * 
 * @Description:Web容器过滤器
 * @author:     Alex
 * @date:        2017年2月22日 下午3:06:39
 * Copyright (c) 2017, Samton. All rights reserved
 */
@Component("webContextFilter")
public class WebContextFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		ServletContext servletContext = request.getSession().getServletContext();
		WebContext.create(request, response, servletContext);
		chain.doFilter(request, response);
		WebContext.clear();
	}

	@Override
	public void destroy() {

	}

}