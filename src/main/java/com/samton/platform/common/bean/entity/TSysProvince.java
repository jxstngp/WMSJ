package com.samton.platform.common.bean.entity;

import com.samton.platform.framework.base.BaseBean;
import java.io.Serializable;

public class TSysProvince extends BaseBean implements Serializable {
    private Integer provinceId;

    private String provinceName;

    private String provinceAbbr;

    private Integer orderNum;

    private Long areaId;

    private static final long serialVersionUID = 1L;

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceAbbr() {
        return provinceAbbr;
    }

    public void setProvinceAbbr(String provinceAbbr) {
        this.provinceAbbr = provinceAbbr;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }
}