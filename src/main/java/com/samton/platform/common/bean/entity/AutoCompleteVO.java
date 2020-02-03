package com.samton.platform.common.bean.entity;

import java.io.Serializable;

/**
 * 
 * @Description:自定补全信息
 * @author: shenchu
 * @date: 2017年2月28日 下午11:57:58 Copyright (c) 2017, Samton. All rights reserved
 */
@SuppressWarnings("serial")
public class AutoCompleteVO implements Serializable {
	//显示内容
	private String label;
	//传值
	private Long id;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
