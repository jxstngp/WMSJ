package com.samton.platform.pm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.samton.platform.pm.bean.entity.TSysPmRoleUser;

/**
 * 
 * @Description:角色用户Mapper
 * @author:     shenchu
 * @date:        2017年2月22日 下午9:02:38
 * Copyright (c) 2017, Samton. All rights reserved
 */
public interface TSysPmRoleUserMapper {
	/**
	 * 
	 * @Title:        deleteByPrimaryKey 
	 * @Description:  删除角色用户关联数据
	 * @param:        @param roleUserId
	 * @param:        @return    
	 * @return:       int    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午9:03:08
	 */
    int deleteByPrimaryKey(Long roleUserId);

    /**
     * 
     * @Title:        insert 
     * @Description:  新增角色用户关联数据
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:03:29
     */
    int insert(TSysPmRoleUser record);

    /**
     * 
     * @Title:        insertSelective 
     * @Description:  新增角色用户关联数据(选择性)
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:03:47
     */
    int insertSelective(TSysPmRoleUser record);

    /**
     * 
     * @Title:        selectByPrimaryKey 
     * @Description:  通过roleUserId获取角色用户关联数据
     * @param:        @param roleUserId
     * @param:        @return    
     * @return:       TSysPmRoleUser    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:04:07
     */
    TSysPmRoleUser selectByPrimaryKey(Long roleUserId);

    /**
     * 
     * @Title:        updateByPrimaryKeySelective 
     * @Description:  修改角色用户关联数据(选择性)
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:04:33
     */
    int updateByPrimaryKeySelective(TSysPmRoleUser record);

    /**
     * 
     * @Title:        updateByPrimaryKey 
     * @Description:  修改角色用户关联数据
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:04:53
     */
    int updateByPrimaryKey(TSysPmRoleUser record);
    
    /**
     * 
     * @Title:        batchInsert 
     * @Description:  批量新增角色用户关联数据
     * @param:        @param list
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:05:17
     */
    int batchInsert(List<TSysPmRoleUser> list);
    
    /**
     * 
     * @Title:        batchUpdateByPrimaryKey 
     * @Description:  批量修改角色用户关联数据
     * @param:        @param list
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:05:36
     */
    int batchUpdateByPrimaryKey(List<TSysPmRoleUser> list);

    /**
     * 
     * @Title:        getRoleUsers 
     * @Description:  通过userID获取角色用户关联数据集合
     * @param:        @param userId
     * @param:        @return    
     * @return:       List<TSysPmRoleUser>    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:05:51
     */
    List<TSysPmRoleUser> getRoleUsers(@Param("userId") long userId);
    
    /**
     * 
     * @Title:        getUserRoleIds 
     * @Description:  通过userID获取该用户下角色ID的集合
     * @param:        @param userId
     * @param:        @param state
     * @param:        @return    
     * @return:       List<Long>    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:07:19
     */
    List<Long> getUserRoleIds(@Param("userId") long userId,@Param("state") short state);
}