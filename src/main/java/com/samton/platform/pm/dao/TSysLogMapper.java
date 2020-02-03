package com.samton.platform.pm.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.pm.bean.entity.TSysLog;

/**
 * 
 * @Description:系统日志Mapper
 * @author:     shenchu
 * @date:        2017年2月22日 下午5:48:38
 * Copyright (c) 2017, Samton. All rights reserved
 */
public interface TSysLogMapper {
	/**
	 * 
	 * @Title:        deleteByPrimaryKey 
	 * @Description:  删除系统日志
	 * @param:        @param logId
	 * @param:        @return    
	 * @return:       int    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午5:49:11
	 */
    int deleteByPrimaryKey(Long logId);

    /**
     * 
     * @Title:        insert 
     * @Description:  新增系统日志
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:49:27
     */
    int insert(TSysLog record);

    /**
     * 
     * @Title:        insertSelective 
     * @Description:  新增系统日志(选择性)
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:49:40
     */
    int insertSelective(TSysLog record);

    /**
     * 
     * @Title:        selectByPrimaryKey 
     * @Description:  通过ID查询系统日志
     * @param:        @param logId
     * @param:        @return    
     * @return:       TSysLog    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:50:03
     */
    TSysLog selectByPrimaryKey(Long logId);

    /**
     * 
     * @Title:        updateByPrimaryKeySelective 
     * @Description:  修改系统日志(选择性)
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:50:25
     */
    int updateByPrimaryKeySelective(TSysLog record);

    /**
     * 
     * @Title:        updateByPrimaryKey 
     * @Description:  修改系统日志
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:50:47
     */
    int updateByPrimaryKey(TSysLog record);
    
    /**
     * 
     * @Title:        queryOprLogs 
     * @Description:  分页查询操作日志
     * @param:        @param paramBean
     * @param:        @param rowBounds
     * @param:        @return    
     * @return:       List<TSysLog>    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:51:04
     */
    List<TSysLog> queryOprLogs(Pagination<TSysLog> paramBean,RowBounds rowBounds);
    
}