package com.samton.platform.pm.bean.entity;

import java.io.Serializable;

import com.samton.platform.framework.base.BaseBean;

/**
 * 
 * @Description:角色实体
 * @author:     shenchu
 * @date:        2017年2月23日 下午3:51:21
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class TSysPmRole extends BaseBean implements Serializable {
    //角色ID
	private Long roleId;
	//角色名称
    private String roleName;
    //数据权限 0：本人及下属 1：本部门  2：本部门及下级部门 3：全部
    private Short dataPermissions;
    //排序
    private Integer sortby;
    //角色描述
    private String descriptions;
    //系统ID
    //状态： 0--删除  1--新增
    private Short state;

    private static final long serialVersionUID = 1L;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Short getDataPermissions() {
        return dataPermissions;
    }

    public void setDataPermissions(Short dataPermissions) {
        this.dataPermissions = dataPermissions;
    }

    public Integer getSortby() {
        return sortby;
    }

    public void setSortby(Integer sortby) {
        this.sortby = sortby;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

}