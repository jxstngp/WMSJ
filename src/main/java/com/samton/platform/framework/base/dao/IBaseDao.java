package com.samton.platform.framework.base.dao;

/**
 * 
 * @Description:BaseDao接口
 * @author:     Alex
 * @date:        2017年2月22日 上午10:07:10
 * Copyright (c) 2017, Samton. All rights reserved
 */
public interface IBaseDao<T> {

	/**
	 * @Title:        deleteByPrimaryKey 
	 * @Description:  删除
	 * @param:        @param id		主键ID
	 * @param:        @return    
	 * @return:       int    
	 * @author        Alex
	 * @Date          2017年2月22日 上午10:11:10
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * 
	 * @Title:        insert 
	 * @Description:  新增(全部)
	 * @param:        @param record
	 * @param:        @return    
	 * @return:       int    
	 * @author        Alex
	 * @Date          2017年2月22日 上午10:11:22
	 */
	int insert(T record);

	/**
	 * 
	 * @Title:        insertSelective 
	 * @Description:  新增(根据条件)
	 * @param:        @param record
	 * @param:        @return    
	 * @return:       int    
	 * @author        Alex
	 * @Date          2017年2月22日 上午10:12:20
	 */
	int insertSelective(T record);

	/**
	 * 
	 * @Title:        selectByPrimaryKey 
	 * @Description:  查询
	 * @param:        @param id
	 * @param:        @return    
	 * @return:       T    
	 * @author        Alex
	 * @Date          2017年2月22日 上午10:13:00
	 */
	T selectByPrimaryKey(Long id);

	/**
	 * 
	 * @Title:        updateByPrimaryKeySelective 
	 * @Description:  编辑(根据条件)
	 * @param:        @param record
	 * @param:        @return    
	 * @return:       int    
	 * @author        Alex
	 * @Date          2017年2月22日 上午10:14:15
	 */
	int updateByPrimaryKeySelective(T record);

	/**
	 * 
	 * @Title:        updateByPrimaryKey 
	 * @Description:  修改(全部)
	 * @param:        @return    
	 * @return:       int    
	 * @author        Alex
	 * @Date          2017年2月22日 上午10:14:57
	 */
	int updateByPrimaryKey(T record);

}
