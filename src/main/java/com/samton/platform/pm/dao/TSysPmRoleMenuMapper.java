package com.samton.platform.pm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.samton.platform.pm.bean.entity.TSysPmRoleMenu;

/**
 * 
 * @Description:角色菜单Mapper
 * @author:     shenchu
 * @date:        2017年2月22日 下午8:55:37
 * Copyright (c) 2017, Samton. All rights reserved
 */
public interface TSysPmRoleMenuMapper {
	/**
	 * 
	 * @Title:        deleteByPrimaryKey 
	 * @Description:  通过roleMenuId删除角色与菜单的关联关系
	 * @param:        @param roleMenuId
	 * @param:        @return    
	 * @return:       int    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午8:55:22
	 */
    int deleteByPrimaryKey(Long roleMenuId);

    /**
     * 
     * @Title:        insert 
     * @Description:  新增角色菜单关系
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:56:50
     */
    int insert(TSysPmRoleMenu record);

    /**
     * 
     * @Title:        insertSelective 
     * @Description:  新增角色菜单关系(选择性)
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:57:14
     */
    int insertSelective(TSysPmRoleMenu record);

    /**
     * 
     * @Title:        selectByPrimaryKey 
     * @Description:  通过ID获取菜单角色关联数据
     * @param:        @param roleMenuId
     * @param:        @return    
     * @return:       TSysPmRoleMenu    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:57:50
     */
    TSysPmRoleMenu selectByPrimaryKey(Long roleMenuId);

    /**
     * 
     * @Title:        updateByPrimaryKeySelective 
     * @Description:  修改角色菜单关联关系(选择性)
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:58:20
     */
    int updateByPrimaryKeySelective(TSysPmRoleMenu record);

    /**
     * 
     * @Title:        updateByPrimaryKey 
     * @Description:  修改角色菜单
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:00:15
     */
    int updateByPrimaryKey(TSysPmRoleMenu record);
    
    /**
     * 
     * @Title:        batchInsert 
     * @Description:  批量新增角色菜单关联数据
     * @param:        @param list
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:00:36
     */
    int batchInsert(List<TSysPmRoleMenu> list);
    
    /**
     * 
     * @Title:        batchUpdateByPrimaryKey 
     * @Description:  批量修改角色菜单关联数据
     * @param:        @param list
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:00:59
     */
    int batchUpdateByPrimaryKey(List<TSysPmRoleMenu> list);
    
    /**
     * 
     * @Title:        getRoleMenus 
     * @Description:  通过角色ID获取角色菜单数据
     * @param:        @param roleId
     * @param:        @return    
     * @return:       List<TSysPmRoleMenu>    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:01:16
     */
    List<TSysPmRoleMenu> getRoleMenus(@Param("roleId") long roleId);

    /**
     * 
     * @Title:        getRoleMenuIds 
     * @Description:  通过角色ID获取菜单ID的集合
     * @param:        @param roleId
     * @param:        @param state
     * @param:        @return    
     * @return:       List<Long>    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:01:49
     */
    List<Long> getRoleMenuIds(@Param("roleId") long roleId,@Param("state") short state);
}