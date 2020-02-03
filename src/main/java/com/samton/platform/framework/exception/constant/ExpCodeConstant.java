package com.samton.platform.framework.exception.constant;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 
 * @Description:异常信息代码常量
 * @author: Alex
 * @date: 2017年2月22日 下午3:20:13
 * Copyright (c) 2017, Samton. All rights reserved
 */
@Component
public class ExpCodeConstant {

	protected static Map<String, String> msgMap = new HashMap<String, String>(0);

	// 系统级别错误“0”开头
	public static final String DEFAULT_CODE = "0000";

	public static final String URL_ACCESS_DENIED = "0001";

	public static final String PARAMTER_ILLEGAL = "0002";

	public static final String CHARACTER_TOO_LONG = "0003";

	public static final String NUMBER_TOO_LARGER = "0004";

	public static final String INVALID_FORMAT_ERROR = "0005";

	public static final String REQUEST_RESUBMIT_ERROR = "0006";

	// 业务级别按模块自己划分，第一位数值位1\2\3\4\5\6\7\8\9依次递增
	static {
		msgMap.put(DEFAULT_CODE, "系统错误");
		msgMap.put(URL_ACCESS_DENIED, "URL拒绝访问");
		msgMap.put(PARAMTER_ILLEGAL, "非法参数");
		msgMap.put(CHARACTER_TOO_LONG, "输入内容过长");
		msgMap.put(NUMBER_TOO_LARGER, "数值不能大于999999999");
		msgMap.put(INVALID_FORMAT_ERROR, "请输入正确的类型");
		msgMap.put(REQUEST_RESUBMIT_ERROR, "正在执行中，请稍后");
	}

	/**
	 * 
	 * @Title:        explainCodeToMsg 
	 * @Description:  根据Code输出异常信息
	 * @param:        @param errorCode
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月22日 下午3:23:51
	 */
	public static String explainCodeToMsg(String errorCode) {
		String errorMsg = msgMap.get(errorCode);
		if (StringUtils.isEmpty(errorMsg)) {
			errorMsg = msgMap.get(DEFAULT_CODE);
		}
		return errorMsg;
	}

}