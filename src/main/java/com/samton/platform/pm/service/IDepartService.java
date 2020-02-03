/**
 * 
 */
package com.samton.platform.pm.service;

import java.util.List;
import java.util.Map;

import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.pm.bean.entity.TSysDepart;
import com.samton.platform.pm.bean.entity.TSysPmUser;

/**
 * 
 * @Description:部门相关Service接口
 * @author:     shenchu
 * @date:        2017年2月22日 下午9:31:37
 * Copyright (c) 2017, Samton. All rights reserved
 */
public interface IDepartService {

	/** 
	 * @Title:   selectUserList      
	 * @Description: 
	 * @param:        @return    
	 * @return:       TPlatformPmUser    
	 * @author        zhijie
	 * @Date          2017年1月16日 下午4:46:19 
	 */
	List<TSysPmUser> selectUserList();
	
	/**
	 * 
	 * @Title:        selectPlatformPmDeparts 
	 * @Description:  获取所有部门
	 * @param:        @return    
	 * @return:       List<TSysDepart>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午9:34:42
	 */
	List<TSysDepart>  selectPlatformPmDeparts();
	
	/**
	 * 
	 * @Title:        getPlatformPmDeparts 
	 * @Description:  获取所有部门
	 * @param:        @param paramBean
	 * @param:        @return    
	 * @return:       Pagination<TSysDepart>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午9:34:53
	 */
	Pagination<TSysDepart> getPlatformPmDeparts(Pagination<TSysDepart> paramBean);
	
	/**
	 * 
	 * @Title:        getTPlatformPmDepartById 
	 * @Description:  查询部门 
	 * @param:        @param departId
	 * @param:        @return    
	 * @return:       TSysDepart    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午9:35:02
	 */
	TSysDepart getTPlatformPmDepartById(long departId);
	
	/**
	 * 
	 * @Title:        addTPlatformPmDepart 
	 * @Description:  新增部门
	 * @param:        @param depart
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       long    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午9:35:12
	 */
	long addTPlatformPmDepart(TSysDepart depart) throws Exception;
	
	/**
	 * 
	 * @Title:        updateTPlatformPmDepart 
	 * @Description:  修改部门
	 * @param:        @param depart
	 * @param:        @return    
	 * @return:       long    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午9:35:21
	 */
	long updateTPlatformPmDepart(TSysDepart depart);
	
	/**
	 * 
	 * @Title:        delTPlatformPmDepartById 
	 * @Description:  删除部门 
	 * @param:        @param departId
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       long    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午9:35:38
	 */
	long delTPlatformPmDepartById(long departId) throws Exception;
	
	/**
	 * 
	 * @Title:        exportDepartList 
	 * @Description:  导出部门
	 * @param:        @param paramBean
	 * @param:        @return    
	 * @return:       Pagination<Map<String,Object>>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午9:35:47
	 */
    Pagination<Map<String, Object>> exportDepartList(Pagination<TSysDepart> paramBean);
    

}
