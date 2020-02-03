package com.samton.platform.pm.bean.vo;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public class TAdvertiseVo  {
    private Integer advertiseId;

    private Short position;

    private String advertiseImg;

    private String link;

    private String remark;

    private Short isForbid;

    private Long createUserId;

    private Date createDate;

    private Long modifyUserId;

    private Date modifyDate;

    private String posited;

    private String forbid;

    private static final long serialVersionUID = 1L;

    public Integer getAdvertiseId() {
        return advertiseId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setAdvertiseId(Integer advertiseId) {
        this.advertiseId = advertiseId;
    }

    public Short getPosition() {
        return position;
    }

    public void setPosition(Short position) {
        this.position = position;
    }

    public String getAdvertiseImg() {
        return advertiseImg;
    }

    public void setAdvertiseImg(String advertiseImg) {
        this.advertiseImg = advertiseImg;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Short getIsForbid() {
        return isForbid;
    }

    public void setIsForbid(Short isForbid) {
        this.isForbid = isForbid;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8" )
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8" )
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getPosited() {
        return posited;
    }

    public void setPosited(String posited) {
        this.posited = posited;
    }

    public String getForbid() {
        return forbid;
    }

    public void setForbid(String forbid) {
        this.forbid = forbid;
    }
}
