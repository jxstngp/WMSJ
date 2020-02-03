package com.samton.platform.pm.bean.entity;

import java.io.Serializable;

import com.samton.platform.framework.base.BaseBean;

/**
 * 
 * @Description:数据字典类型实体
 * @author:     shenchu
 * @date:        2017年2月23日 下午9:30:35
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class TSysDictType extends BaseBean implements Serializable {
	//字典类型ID
    private Long typeId;
    //类型名称
    private String typeName;
    //备注
    private String remark;
    //状态 0 删除 1 新增
    private Short state;

    private static final long serialVersionUID = 1L;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

}