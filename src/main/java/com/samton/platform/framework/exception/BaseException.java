package com.samton.platform.framework.exception;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.samton.platform.framework.exception.constant.ExpCodeConstant;

/**
 * 
 * @Description:基础异常信息 集成Java Exception
 * @author: Alex
 * @date: 2017年2月22日 下午3:26:16
 * Copyright (c) 2017, Samton. All rights reserved
 */
public abstract class BaseException extends Exception {

	private static final long serialVersionUID = 1L;

	// 详细异常代码
	private String detailCode;
	// 异常提示信息
	private String msg;
	// 后台打印异常信息
	private String bgMsg;
	// 显示的异常编号
	private String showCode;
	// 是否前台显示提示
	private boolean alertExpFlag;

	public abstract String getErrorLayer();

	public String getDetailCode() {
		return detailCode;
	}

	public void setDetailCode(String detailCode) {
		this.detailCode = detailCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getBgMsg() {
		return bgMsg;
	}

	public void setBgMsg(String bgMsg) {
		this.bgMsg = bgMsg;
	}

	public boolean getAlertExpFlag() {
		return alertExpFlag;
	}

	public void setAlertExpFlag(boolean alertExpFlag) {
		this.alertExpFlag = alertExpFlag;
	}

	/**
	 * @param errorCode	前台显示异常代码
	 */
	public BaseException(String errorCode) {
		this(errorCode, null, true);
	}

	/**
	 * @param errorCode 前台显示异常代码
	 * @param bgMsg		后台打印异常信息
	 */
	public BaseException(String errorCode, String bgMsg) {
		this(errorCode, bgMsg, true);
	}

	/**
	 * @param errorCode		前台显示异常代码
	 * @param alertExpFlag	前台是否显示提示语
	 */
	public BaseException(String errorCode, boolean alertExpFlag) {
		this(errorCode, null, alertExpFlag);
	}

	/**
	 * 
	 * @param errorCode		前台显示异常代码
	 * @param bgMsg			后台打印异常信息
	 * @param alertExpFlag	前台是否显示提示语
	 */
	public BaseException(Throwable cause, String errorCode) {
		this(cause, errorCode, true, null);
	}

	public BaseException(Throwable cause, String errorCode, boolean alertExpFlag, String showCode) {
		super(cause);
		if (StringUtils.isEmpty(errorCode)) {
			errorCode = ExpCodeConstant.DEFAULT_CODE;
		}
		this.detailCode = this.getErrorLayer() + "_" + errorCode;
		this.msg = ExpCodeConstant.explainCodeToMsg(errorCode);
		this.bgMsg = cause.getMessage();
		this.alertExpFlag = alertExpFlag;
		this.showCode = showCode;
	}

	public BaseException(String errorCode, String bgMsg, boolean alertExpFlag) {
		super(ExpCodeConstant.explainCodeToMsg(errorCode));
		this.detailCode = this.getErrorLayer() + "_" + errorCode;
		this.msg = this.getMessage();
		this.bgMsg = bgMsg;
		this.alertExpFlag = alertExpFlag;
	}

	public JSONObject processException() {
		JSONObject jo = new JSONObject();
		jo.put("rs", -1);
		jo.put("detailCode", this.getDetailCode());
		String msg = this.getMsg();
		if (StringUtils.isNotEmpty(showCode)) {
			msg += "(" + showCode + ")";
		}
		jo.put("msg", msg);
		jo.put("alertExpFlag", this.getAlertExpFlag() ? 1 : 0);
		return jo;
	}

}
