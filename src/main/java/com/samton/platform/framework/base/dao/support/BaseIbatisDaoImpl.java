package com.samton.platform.framework.base.dao.support;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.samton.platform.framework.base.dao.IBaseDao;

/**
 * 
 * @Description:BaseDao实现类
 * @author:     Alex
 * @date:        2017年2月22日 上午10:16:48
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class BaseIbatisDaoImpl<T> extends SqlSessionDaoSupport implements
		IBaseDao<T> {

	@Resource
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	private String getStatementId(String suffix) {
		String className = this.getClass().getName();
		return className + "." + suffix;
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return this.getSqlSession().delete(getStatementId("deleteByPrimaryKey"), id);
	}

	@Override
	public int insert(T record) {
		return this.getSqlSession().insert(getStatementId("insert"), record);
	}

	@Override
	public int insertSelective(T record) {
		return this.getSqlSession().insert(getStatementId("insertSelective"), record);
	}

	@Override
	public T selectByPrimaryKey(Long roleId) {
		return this.getSqlSession().selectOne(getStatementId("selectByPrimaryKey"), roleId);
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		return this.getSqlSession().update(getStatementId("updateByPrimaryKeySelective"), record);
	}

	@Override
	public int updateByPrimaryKey(T record) {
		return this.getSqlSession().update(getStatementId("updateByPrimaryKey"), record);
	}

}