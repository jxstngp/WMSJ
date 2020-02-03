package com.samton.platform.common.dao;

import java.util.List;

import com.samton.platform.common.bean.entity.TSysCity;

public interface TSysCityMapper {
    int deleteByPrimaryKey(Integer cityId);

    int insert(TSysCity record);

    int insertSelective(TSysCity record);

    TSysCity selectByPrimaryKey(Integer cityId);

    int updateByPrimaryKeySelective(TSysCity record);

    int updateByPrimaryKey(TSysCity record);

	/** 
	 * @Title:        getCityByProvinceId 
	 * @Description:  通过省ID获取市信息
	 * @param:        @param provinceId
	 * @param:        @return    
	 * @return:       List<TSysCity>    
	 * @author        shenchu
	 * @Date          2017年3月2日 下午1:27:17 
	 */
	List<TSysCity> getCityByProvinceId(Long provinceId);
}