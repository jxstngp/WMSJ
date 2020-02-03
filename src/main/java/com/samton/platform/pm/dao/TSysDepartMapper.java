package com.samton.platform.pm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.pm.bean.entity.TSysDepart;

/**
 * 
 * @Description:部门Mapper
 * @author:     shenchu
 * @date:        2017年2月22日 下午5:17:45
 * Copyright (c) 2017, Samton. All rights reserved
 */
public interface TSysDepartMapper {
	/**
	 * 
	 * @Title:        deleteByPrimaryKey 
	 * @Description:  删除部门
	 * @param:        @param departId
	 * @param:        @return    
	 * @return:       int    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午3:59:20
	 */
    int deleteByPrimaryKey(Long departId);
    
    /**
     * 
     * @Title:        insert 
     * @Description:  新增用户
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:25:13
     */
    int insert(TSysDepart record);

    /**
     * 
     * @Title:        insertSelective 
     * @Description:  新增部门（选择性）
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午4:00:08
     */
    int insertSelective(TSysDepart record);

    /**
     * 
     * @Title:        selectByPrimaryKey 
     * @Description:  通过ID查询部门信息
     * @param:        @param departId
     * @param:        @return    
     * @return:       TSysDepart    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:24:06
     */
    TSysDepart selectByPrimaryKey(Long departId);

    /**
     * 
     * @Title:        updateByPrimaryKeySelective 
     * @Description:  修改部门信息(选择性)
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:25:38
     */
    int updateByPrimaryKeySelective(TSysDepart record);

    /**
     * 
     * @Title:        updateByPrimaryKey 
     * @Description:  修改部门信息
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:25:58
     */
    int updateByPrimaryKey(TSysDepart record);
    
    /**
     * 
     * @Title:        getPlatformPmDeparts 
     * @Description:  获取部门信息(分页)
     * @param:        @param paramBean
     * @param:        @param rowBounds
     * @param:        @return    
     * @return:       List<TSysDepart>    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:27:50
     */
    List<TSysDepart> getPlatformPmDeparts(Pagination<TSysDepart> paramBean,RowBounds rowBounds);
    
    /**
     * 
     * @Title:        selectPlatformPmDeparts 
     * @Description:  查询部门信息
     * @param:        @return    
     * @return:       List<TSysDepart>    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:28:10
     */
    List<TSysDepart> selectPlatformPmDeparts();
    
    /**
     * 
     * @Title:        selectByPrimary 
     * @Description:  查询部门信息
     * @param:        @param depart
     * @param:        @return    
     * @return:       List<TSysDepart>    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:28:27
     */
    List<TSysDepart> selectByPrimary(TSysDepart depart);
    
    /**
     * 
     * @Title:        exportDepartList 
     * @Description:  部门信息导出
     * @param:        @param paramBean
     * @param:        @param rowBounds
     * @param:        @return    
     * @return:       List<Map<String,Object>>    
     * @author        shenchu
     * @Date          2017年2月22日 下午5:29:13
     */
    List<Map<String, Object>> exportDepartList(Pagination<TSysDepart> paramBean,RowBounds rowBounds);

	/** 
	 * @Title:        selectDepartListForSelect 
	 * @Description:  查询所有部门
	 * @param:        @return    
	 * @return:       List<TSysDepart>    
	 * @author        shenchu
	 * @Date          2017年3月2日 下午5:00:55 
	 */
	List<TSysDepart> selectDepartListForSelect();
    

}