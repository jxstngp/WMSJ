package com.samton.platform.pm.bean.entity;

import com.samton.platform.framework.base.BaseBean;
import java.io.Serializable;

/**
 * 
 * @Description:系统参数实体
 * @author:     shenchu
 * @date:        2017年2月23日 下午5:09:00
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class TSysParam extends BaseBean implements Serializable {
	//参数ID
    private Long paramId;
    //参数名称
    private String paramName;
    //参数值
    private String paramValue;
    //备注
    private String remark;
    //状态： 0--删除 1--新增
    private Short state;

    private static final long serialVersionUID = 1L;

    public Long getParamId() {
        return paramId;
    }

    public void setParamId(Long paramId) {
        this.paramId = paramId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
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