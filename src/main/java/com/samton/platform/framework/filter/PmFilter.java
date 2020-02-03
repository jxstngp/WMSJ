package com.samton.platform.framework.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.samton.platform.framework.bean.UserCacheBean;
import com.samton.platform.framework.constant.WebConstant;
import com.samton.platform.framework.exception.FilterException;
import com.samton.platform.framework.util.CurrentUtil;
import com.samton.platform.pm.constant.PmExpCodeConstant;

/**
 * 
 * @Description:系统权限过滤器
 * @author:     shenchu
 * @date:        2017年2月22日 下午9:26:04
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class PmFilter implements Filter{
	//可放行url集合
	private Set<String> passPath;

	@Override
	//初始化过滤器执行内容
	public void init(FilterConfig filterConfig) throws ServletException {
		passPath=new HashSet<String>();
		//后台接口
		passPath.add("/platform/pm/login"+WebConstant.PAGE_SUFFIX);
		passPath.add("/platform/pm/getVaildImg"+WebConstant.PAGE_SUFFIX);//验证码
		//前台页面
		passPath.add("/resources/platform/views/login.jsp");
	}

	@Override
	//过滤内容1：检查url连接是否在可放行url集合中；2：不在可放行url集合中则判断当前session中是否有用户信息存在
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		String servletPath=req.getServletPath();
		//包含Set中的集合就放行
		if(passPath.contains(servletPath)){
			chain.doFilter(request, response);
			return;
		}
		
		UserCacheBean userCacheBean=CurrentUtil.getCurrentUser();
		try {
			if(userCacheBean==null){
				throw new FilterException(PmExpCodeConstant.PM_SESSION_EXPIRE,null);
			}
			chain.doFilter(request, response);
		} catch (FilterException e) {
			HttpServletResponse resp=(HttpServletResponse)response;
			//如果是ajax请求响应
			if (req.getHeader("x-requested-with") != null && req.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){  
				resp.setHeader("sessionstatus", "timeout");
				PrintWriter out = response.getWriter();
			    out.print("timeout");
		        //释放资源
		        out.flush();
		        out.close();
		        out = null;
				return ;  
	        }			
			resp.sendRedirect(req.getContextPath()+"/resources/platform/views/login.jsp");
			return;
		}
	}

	@Override
	public void destroy() {
		
	}

}