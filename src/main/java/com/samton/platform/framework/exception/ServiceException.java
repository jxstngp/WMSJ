package com.samton.platform.framework.exception;

import com.samton.platform.framework.exception.constant.ExpLayerConstant;

/**
 * 
 * @Description:service 层的异常信息
 * @author:     Alex
 * @date:        2017年2月22日 下午4:44:06
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class ServiceException extends BaseException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @param errorCode	前台显示异常代码
	 */
	public ServiceException(String errorCode) {
		super(errorCode);
	}
	
	/**
	 * @param errorCode 前台显示异常代码
	 * @param bgMsg		后台打印异常信息
	 */
	public ServiceException(String errorCode, String bgMsg) {
		super(errorCode , bgMsg);
	}
	
	/**
	 * @param errorCode		前台显示异常代码
	 * @param alertExpFlag	前台是否显示提示语
	 */
	public ServiceException(String errorCode, boolean alertExpFlag){
		super(errorCode,alertExpFlag);
	}
	
	/**
	 * 
	 * @param errorCode		前台显示异常代码
	 * @param bgMsg			后台打印异常信息
	 * @param alertExpFlag	前台是否显示提示语
	 */
	public ServiceException(String errorCode, String bgMsg, boolean alertExpFlag) {
		super(errorCode, bgMsg, alertExpFlag);
	}

	@Override
	public String getErrorLayer() {
		return ExpLayerConstant.ERROR_LAYER_SERVICE;
	}

}