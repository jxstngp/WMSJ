package com.samton.platform.framework.request.aop;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.samton.platform.framework.cache.ICache;
import com.samton.platform.framework.context.WebContext;
import com.samton.platform.framework.exception.FilterException;
import com.samton.platform.framework.exception.constant.ExpCodeConstant;
import com.samton.platform.framework.log.util.LogUtil;
import com.samton.platform.framework.util.MD5Util;

/**
 * 
 * @Description:重复提交控制类
 * @author:     Alex
 * @date:        2017年2月22日 下午8:09:15
 * Copyright (c) 2017, Samton. All rights reserved
 */
@Component
public class ResubmitCheckAop {

	@Resource
	private ICache resubmitCache;

	public Object resubmitIntercept(ProceedingJoinPoint pjp) throws Throwable {
		// @ResubmitCheck(checkFlag=false)的不控制重复提交
		ResubmitCheck classResubmitCheck = pjp.getTarget().getClass().getAnnotation(ResubmitCheck.class);
		if (classResubmitCheck != null && !classResubmitCheck.checkFlag()) {
			return pjp.proceed();
		}
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		ResubmitCheck methodResubmitCheck = (ResubmitCheck) signature.getMethod().getAnnotation(ResubmitCheck.class);
		if (methodResubmitCheck != null && !methodResubmitCheck.checkFlag()) {
			return pjp.proceed();
		}

		HttpServletRequest req = WebContext.getInstance().getRequest();
		String servletPath = req.getServletPath();
		String interfaceName = servletPath.substring(servletPath.lastIndexOf("/") + 1);
		if (interfaceName.matches("^(get|find|select|query|search|count).*")) {
			return pjp.proceed();
		}
		int index = servletPath.indexOf(".");
		if (index != -1) {
			servletPath = servletPath.substring(0, index);
		}
		String sessionId = req.getSession().getId();
		String paramsMd5 = MD5Util.getMD5String(JSONObject.fromObject(req.getParameterMap()).toString());
		String key = servletPath + "_" + sessionId + "_" + paramsMd5;
		// try {
		boolean flag = resubmitCache.add(key, 1);
		if (!flag) {
			LogUtil.errorSystemLog("Resubmit URL：" + servletPath);
			throw new FilterException(ExpCodeConstant.REQUEST_RESUBMIT_ERROR,
					null);
		}
		return pjp.proceed();
		// } finally {
		// resubmitCache.remove(key);
		// }
	}

}