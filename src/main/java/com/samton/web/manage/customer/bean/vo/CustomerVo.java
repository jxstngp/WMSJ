package com.samton.web.manage.customer.bean.vo;

import com.samton.web.manage.customer.bean.entity.TCustomer;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CustomerVo extends TCustomer {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerDateBegin; //注册开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerDateEnd; //注册结束时间

    private String registerTypeCN; //注册类型转义
    private String provinceName;
    private String cityName;

    public Date getRegisterDateBegin() {
        return registerDateBegin;
    }

    public void setRegisterDateBegin(Date registerDateBegin) {
        this.registerDateBegin = registerDateBegin;
    }

    public Date getRegisterDateEnd() {
        return registerDateEnd;
    }

    public void setRegisterDateEnd(Date registerDateEnd) {
        this.registerDateEnd = registerDateEnd;
    }

    public String getRegisterTypeCN() {
        return registerTypeCN;
    }

    public void setRegisterTypeCN(String registerTypeCN) {
        this.registerTypeCN = registerTypeCN;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
