package com.samton.platform.pm.bean.entity;

import java.io.Serializable;
import java.util.Date;

import com.samton.platform.framework.base.BaseBean;

/**
 * 
 * @Description:部门实体
 * @author:     shenchu
 * @date:        2017年2月23日 下午9:02:45
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class TSysDepart extends BaseBean implements Serializable {
    //部门ID
	private Long departId;
	//部门名称
    private String departName;
    //父级ID
    private Integer parentDepartId;
    //备注
    private String remark;
    //是否部门负责人 0. 否  1. 是
    private Integer managerUserId;
    //状态  0--删除 1--新增;
    private Short state;

    private String parentDepartName;
    
    private String managerUserNmae;
    
    private static final long serialVersionUID = 1L;

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

    public Integer getParentDepartId() {
        return parentDepartId;
    }

    public void setParentDepartId(Integer parentDepartId) {
        this.parentDepartId = parentDepartId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getManagerUserId() {
        return managerUserId;
    }

    public void setManagerUserId(Integer managerUserId) {
        this.managerUserId = managerUserId;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

	public String getParentDepartName() {
		return parentDepartName;
	}

	public void setParentDepartName(String parentDepartName) {
		this.parentDepartName = parentDepartName;
	}

	public String getManagerUserNmae() {
		return managerUserNmae;
	}

	public void setManagerUserNmae(String managerUserNmae) {
		this.managerUserNmae = managerUserNmae;
	}
    
}