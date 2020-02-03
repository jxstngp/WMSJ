package com.samton.platform.pm.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.pm.bean.entity.TSysLogLogin;

/**
 * 
 * @Description:登录日志Mapper
 * @author:     shenchu
 * @date:        2017年2月22日 下午5:29:37
 * Copyright (c) 2017, Samton. All rights reserved
 */
public interface TSysLogLoginMapper {
	/**
	 * 
	 * @Title:        deleteByPrimaryKey 
	 * @Description:  删除登录日志
	 * @param:        @param logId
	 * @param:        @return    
	 * @return:       int    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午5:29:55
	 */
    int deleteByPrimaryKey(Long logId);

    /**
     * 
     * @Title:        insert 
     * @Description:  新增登录日志
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:30:10
     */
    int insert(TSysLogLogin record);

    /**
     * 
     * @Title:        insertSelective 
     * @Description:  新增日志(选择性)
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:30:43
     */
    int insertSelective(TSysLogLogin record);

    /**
     * 
     * @Title:        selectByPrimaryKey 
     * @Description:  通过ID查询登录日志信息(选择性)
     * @param:        @param logId
     * @param:        @return    
     * @return:       TSysLogLogin    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:31:04
     */
    TSysLogLogin selectByPrimaryKey(Long logId);

    /**
     * 
     * @Title:        updateByPrimaryKeySelective 
     * @Description:  修改登录日志(选择性)
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:45:39
     */
    int updateByPrimaryKeySelective(TSysLogLogin record);

    /**
     * 
     * @Title:        updateByPrimaryKey 
     * @Description:  修改登录日志
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:46:04
     */
    int updateByPrimaryKey(TSysLogLogin record);
    
    /**
     * 
     * @Title:        queryLogLogins 
     * @Description:  分页查询登录日志
     * @param:        @param paramBean
     * @param:        @param rowBounds
     * @param:        @return    
     * @return:       List<TSysLogLogin>    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:46:57
     */
    List<TSysLogLogin> queryLogLogins(Pagination<TSysLogLogin> paramBean,RowBounds rowBounds);
    
}