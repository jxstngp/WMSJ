package com.samton.platform.framework.util.http.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName:     RequestParams
 * @Description:TODO(Http请求参数bean)
 * @author:     Joshua_Cheng@qq.com
 * @date:        2015年6月25日 下午7:41:36
 * Copyright (c) 2015, Samton. All rights reserved
 */
public class RequestParams {
	//请求URL
	private String url;
	//请求参数map
	private Map<String,String> params;
	
	//构造器初始化请求参数
	public RequestParams() {
		super();
//		//设置默认接口URL
//		this.setUrl(Constant.HTTPURL);
		
		params = new HashMap<String,String>();
		//请求方法名
		params.put("f","dome");
		//请求是否验证加密{0 不加密 1加密}
		params.put("encrypt","0");
		//请求加密密钥
		params.put("key",null);
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}
