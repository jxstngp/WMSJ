package com.samton.platform.pm.dao;

import com.samton.platform.pm.bean.entity.TSysParam;

/**
 * 
 * @Description:系统参数Mapper
 * @author:     shenchu
 * @date:        2017年2月22日 下午5:51:29
 * Copyright (c) 2017, Samton. All rights reserved
 */
public interface TSysParamMapper {
	/**
	 * 
	 * @Title:        deleteByPrimaryKey 
	 * @Description:  删除系统参数
	 * @param:        @param paramId
	 * @param:        @return    
	 * @return:       int    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午5:51:43
	 */
    int deleteByPrimaryKey(Long paramId);

    /**
     * 
     * @Title:        insert 
     * @Description:  新增系统参数
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:51:56
     */
    int insert(TSysParam record);

    /**
     * 
     * @Title:        insertSelective 
     * @Description:  新增系统参数(选择性)
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:52:16
     */
    int insertSelective(TSysParam record);

    /**
     * 
     * @Title:        selectByPrimaryKey 
     * @Description:  通过ID查询系统参数
     * @param:        @param paramId
     * @param:        @return    
     * @return:       TSysParam    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:52:32
     */
    TSysParam selectByPrimaryKey(Long paramId);

    /**
     * 
     * @Title:        updateByPrimaryKeySelective 
     * @Description:  修改系统参数(选择性)
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:53:04
     */
    int updateByPrimaryKeySelective(TSysParam record);

    /**
     * 
     * @Title:        updateByPrimaryKey 
     * @Description:  修改系统参数
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:53:21
     */
    int updateByPrimaryKey(TSysParam record);

    /**
     * 
     * @Title:        getParamByName 
     * @Description:  通过系统参数名称查询系统
     * @param:        @param name
     * @param:        @return    
     * @return:       TSysParam    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:53:37
     */
    TSysParam getParamByName(String name);
}