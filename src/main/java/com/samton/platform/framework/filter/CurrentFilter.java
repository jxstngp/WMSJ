package com.samton.platform.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.samton.platform.framework.bean.UserCacheBean;
import com.samton.platform.framework.constant.WebConstant;
import com.samton.platform.framework.util.CurrentUtil;
import com.samton.platform.framework.util.NetworkUtil;

/**
 * 
 * @Description:给当前线程赋值，配合CurrentUtil使用
 * @author:     Alex
 * @date:        2017年2月22日 下午4:49:02
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class CurrentFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		UserCacheBean userCacheBean = (UserCacheBean) req.getSession().getAttribute(WebConstant.USER_SESSION);
		if (userCacheBean != null) {
			userCacheBean.setIp(NetworkUtil.getIpAddress(req));
			userCacheBean.setPort(req.getRemotePort());
			CurrentUtil.setCurrentUser(userCacheBean);
		}
		try {
			chain.doFilter(request, response);
		} finally {
			CurrentUtil.removeCurrentUser();
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}