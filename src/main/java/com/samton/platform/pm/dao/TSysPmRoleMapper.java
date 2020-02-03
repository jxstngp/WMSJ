package com.samton.platform.pm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.pm.bean.entity.TSysPmRole;

/**
 * 
 * @Description:角色Mapper
 * @author:     shenchu
 * @date:        2017年2月22日 下午8:39:08
 * Copyright (c) 2017, Samton. All rights reserved
 */
public interface TSysPmRoleMapper {
	/**
	 * 
	 * @Title:        deleteByPrimaryKey 
	 * @Description:  删除角色
	 * @param:        @param roleId
	 * @param:        @return    
	 * @return:       int    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午8:39:21
	 */
    int deleteByPrimaryKey(Long roleId);

    /**
     * 
     * @Title:        insert 
     * @Description:  新增角色
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:39:33
     */
    int insert(TSysPmRole record);

    /**
     * 
     * @Title:        insertSelective 
     * @Description:  新增角色(选择性)
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:45:41
     */
    int insertSelective(TSysPmRole record);

    /**
     * 
     * @Title:        selectByPrimaryKey 
     * @Description:  通过ID查询角色
     * @param:        @param roleId
     * @param:        @return    
     * @return:       TSysPmRole    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:46:00
     */
    TSysPmRole selectByPrimaryKey(Long roleId);

    /**
     * 
     * @Title:        updateByPrimaryKeySelective 
     * @Description:  修改角色(选择性)
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:46:13
     */
    int updateByPrimaryKeySelective(TSysPmRole record);

    /**
     * 
     * @Title:        updateByPrimaryKey 
     * @Description:  修改角色
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:46:38
     */
    int updateByPrimaryKey(TSysPmRole record);
    
    /**
     * 
     * @Title:        getRolesBySystemId 
     * @Description:  通过systemID选择角色
     * @param:        @param systemId
     * @param:        @param state
     * @param:        @return    
     * @return:       List<TSysPmRole>    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:47:09
     */
    List<TSysPmRole>getRolesBySystemId(@Param("systemId") long systemId,@Param("state") short state);
    
    /**
     * 
     * @Title:        queryRoleList 
     * @Description:  分页查询角色集合
     * @param:        @param paramBean
     * @param:        @param rowBounds
     * @param:        @return    
     * @return:       List<TSysPmRole>    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:47:27
     */
    List<TSysPmRole> queryRoleList(Pagination<TSysPmRole> paramBean,RowBounds rowBounds);
    
    /**
     * 
     * @Title:        exportRoleList 
     * @Description:  导出角色集合
     * @param:        @param paramBean
     * @param:        @param rowBounds
     * @param:        @return    
     * @return:       List<Map<String,Object>>    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:49:14
     */
    List<Map<String, Object>> exportRoleList(Pagination<TSysPmRole> paramBean,RowBounds rowBounds);

    /**
     * 
     * @Title:        getRoleById 
     * @Description:  通过ID获取角色
     * @param:        @param roleId
     * @param:        @return    
     * @return:       TSysPmRole    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:49:28
     */
    TSysPmRole getRoleById(Long roleId);

    /**
     * 
     * @Title:        checkRoleName 
     * @Description:  通过角色名称以及ID查询角色统计个数
     * @param:        @param roleName
     * @param:        @param roleId
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:49:46
     */
	int checkRoleName(@Param("roleName")String roleName, @Param("roleId") Long roleId);
	
	/**
	 * 
	 * @Title:        checkRoleUserNum 
	 * @Description:  通过角色ID获取该角色下用户的个数
	 * @param:        @param roleId
	 * @param:        @return    
	 * @return:       int    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午8:50:35
	 */
	int checkRoleUserNum(Long roleId);
}