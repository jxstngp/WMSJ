package com.samton.platform.framework.exception;

import com.samton.platform.framework.exception.constant.ExpLayerConstant;

/**
 * 
 * @Description:service 层的异常信息
 * @author:     Alex
 * @date:        2017年2月22日 下午4:47:46
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class UndefinedException extends BaseException {
	
	private static final long serialVersionUID = 1L;

	public UndefinedException(Throwable cause, String errorCode,String showCode) {
		super(cause, errorCode, true, showCode);
	}

	@Override
	public String getErrorLayer() {
		return ExpLayerConstant.ERROR_UNDEFINED;
	}

}