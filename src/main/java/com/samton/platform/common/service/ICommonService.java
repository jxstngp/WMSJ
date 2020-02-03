/**
 * 
 */
package com.samton.platform.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.samton.platform.common.bean.entity.TSysCity;
import com.samton.platform.common.bean.entity.TSysProvince;
import com.samton.platform.pm.bean.entity.TSysDepart;
import com.samton.platform.pm.bean.entity.TSysPmUser;

/**
 * @Description:共用相关Service接口
 * @author:     shenchu
 * @date:        2017年3月1日 下午9:41:25
 * Copyright (c) 2017, Samton. All rights reserved
 */
public interface ICommonService {

	/** 
	 * @Title:        getProvince 
	 * @Description:  获取省份信息
	 * @param:        @return    
	 * @return:       List<TSysProvince>    
	 * @author        shenchu
	 * @Date          2017年3月2日 上午9:21:57 
	 */
	List<TSysProvince> getProvince();

	/** 
	 * @Title:        getCityByProvinceId 
	 * @Description:  通过省获取省下的市信息
	 * @param:        @param provinceId
	 * @param:        @return    
	 * @return:       List<TSysCity>    
	 * @author        shenchu
	 * @Date          2017年3月2日 下午1:25:44 
	 */
	List<TSysCity> getCityByProvinceId(Long provinceId);

	/** 
	 * @Title:        getUsersForSelect 
	 * @Description:  获取所有用户
	 * @param:        @return    
	 * @return:       List<TSysPmUser>    
	 * @author        shenchu
	 * @Date          2017年3月2日 下午4:53:40 
	 */
	List<TSysPmUser> getUsersForSelect();

	/** 
	 * @Title:        getDepartForSelect 
	 * @Description:  获取所有部门 
	 * @param:        @return    
	 * @return:       List<TSysDepart>    
	 * @author        shenchu
	 * @Date          2017年3月2日 下午4:59:12 
	 */
	List<TSysDepart> getDepartForSelect();

	/** 
	 * @Title:        queryUserName 
	 * @Description:  查询用户信息集合，数量不超过10个
	 * @param:        @param name
	 * @param:        @return    
	 * @return:       List<Map<String,Object>>    
	 * @author        shenchu
	 * @Date          2017年3月4日 下午5:04:39 
	 */
	List<Map<String, Object>> queryUserName(String name);

	/**
	 * 
	 * @Title:        uploadFileSingle 
	 * @Description:  TODO(这里用一句话描述这个方法的作用) 
	 * @param:        @param file
	 * @param:        @param request
	 * @param:        @return    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @throws Exception 
	 * @Date          2017年3月6日 上午10:51:53
	 */
	Map<String, Object> uploadFileSingle(CommonsMultipartFile file);

}
