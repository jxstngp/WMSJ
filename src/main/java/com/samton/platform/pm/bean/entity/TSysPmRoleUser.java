package com.samton.platform.pm.bean.entity;

import java.io.Serializable;

import com.samton.platform.framework.base.BaseBean;

/**
 * 
 * @Description:角色用户关联实体
 * @author:     shenchu
 * @date:        2017年2月23日 下午3:49:58
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class TSysPmRoleUser extends BaseBean implements Serializable {
    //角色用户ID
	private Long roleUserId;
	//角色ID
    private Long roleId;
    //用户ID
    private Long userId;

    //状态： 0--删除 1--新增
    private Short state;

    private static final long serialVersionUID = 1L;

    public Long getRoleUserId() {
        return roleUserId;
    }

    public void setRoleUserId(Long roleUserId) {
        this.roleUserId = roleUserId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

}