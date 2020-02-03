package com.samton.platform.pm.bean.entity;

import com.samton.platform.framework.base.BaseBean;
import com.samton.platform.framework.util.CurrentUtil;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @Description:操作日志实体
 * @author:     shenchu
 * @date:        2017年2月23日 下午5:21:00
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class TSysLog extends BaseBean implements Serializable {
    //操作日志ID
	private Long logId;
	//操作时间
    private Date operateDate;
    //操作描述
    private String operateDesc;
    //模块名称
    private String moduleName;

    private Date startDateTime;
    
    private Date endDateTime;
    
    private String loginName;
    
    private String userName;

    private static final long serialVersionUID = 1L;
    
    public TSysLog () {}
    
    public TSysLog (String moduleName, String operateDesc, Short operateType){
    	this.moduleName = moduleName;
    	this.operateDesc = operateDesc;
    	this.createUserId = CurrentUtil.getCurrentUser().getUserId();
    	this.modifyUserId = CurrentUtil.getCurrentUser().getUserId();
    	this.createUserName = CurrentUtil.getCurrentUser().getUserName();
    	this.modifyUserName = CurrentUtil.getCurrentUser().getUserName();
    	Date nowDate=new Date();
    	this.setCreateDate(nowDate);
    	this.setOperateDate(nowDate);
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public String getOperateDesc() {
        return operateDesc;
    }

    public void setOperateDesc(String operateDesc) {
        this.operateDesc = operateDesc;
    }

    public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    
}