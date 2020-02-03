package com.samton.platform.pm.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.pm.bean.entity.TSysDictType;

/**
 * 
 * @Description:数据字典类型Mapper
 * @author:     shenchu
 * @date:        2017年2月22日 下午5:16:34
 * Copyright (c) 2017, Samton. All rights reserved
 */
public interface TSysDictTypeMapper {
	/**
	 * 
	 * @Title:        deleteByPrimaryKey 
	 * @Description:  删除字典类型数据
	 * @param:        @param typeId
	 * @param:        @return    
	 * @return:       int    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午3:55:49
	 */
    int deleteByPrimaryKey(Long typeId);

    /**
     * 
     * @Title:        insert 
     * @Description:  新增字典类型数据
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午3:56:17
     */
    int insert(TSysDictType record);

    /**
     * 
     * @Title:        insertSelective 
     * @Description:  新增字典类型数据（选择性）
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午3:56:39
     */
    int insertSelective(TSysDictType record);

    /**
     * 
     * @Title:        selectByPrimaryKey 
     * @Description:  通过id获取字典类型数据
     * @param:        @param typeId
     * @param:        @return    
     * @return:       TSysDictType    
     * @author        shenchu
     * @Date          2017年2月22日 下午3:57:14
     */
    TSysDictType selectByPrimaryKey(Long typeId);

    /**
     * 
     * @Title:        updateByPrimaryKeySelective 
     * @Description:  修改字典类型数据(选择性)
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午3:57:37
     */
    int updateByPrimaryKeySelective(TSysDictType record);

    /**
     * 
     * @Title:        updateByPrimaryKey 
     * @Description:  修改数据字典数据
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午3:58:02
     */
    int updateByPrimaryKey(TSysDictType record);
    
    /**
     * 
     * @Title:        getDictTypeList 
     * @Description:  查找字典类型数据
     * @param:        @param record
     * @param:        @return    
     * @return:       List<TSysDictType>    
     * @author        shenchu
     * @Date          2017年2月22日 下午3:58:22
     */
    List<TSysDictType> getDictTypeList(TSysDictType record);
    
    /**
     * 
     * @Title:        queryDictTypeList 
     * @Description:  分页查询字典类型数据
     * @param:        @param paramBean
     * @param:        @param rowBounds
     * @param:        @return    
     * @return:       List<TSysDictType>    
     * @author        shenchu
     * @Date          2017年2月22日 下午3:58:35
     */
    List<TSysDictType> queryDictTypeList(Pagination<TSysDictType> paramBean,RowBounds rowBounds);
}