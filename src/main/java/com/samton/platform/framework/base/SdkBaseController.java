package com.samton.platform.framework.base;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 
 * @Description:SdkBaseController类
 * @author: Alex
 * @date: 2017年2月22日 上午11:59:12
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class SdkBaseController extends BaseController {

	private static final String PLAT_TYPE = "platType";
	private static final String SDK_VERSION = "sdkVersion";

	// 移动端必传参数sdkVersion，用于之后做程序兼容，如果为null则为web端
	protected Integer getSdkVersion() {
		String sdkVersion = this.getRequest().getParameter(SDK_VERSION);
		if (StringUtils.isEmpty(sdkVersion)) {
			return null;
		}
		return Integer.parseInt(sdkVersion);
	}

	// PC端为1，安卓端为2，IOS端为3
	protected Integer getPlatType() {
		String platType = this.getRequest().getParameter(PLAT_TYPE);
		if (StringUtils.isEmpty(platType)) {
			return 1;
		}
		return Integer.parseInt(platType);
	}

	/**
	 * 表单提交 Date类型数据绑定 <功能详细描述>
	 * 
	 * @param binder
	 * @see [类、类#方法、类#成员]
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}
