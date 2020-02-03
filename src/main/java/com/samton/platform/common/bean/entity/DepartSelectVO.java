package com.samton.platform.common.bean.entity;

import java.io.Serializable;

/**
 * 
 * @Description:人员选择器部门信息实体
 * @author: shenchu
 * @date: 2017年2月28日 下午11:57:58 Copyright (c) 2017, Samton. All rights reserved
 */
@SuppressWarnings("serial")
public class DepartSelectVO implements Serializable {
    //部门ID
	private Long departId;
	//部门名称
    private String departName;
	public Long getDepartId() {
		return departId;
	}
	public void setDepartId(Long departId) {
		this.departId = departId;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
    
}
