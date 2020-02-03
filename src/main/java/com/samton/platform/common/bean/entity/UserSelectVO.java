package com.samton.platform.common.bean.entity;

import java.io.Serializable;

/**
 * 
 * @Description:人员选择器实体
 * @author: shenchu
 * @date: 2017年2月28日 下午11:57:58 Copyright (c) 2017, Samton. All rights reserved
 */
@SuppressWarnings("serial")
public class UserSelectVO implements Serializable {
    //用户ID
	private Long userId;
    //姓名
    private String userName;
    //所属部门ID
    private Long departId;
    
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getDepartId() {
		return departId;
	}
	public void setDepartId(Long departId) {
		this.departId = departId;
	}

}
