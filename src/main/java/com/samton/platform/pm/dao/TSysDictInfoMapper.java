package com.samton.platform.pm.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.pm.bean.entity.TSysDictInfo;

/**
 * 
 * @Description:数据字典Mapper
 * @author:     shenchu
 * @date:        2017年2月22日 下午5:13:14
 * Copyright (c) 2017, Samton. All rights reserved
 */
public interface TSysDictInfoMapper {
	/**
	 * 
	 * @Title:        deleteByPrimaryKey 
	 * @Description:  根据主键删除字典数据
	 * @param:        @param dictId
	 * @param:        @return    
	 * @return:       int    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午3:53:01
	 */
    int deleteByPrimaryKey(Long dictId);

    /**
     * 
     * @Title:        insert 
     * @Description:  新增字典数据
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午3:53:22
     */
    int insert(TSysDictInfo record);

    /**
     * 
     * @Title:        insertSelective 
     * @Description:  新增字典数据(选择性)
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午3:53:47
     */
    int insertSelective(TSysDictInfo record);

    /**
     * 
     * @Title:        selectByPrimaryKey 
     * @Description:  通过ID查询字典数据
     * @param:        @param dictId
     * @param:        @return    
     * @return:       TSysDictInfo    
     * @author        shenchu
     * @Date          2017年2月22日 下午3:54:14
     */
    TSysDictInfo selectByPrimaryKey(Long dictId);

    /**
     * 
     * @Title:        updateByPrimaryKeySelective 
     * @Description:  修改字典数据(选择性)
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午3:54:36
     */
    int updateByPrimaryKeySelective(TSysDictInfo record);

    /**
     * 
     * @Title:        updateByPrimaryKey 
     * @Description:  修改字典数据
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午3:55:13
     */
    int updateByPrimaryKey(TSysDictInfo record);
    
    /**
     * 
     * @Title:        queryDictInfoList 
     * @Description:  分页查询字典数据
     * @param:        @param paramBean
     * @param:        @param rowBounds
     * @param:        @return    
     * @return:       List<TSysDictInfo>    
     * @author        shenchu
     * @Date          2017年2月22日 下午3:55:23
     */
    List<TSysDictInfo> queryDictInfoList(Pagination<TSysDictInfo> paramBean,RowBounds rowBounds);
    
    /**
     * @Title:       queryDictInfoListByTypeId 
     * @Description: 根据字典类型查询该类型下的字典数据 
     * @param:       @param dicttypeId
     * @param:       @return    
     * @return:      List<TSysDictInfo>    
     * @author       lijc
     * @Date         2017年3月31日 下午2:56:42
     */
    List<TSysDictInfo> queryDictInfoListByTypeId(Long dicttypeId);
    
}