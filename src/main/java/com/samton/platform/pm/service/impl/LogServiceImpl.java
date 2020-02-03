package com.samton.platform.pm.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.samton.platform.framework.mybatis.pagination.PageContext;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.pm.bean.entity.TSysLog;
import com.samton.platform.pm.bean.entity.TSysLogLogin;
import com.samton.platform.pm.dao.TSysLogLoginMapper;
import com.samton.platform.pm.dao.TSysLogMapper;
import com.samton.platform.pm.service.ILogService;

/**
 * 
 * @Description:日志管理Service实现类
 * @author:     shenchu
 * @date:        2017年2月22日 下午9:40:04
 * Copyright (c) 2017, Samton. All rights reserved
 */
@Service("logService")
public class LogServiceImpl implements ILogService {
	
	@Resource
	private TSysLogLoginMapper logLoginMapper;
	@Resource
	private TSysLogMapper logMapper;
	
	@Override
	public void addLogLogin(TSysLogLogin log) {
		logLoginMapper.insertSelective(log);
	}
	
	@Override
	public void addLog(TSysLog log) {
		logMapper.insertSelective(log);
	}

	@Override
	public Pagination<TSysLogLogin> queryLogLogins(Pagination<TSysLogLogin> paramBean, TSysLogLogin pmLogLogin) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if (pmLogLogin.getStartDateTime() == null) {
			pmLogLogin.setStartDateTime(sdfParse.parse(sdf.format(System.currentTimeMillis()) + " 00:00"));
		}
		if (pmLogLogin.getEndDateTime() == null) {
			pmLogLogin.setEndDateTime(sdfParse.parse(sdf.format(System.currentTimeMillis()) + " 23:59"));
		}
		paramBean.setSearch(pmLogLogin);
		Pagination<TSysLogLogin> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
		List<TSysLogLogin> pmLogLogins = logLoginMapper.queryLogLogins(paramBean, pagination.getRowBounds());
		pagination.setData(pmLogLogins);
		return pagination;
	}

	@Override
	public Pagination<TSysLog> queryOprLogs(Pagination<TSysLog> paramBean, TSysLog pmLogOpr) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if (pmLogOpr.getStartDateTime() == null) {
			pmLogOpr.setStartDateTime(sdfParse.parse(sdf.format(System.currentTimeMillis()) + " 00:00"));
		}
		if (pmLogOpr.getEndDateTime() == null) {
			pmLogOpr.setEndDateTime(sdfParse.parse(sdf.format(System.currentTimeMillis()) + " 23:59"));
		}
		paramBean.setSearch(pmLogOpr);
		Pagination<TSysLog> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
		List<TSysLog> pmLogs = logMapper.queryOprLogs(paramBean, pagination.getRowBounds());
		pagination.setData(pmLogs);
		return pagination;
	}

}
