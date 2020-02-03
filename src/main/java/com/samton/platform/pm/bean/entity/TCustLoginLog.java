package com.samton.platform.pm.bean.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class TCustLoginLog implements Serializable {
    private Integer logId;

    private Long custId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss ")
    private Date loginDate;

    private String loginIp;

    private Short loginType;

    private static final long serialVersionUID = 1L;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8" )
    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Short getLoginType() {
        return loginType;
    }

    public void setLoginType(Short loginType) {
        this.loginType = loginType;
    }
}