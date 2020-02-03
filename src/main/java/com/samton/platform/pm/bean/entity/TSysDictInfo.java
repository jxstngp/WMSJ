package com.samton.platform.pm.bean.entity;

import java.io.Serializable;

import com.samton.platform.framework.base.BaseBean;

/**
 * 
 * @Description:字典信息实体
 * @author:     shenchu
 * @date:        2017年2月23日 下午9:36:30
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class TSysDictInfo extends BaseBean implements Serializable {
	//字典信息ID
    private Integer dictId;
    //字典信息名称
    private String dictName;
    //排序
    private Integer sort;
    //备注
    private String remark;
    //字典类型ID
    private Long typeId;
    //状态  0 删除 1 新增
    private Short state;
    //字典类型名称
    private String typeName;

    private static final long serialVersionUID = 1L;

    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}