package com.samton.platform.framework.log.aop;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.samton.platform.framework.bean.UserCacheBean;
import com.samton.platform.framework.context.WebContext;
import com.samton.platform.framework.log.util.LogUtil;
import com.samton.platform.framework.util.CurrentUtil;
import com.samton.platform.framework.util.DateUtil;
import com.samton.platform.framework.util.NetworkUtil;

/**
 * 
 * @Description:日志AOP类
 * @author:     Alex
 * @date:        2017年2月22日 下午5:19:11
 * Copyright (c) 2017, Samton. All rights reserved
 */
@Component
public class AccessLogAop {

	private String getDefaultValue(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}

	public Object recordLog(ProceedingJoinPoint pjp) throws Throwable {
		Object response = null;
		String flag = "1";
		try {
			response = pjp.proceed();
		} catch (Throwable e) {
			flag = "0";
			throw e;
		} finally {
			MethodSignature signature = (MethodSignature) pjp.getSignature();
			AccessLog accessLog = (AccessLog) signature.getMethod().getAnnotation(AccessLog.class);
			// @AccessLog(recordFlag=false)的不打印日志
			if (accessLog != null && !accessLog.recordFlag()) {
				return response;
			}
			// 未登录用户(ip,访问url,访问参数,访问时间,访问结果(成功/失败))
			// 登录用户(ip,访问url,访问参数,访问时间,访问结果(成功/失败),企业id,用户id,用户名)
			HttpServletRequest req = WebContext.getInstance().getRequest();
			String servletPath = req.getServletPath();
			int index = servletPath.indexOf(".");
			if (index != -1) {
				servletPath = servletPath.substring(0, index);
			}
			List<String> params = new ArrayList<String>();
			params.add(getDefaultValue(NetworkUtil.getIpAddress(req)));
			params.add(getDefaultValue(servletPath));
			String paramValue = "";
			if (req.getParameterMap() != null) {
				paramValue = JSONObject.fromObject(req.getParameterMap()).toString();
			}
			params.add(paramValue);
			params.add(DateUtil.currentDatetime());
			params.add(flag);
			UserCacheBean userCacheBean = CurrentUtil.getCurrentUser();
			if (userCacheBean != null) {
				params.add(getDefaultValue(userCacheBean.getEnterpriseId()));
				params.add(getDefaultValue(userCacheBean.getUserId()));
				params.add(getDefaultValue(userCacheBean.getUserName()));
			}
			LogUtil.infoAccessLog(params);
		}
		return response;
	}

}