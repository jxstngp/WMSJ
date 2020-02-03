package com.samton.platform.pm.bean.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.samton.platform.pm.bean.entity.TCustLoginLog;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class TCustLoginLogVo extends TCustLoginLog {


    private String custCode;

    private String custName;

    private String wechatName;

    private Short fqLevel;

    private String password;

    private Short sex;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    private String mobile;

    private Short registerType;

    private String wechatCode;

    private Long provinceId;

    private Long cityId;

    private String address;

    private String head;

    private String clientId;

    private Short isForbid;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastTime;

    private String lastIp;

    private Short optionSystem;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    private static final long serialVersionUID = 1L;



    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getWechatName() {
        return wechatName;
    }

    public void setWechatName(String wechatName) {
        this.wechatName = wechatName;
    }

    public Short getFqLevel() {
        return fqLevel;
    }

    public void setFqLevel(Short fqLevel) {
        this.fqLevel = fqLevel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Short getRegisterType() {
        return registerType;
    }

    public void setRegisterType(Short registerType) {
        this.registerType = registerType;
    }

    public String getWechatCode() {
        return wechatCode;
    }

    public void setWechatCode(String wechatCode) {
        this.wechatCode = wechatCode;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Short getIsForbid() {
        return isForbid;
    }

    public void setIsForbid(Short isForbid) {
        this.isForbid = isForbid;
    }
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8" )
    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public Short getOptionSystem() {
        return optionSystem;
    }

    public void setOptionSystem(Short optionSystem) {
        this.optionSystem = optionSystem;
    }
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8" )
    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8" )
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


}
