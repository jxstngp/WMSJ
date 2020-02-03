package com.samton.platform.pm.bean.entity;

import com.samton.platform.framework.base.BaseBean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @Description:用户实体
 * @author:     shenchu
 * @date:        2017年2月23日 下午3:37:52
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class TSysPmUser extends BaseBean implements Serializable {
    //用户ID
	private Long userId;
	//登录名
    private String loginName;
    //登录密码
    private String pwd;
    //姓名
    private String userName;
    //邮箱
    private String email;
    //手机
    private String mobile;
    //所属部门ID
    private Long departId;
    //状态： 0--删除 1--新增
    private Short state;
    //部门名称
    private String departName;
    //角色名称
    private String roleNames;

    private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDepartId() {
        return departId;
    }

    public void setDepartId(Long departId) {
        this.departId = departId;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}