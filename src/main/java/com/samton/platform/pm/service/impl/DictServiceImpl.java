/**
 * 
 */
package com.samton.platform.pm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.samton.platform.framework.exception.ServiceException;
import com.samton.platform.framework.mybatis.pagination.PageContext;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.framework.util.CurrentUtil;
import com.samton.platform.pm.bean.entity.TSysDictInfo;
import com.samton.platform.pm.bean.entity.TSysDictType;
import com.samton.platform.pm.constant.PmStateConstant;
import com.samton.platform.pm.dao.TSysDictInfoMapper;
import com.samton.platform.pm.dao.TSysDictTypeMapper;
import com.samton.platform.pm.service.IDictService;

/**
 * @Description: 字典管理和字典类型管理Service实现类
 * @author:      lijc
 * @date:        2017年2月7日 下午3:51:32
 * Copyright (c) 2017, Samton. All rights reserved
 */
@Service("dictService")
public class DictServiceImpl implements IDictService {
	
	@Resource
	private TSysDictTypeMapper dictTypeMapper;
	
	@Resource
	private TSysDictInfoMapper dictInfoMapper;
	
	@Override
	public Pagination<TSysDictType> queryDictTypeList(Pagination<TSysDictType> paramBean){
		Pagination<TSysDictType> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
		List<TSysDictType> dictTypes = dictTypeMapper.queryDictTypeList(paramBean, pagination.getRowBounds());
		pagination.setData(dictTypes);
		return pagination;
	}
	
	@Override
	public List<TSysDictType> getDictTypeList(TSysDictType dictType){
		return dictTypeMapper.getDictTypeList(dictType);
	}
	
	@Override
	public TSysDictType getDictType(long typeId) throws Exception {
		TSysDictType dictType = dictTypeMapper.selectByPrimaryKey(typeId);
		return dictType;
	}

	@Override
	public long addDictType(TSysDictType dictType) throws ServiceException {
		dictType.setState(PmStateConstant.ADD);
		CurrentUtil.setBaseBeanByInsert(dictType);
		long flag = dictTypeMapper.insertSelective(dictType);
		return flag;
	}
	
	@Override
	public boolean updateDictType(TSysDictType dictType) throws ServiceException {
		CurrentUtil.setBaseBeanByModify(dictType);
		boolean flag = dictTypeMapper.updateByPrimaryKeySelective(dictType) > 0;
		return flag;
	}
	
	@Override
	public boolean delDictType(long typeId) throws Exception {
		TSysDictType dictType = dictTypeMapper.selectByPrimaryKey(typeId);
		dictType.setState(PmStateConstant.DEL);
		CurrentUtil.setBaseBeanByModify(dictType);
		boolean flag = dictTypeMapper.updateByPrimaryKeySelective(dictType) > 0;
		if (flag) {
			List<TSysDictInfo> dictInfos = dictInfoMapper.queryDictInfoListByTypeId(typeId);
			for (TSysDictInfo tSysDictInfo : dictInfos) {
				tSysDictInfo.setState(PmStateConstant.DEL);
				CurrentUtil.setBaseBeanByModify(tSysDictInfo);
				dictInfoMapper.updateByPrimaryKeySelective(tSysDictInfo);
			}
		}
		return flag;
	}

	@Override
	public Pagination<TSysDictInfo> queryDictInfoList(Pagination<TSysDictInfo> paramBean) {
		Pagination<TSysDictInfo> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
		List<TSysDictInfo> dictInfos = dictInfoMapper.queryDictInfoList(paramBean, pagination.getRowBounds());
		pagination.setData(dictInfos);
		return pagination;
	}

	@Override
	public TSysDictInfo getDictInfo(long dictId) throws Exception {
		TSysDictInfo dictInfo = dictInfoMapper.selectByPrimaryKey(dictId);
		return dictInfo;
	}

	@Override
	public long addDictInfo(TSysDictInfo dictInfo) throws ServiceException {
		dictInfo.setState(PmStateConstant.ADD);
		CurrentUtil.setBaseBeanByInsert(dictInfo);
		long flag = dictInfoMapper.insertSelective(dictInfo);
		return flag;
	}

	@Override
	public boolean updateDictInfo(TSysDictInfo dictInfo) throws ServiceException {
		CurrentUtil.setBaseBeanByModify(dictInfo);
		boolean flag = dictInfoMapper.updateByPrimaryKeySelective(dictInfo) > 0;
		return flag;
	}

	@Override
	public boolean delDictInfo(long dictId) throws Exception {
		TSysDictInfo dictInfo = dictInfoMapper.selectByPrimaryKey(dictId);
		dictInfo.setState(PmStateConstant.DEL);
		CurrentUtil.setBaseBeanByModify(dictInfo);
		return dictInfoMapper.updateByPrimaryKeySelective(dictInfo) > 0;
	}
	
}
