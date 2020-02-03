package com.samton.platform.pm.service;

import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.pm.bean.entity.TSysLog;
import com.samton.platform.pm.bean.entity.TSysLogLogin;

/**
 * 
 * @Description:日志管理Service
 * @author:     shenchu
 * @date:        2017年2月22日 下午9:39:15
 * Copyright (c) 2017, Samton. All rights reserved
 */
public interface ILogService {
	
	/**
	 * @Title:       queryLogLogins 
	 * @Description: 查询登录日志列表
	 * @param:       @param paramBean
	 * @param:       @param pmLogLogin
	 * @param:       @return
	 * @param:       @throws Exception    
	 * @return:      Pagination<TSysLogLogin>    
	 * @author       lijc
	 * @Date         2017年1月17日 下午3:48:30
	 */
	Pagination<TSysLogLogin> queryLogLogins(Pagination<TSysLogLogin> paramBean, TSysLogLogin pmLogLogin) throws Exception;
	
	/**
	 * 
	 * @Title:        addLogLogin 
	 * @Description:  添加登陆日志
	 * @param:        @param log    
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年1月17日 上午11:45:46
	 */
	void addLogLogin(TSysLogLogin log);
	
	/**
	 * @Title:       queryOprLogs 
	 * @Description: 查询操作日志列表
	 * @param:       @param paramBean
	 * @param:       @param pmLogOpr
	 * @param:       @return
	 * @param:       @throws Exception    
	 * @return:      Pagination<TSysLog>    
	 * @author       lijc
	 * @Date         2017年1月17日 下午4:18:12
	 */
	Pagination<TSysLog> queryOprLogs(Pagination<TSysLog> paramBean, TSysLog pmLogOpr) throws Exception;
	
	/**
	 * 
	 * @Title:        addLog 
	 * @Description:  添加操作日志
	 * @param:        @param log    
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年1月17日 下午5:15:27
	 */
	void addLog(TSysLog log);

}
