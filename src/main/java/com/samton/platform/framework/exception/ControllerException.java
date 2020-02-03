package com.samton.platform.framework.exception;

import com.samton.platform.framework.exception.constant.ExpLayerConstant;

/**
 * 
 * @Description:异常信息框架Controller类
 * @author:     Alex
 * @date:        2017年2月22日 下午4:33:47
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class ControllerException extends BaseException{
	
	private static final long serialVersionUID = 1L;

	/**
	 * @param errorCode	前台显示异常代码
	 */
	public ControllerException(String errorCode) {
		super(errorCode);
	}
	
	/**
	 * @param errorCode 前台显示异常代码
	 * @param bgMsg		后台打印异常信息
	 */
	public ControllerException(String errorCode, String bgMsg) {
		super(errorCode, bgMsg);
	}
	
	/**
	 * @param errorCode		前台显示异常代码
	 * @param alertExpFlag	前台是否显示提示语
	 */
	public ControllerException(String errorCode, boolean alertExpFlag){
		super(errorCode,alertExpFlag);
	}
	
	/**
	 * 
	 * @param errorCode		前台显示异常代码
	 * @param bgMsg			后台打印异常信息
	 * @param alertExpFlag	前台是否显示提示语
	 */
	public ControllerException(String errorCode, String bgMsg, boolean alertExpFlag) {
		super(errorCode, bgMsg, alertExpFlag);
	}


	@Override
	public String getErrorLayer() {
		return ExpLayerConstant.ERROR_LAYER_CONTROLLER;
	}
}
