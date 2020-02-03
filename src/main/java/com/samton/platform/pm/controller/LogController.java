/**
 * 
 */
package com.samton.platform.pm.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.samton.platform.framework.base.SdkBaseController;
import com.samton.platform.framework.constant.WebConstant;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.pm.bean.entity.TSysLog;
import com.samton.platform.pm.bean.entity.TSysLogLogin;
import com.samton.platform.pm.service.ILogService;

/**
 * @Description: 日志管理页面
 * @author: lijc
 * @date: 2017年2月6日 下午1:59:26 Copyright (c) 2017, Samton. All rights reserved
 */
@Controller
@RequestMapping("/platform/pm")
public class LogController extends SdkBaseController {

	@Resource
	private ILogService logService;

	/**
	 * @Title: queryLoginLogList
	 * @Description: 查询登录日志
	 * @param: @param paramBean
	 * @param: @param pmLogLogin
	 * @param: @return
	 * @param: @throws Exception
	 * @return: String
	 * @author lijc
	 * @Date 2017年1月17日 上午9:44:14
	 */
	@RequestMapping("/queryLoginLogList" + WebConstant.PAGE_SUFFIX)
	public String queryLoginLogList(Pagination<TSysLogLogin> paramBean, TSysLogLogin pmLogLogin) throws Exception {
		if (pmLogLogin != null) {
			if (pmLogLogin.getUserName() != null) {
				pmLogLogin.setUserName(pmLogLogin.getUserName().trim());
			}
		}
		Pagination<TSysLogLogin> pageData = logService.queryLogLogins(paramBean, pmLogLogin);
		this.request.setAttribute("pageData", pageData);
		this.request.setAttribute("pmLogLogin", pmLogLogin);
		return "log/logLoginManage";
	}

	/**
	 * @Title: queryOprLogList
	 * @Description: 查询操作日志
	 * @param: @param paramBean
	 * @param: @param pmLog
	 * @param: @return
	 * @param: @throws Exception
	 * @return: String
	 * @author lijc
	 * @Date 2017年1月17日 下午4:12:52
	 */
	@RequestMapping("/queryOprLogList" + WebConstant.PAGE_SUFFIX)
	public String queryOprLogList(Pagination<TSysLog> paramBean, TSysLog pmLog)throws Exception {
		if (pmLog != null) {
			if (pmLog.getUserName() != null) {
				pmLog.setUserName(pmLog.getUserName().trim());
			}
			if (pmLog.getModuleName() != null) {
				pmLog.setModuleName(pmLog.getModuleName().trim());
			}
		}
		Pagination<TSysLog> pageData = logService.queryOprLogs(paramBean, pmLog);
		this.request.setAttribute("pageData", pageData);
		this.request.setAttribute("pmLog", pmLog);
		return "log/logOprManage";
	}

}
