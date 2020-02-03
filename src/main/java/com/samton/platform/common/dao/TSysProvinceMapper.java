package com.samton.platform.common.dao;

import java.util.List;

import com.samton.platform.common.bean.entity.TSysProvince;

public interface TSysProvinceMapper {
    int deleteByPrimaryKey(Integer provinceId);

    int insert(TSysProvince record);

    int insertSelective(TSysProvince record);

    TSysProvince selectByPrimaryKey(Integer provinceId);

    int updateByPrimaryKeySelective(TSysProvince record);

    int updateByPrimaryKey(TSysProvince record);

	/** 
	 * @Title:        getProvince 
	 * @Description:  获取省份信息
	 * @param:        @return    
	 * @return:       List<TSysProvince>    
	 * @author        shenchu
	 * @Date          2017年3月2日 上午9:24:42 
	 */
	List<TSysProvince> getProvince();
}