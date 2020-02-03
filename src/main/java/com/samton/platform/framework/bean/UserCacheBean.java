package com.samton.platform.framework.bean;

import java.io.Serializable;
import java.util.List;

import com.samton.platform.pm.bean.entity.TSysPmMenu;

/**
 * 
 * @Description:登录用户缓存类
 * @author:     Alex
 * @date:        2017年2月22日 下午1:37:53
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class UserCacheBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// 用户ID
	protected Long userId;
	// 登录名称
	protected String loginName;
	// 系统ID
	protected long systemId;
	// 企业ID
	protected long enterpriseId;
	// 用户名称
	protected String userName;
	// SessionId
	private String sessionId;
	// 登录IP
	private String ip;
	// 端口
	private int port;
	// 是否是admin用户
	private Short isAdmin;
	// 是否是管理员
	private Short isManager;
	// 代理商ID
	private Long proxyId;
	
	private List<TSysPmMenu> menus;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public long getSystemId() {
		return systemId;
	}

	public void setSystemId(long systemId) {
		this.systemId = systemId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Short getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Short isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Short getIsManager() {
		return isManager;
	}

	public void setIsManager(Short isManager) {
		this.isManager = isManager;
	}

	public Long getProxyId() {
		return proxyId;
	}

	public void setProxyId(Long proxyId) {
		this.proxyId = proxyId;
	}

	public List<TSysPmMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<TSysPmMenu> menus) {
		this.menus = menus;
	}
}