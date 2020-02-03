	/**
 * 
 */
package com.samton.platform.pm.service.impl;

import com.samton.platform.framework.mybatis.pagination.PageContext;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.framework.util.CurrentUtil;
import com.samton.platform.pm.bean.entity.TSysDepart;
import com.samton.platform.pm.bean.entity.TSysPmUser;
import com.samton.platform.pm.dao.TSysDepartMapper;
import com.samton.platform.pm.dao.TSysLogMapper;
import com.samton.platform.pm.dao.TSysPmUserMapper;
import com.samton.platform.pm.service.IDepartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 
 * @Description:部门相关Service实现类
 * @author:     shenchu
 * @date:        2017年2月22日 下午9:36:40
 * Copyright (c) 2017, Samton. All rights reserved
 */
@Service("departService")
public class DepartServiceImpl implements IDepartService {
	@Resource
	private TSysDepartMapper departMapper;
	@Resource
	private TSysPmUserMapper userMapper;
	@Resource
	private TSysLogMapper logMapper;

	@Override
	public List<TSysPmUser> selectUserList() {
		return userMapper.selectUserList();
	}
	
	@Override
	public Pagination<TSysDepart> getPlatformPmDeparts(Pagination<TSysDepart> paramBean) {
		Pagination<TSysDepart> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
		List<TSysDepart> pmDeparts = departMapper.getPlatformPmDeparts(paramBean, pagination.getRowBounds());
		pagination.setData(pmDeparts);
		return pagination;
	}

	@Override
	public TSysDepart getTPlatformPmDepartById(long departId) {
		return departMapper.selectByPrimaryKey(departId);
	}

	@Override
	public long addTPlatformPmDepart(TSysDepart depart) throws Exception{
		
		List<TSysDepart> departs=departMapper.selectByPrimary(depart);
		if (departs.size()>0) {
			return -2;
		}
		CurrentUtil.setBaseBeanByInsert(depart);
		Short state=1;
		depart.setState(state);
		return departMapper.insertSelective(depart);
	}

	@Override
	public long updateTPlatformPmDepart(TSysDepart depart) {
		List<TSysDepart> departs=departMapper.selectByPrimary(depart);
		if (departs.size()>0) {
			return -2;
		}
		CurrentUtil.setBaseBeanByModify(depart);
		return departMapper.updateByPrimaryKeySelective(depart);
	}

	@Override
	public long delTPlatformPmDepartById(long departId) throws Exception {
		List<TSysPmUser> users =userMapper.selectUserListByDepartId(departId);
		if (users.size() > 0) {
			return 0;
		}
		TSysDepart depart = new TSysDepart();
		depart.setDepartId(departId);
		Short state=0;
		depart.setState(state);
		depart.setState(state);
		CurrentUtil.setBaseBeanByModify(depart);
		return departMapper.updateByPrimaryKeySelective(depart);
	}

	@Override
	public List<TSysDepart> selectPlatformPmDeparts() {
		return departMapper.selectPlatformPmDeparts();
	}

	@Override
	public Pagination<Map<String, Object>> exportDepartList(
			Pagination<TSysDepart> paramBean) {
		Pagination<Map<String, Object>> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
		List<Map<String, Object>> roles = departMapper.exportDepartList(paramBean, pagination.getRowBounds());
		pagination.setData(roles);
		return pagination;
	}

}
