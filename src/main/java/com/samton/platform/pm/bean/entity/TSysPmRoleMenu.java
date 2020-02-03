package com.samton.platform.pm.bean.entity;

import java.io.Serializable;

import com.samton.platform.framework.base.BaseBean;

/**
 * 
 * @Description:角色与菜单对应关系实体
 * @author:     shenchu
 * @date:        2017年2月23日 下午5:04:16
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class TSysPmRoleMenu extends BaseBean implements Serializable {
    //角色菜单ID
	private Long roleMenuId;
	//角色ID
    private Long roleId;
    //菜单ID
    private Long menuId;
    //状态： 0--删除 1--新增
    private Short state;
   
    private static final long serialVersionUID = 1L;

    public Long getRoleMenuId() {
        return roleMenuId;
    }

    public void setRoleMenuId(Long roleMenuId) {
        this.roleMenuId = roleMenuId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

}