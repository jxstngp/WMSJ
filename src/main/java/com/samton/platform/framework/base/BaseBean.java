package com.samton.platform.framework.base;

import java.util.Date;

/**
 * 
 * @Description:BaseBean类
 * @author:     Alex
 * @date:        2017年2月22日 上午10:27:27
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class BaseBean implements Cloneable {

	// 创建人ID
	protected Long createUserId;
	// 创建人名称
	protected String createUserName;
	// @DateTimeFormat(pattern="yyyy-MM-dd")
	// 创建时间
	protected Date createDate;
	// 修改人ID
	protected Long modifyUserId;
	// 修改人名称
	protected String modifyUserName;
	// @DateTimeFormat(pattern="yyyy-MM-dd")
	// 修改时间
	protected Date modifyDate;

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getModifyUserName() {
		return modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public void setBaseBean(BaseBean baseBean) {
		this.createDate = baseBean.createDate;
		this.createUserId = baseBean.createUserId;
		this.createUserName = baseBean.createUserName;
		this.modifyDate = baseBean.modifyDate;
		this.modifyUserId = baseBean.modifyUserId;
		this.modifyUserName = baseBean.modifyUserName;
	}

	// 浅度复制
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

}