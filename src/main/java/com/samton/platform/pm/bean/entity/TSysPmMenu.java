package com.samton.platform.pm.bean.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.samton.platform.framework.base.BaseBean;

/**
 * 
 * @Description:菜单实体
 * @author: Alex
 * @date: 2017年2月23日 下午4:27:39
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class TSysPmMenu extends BaseBean implements Serializable {

	// 菜单ID
	private Long menuId;

	// 菜单编码
	private String menuCode;

	// 菜单名
	private String menuName;

	// 菜单图标
	private String menuIcon;

	// 菜单URL地址
	private String menuUrl;

	// 菜单描述
	private String descriptions;

	// 菜单父级ID
	private Long parentId;

	// 排序ID，定义菜单位置
	private Integer sortby;

	// 0--系统Menu标题,1--MENU,2--MENU ACTION,3--MENU OBJECT
	private Short menuType;

	// 是否默认菜单项	0：否	1：是
	private Short isDefault;

	// 状态	0--删除	1--新增
	private Short state;

	// 下级菜单集合
    private List<TSysPmMenu> subMenus = new ArrayList<TSysPmMenu>(0);
    
    // 是否拥有该权限
    private boolean isCheck;

	private static final long serialVersionUID = 1L;

    public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getSortby() {
		return sortby;
	}

	public void setSortby(Integer sortby) {
		this.sortby = sortby;
	}

	public Short getMenuType() {
		return menuType;
	}

	public void setMenuType(Short menuType) {
		this.menuType = menuType;
	}

	public Short getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Short isDefault) {
		this.isDefault = isDefault;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public List<TSysPmMenu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<TSysPmMenu> subMenus) {
		this.subMenus = subMenus;
	}

	public boolean getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}
}