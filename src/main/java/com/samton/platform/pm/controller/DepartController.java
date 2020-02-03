package com.samton.platform.pm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.samton.platform.framework.base.SdkBaseController;
import com.samton.platform.framework.constant.WebConstant;
import com.samton.platform.framework.exception.ServiceException;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.pm.bean.entity.TSysDepart;
import com.samton.platform.pm.bean.entity.TSysLog;
import com.samton.platform.pm.bean.entity.TSysPmUser;
import com.samton.platform.pm.constant.PmExpCodeConstant;
import com.samton.platform.pm.constant.PmStateConstant;
import com.samton.platform.pm.service.IDepartService;
import com.samton.platform.pm.service.ILogService;

/**
 * 
 * @Description:部门管理控制类
 * @author: shenchu
 * @date: 2017年2月22日 下午1:33:25 Copyright (c) 2017, Samton. All rights reserved
 */
@Controller
@RequestMapping("/platform/depart")
public class DepartController extends SdkBaseController {
	@Resource
	// 部门管理业务层
	public IDepartService departService;

	@Resource
	// 日志管理
	private ILogService logService;

	/**
	 * 
	 * @Title: queryDepartList
	 * @Description: 分页查询部门信息
	 * @param: @param paramBean 分页参数
	 * @param: @param pmDepart 部门查询条件
	 * @param: @return
	 * @param: @throws Exception
	 * @return: String
	 * @author shenchu
	 * @Date 2017年2月22日 下午1:34:27
	 */
	@RequestMapping("/queryDepartList"+WebConstant.PAGE_SUFFIX)
	public String queryDepartList(Pagination<TSysDepart> paramBean, TSysDepart pmDepart) throws Exception {
		if (pmDepart != null && pmDepart.getDepartName() != null) {
			pmDepart.setDepartName(pmDepart.getDepartName().trim());
		}
		paramBean.setSearch(pmDepart);
		Pagination<TSysDepart> pageData = departService.getPlatformPmDeparts(paramBean);
		List<TSysDepart> departs = departService.selectPlatformPmDeparts();
		this.request.setAttribute("pageData", pageData);
		this.request.setAttribute("mpDeparts", departs);
		this.request.setAttribute("pmDepart", pmDepart);
		return "depart/departManage";
	}

	/**
	 * 
	 * @Title: getDepartList
	 * @Description: 查询部门信息
	 * @param: @return
	 * @param: @throws Exception
	 * @return: List<TSysDepart>
	 * @author shenchu
	 * @Date 2017年2月22日 下午1:35:27
	 */
	@RequestMapping("/getDepartList" + WebConstant.NO_AUTH_SUFFIX)
	@ResponseBody
	public List<TSysDepart> getDepartList() throws Exception {
		return departService.selectPlatformPmDeparts();
	}

	/**
	 * 
	 * @Title:        getDepartUserList 
	 * @Description:  查询部门信息
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       List<TSysDepart>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午1:36:41
	 */
	@RequestMapping("/getDepartUserList"+ WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public List<TSysDepart> getDepartUserList() throws Exception {
		return departService.selectPlatformPmDeparts();
	}

	/**
	 * 
	 * @Title:        addDepart 
	 * @Description:  新增部门
	 * @param:        @param depart
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:18:18
	 */
	@RequestMapping("/addDepart" + WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> addDepart(TSysDepart depart) throws Exception {
		long result = departService.addTPlatformPmDepart(depart);
		if (result > 0) {
			logService.addLog(new TSysLog("部门管理-新增", "新增部门【"+ depart.getDepartName() + "】成功 ！", PmStateConstant.LOG_PLATFORM));
		} else {
			logService.addLog(new TSysLog("部门管理-新增", "新增部门【"+ depart.getDepartName() + "】失败 ！", PmStateConstant.LOG_PLATFORM));
		}
		if (result == -2) {
			throw new ServiceException(PmExpCodeConstant.PM_DEPART_DEPARTNAME_REPEAT);
		}
		return this.getResultMap(true, result);
	}

	/**
	 * 
	 * @Title:        delDepart 
	 * @Description:  删除部门
	 * @param:        @param departId
	 * @param:        @param departName
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:23:30
	 */
	@RequestMapping("/delDepart" + WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> delDepart(long departId, String departName)throws Exception {
		long result = departService.delTPlatformPmDepartById(departId);
		if (result > 0) {
			logService.addLog(new TSysLog("部门管理-删除", "删除部门【" + departName + "】成功  ！", PmStateConstant.LOG_PLATFORM));
		} else {
			logService.addLog(new TSysLog("部门管理-删除", "删除部门【" + departName + "】失败！", PmStateConstant.LOG_PLATFORM));
			throw new ServiceException(PmExpCodeConstant.PM_DEPART_USER_EXIST);
		}
		return this.getResultMap(true, result);
	}

	/**
	 * 
	 * @Title:        getDepartById 
	 * @Description:  通过ID查询部门
	 * @param:        @param departId
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:23:41
	 */
	@RequestMapping("/getDepartById" + WebConstant.NO_AUTH_SUFFIX)
	@ResponseBody
	public Map<String, Object> getDepartById(long departId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		TSysDepart depart = departService.getTPlatformPmDepartById(departId);
		List<TSysPmUser> users = departService.selectUserList();
		List<TSysDepart> departs = departService.selectPlatformPmDeparts();
		map.put("depart", depart);
		map.put("users", users);
		map.put("departs", departs);
		return this.getResultMap(true, map);
	}

	/**
	 * 
	 * @Title:        editDepart 
	 * @Description:  编辑部门
	 * @param:        @param depart
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:24:07
	 */
	@RequestMapping("/editDepart" + WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> editDepart(TSysDepart depart) throws Exception {
		long result = departService.updateTPlatformPmDepart(depart);
		if (result > 0) {
			logService.addLog(new TSysLog("部门管理-编辑", "编辑部门【" + depart.getDepartName() + "】成功 ！", PmStateConstant.LOG_PLATFORM));
		} else {
			logService.addLog(new TSysLog("部门管理-编辑", "编辑部门【" + depart.getDepartName() + "】 失败！", PmStateConstant.LOG_PLATFORM));
		}
		if (result == -2) {
			throw new ServiceException(PmExpCodeConstant.PM_DEPART_DEPARTNAME_REPEAT);
		}
		return this.getResultMap(true, result);
	}

	/**
	 * 
	 * @Title:        exportDepartList 
	 * @Description:  导出部门
	 * @param:        @param paramBean
	 * @param:        @param pmDepart
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       String    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:24:48
	 */
	@RequestMapping("/exportDepartList" + WebConstant.BUSINESS_SUFFIX)
	public String exportDepartList(Pagination<TSysDepart> paramBean, TSysDepart pmDepart) throws Exception {
		paramBean.setSearch(pmDepart);
		Pagination<Map<String, Object>> pageData = departService.exportDepartList(paramBean);
		logService.addLog(new TSysLog("部门管理-导出", "导出部门详情信息！", PmStateConstant.LOG_PLATFORM));
		String title = "部门名称,部门负责人,上级部门,备注";
		List<String> colNames = new ArrayList<String>();
		colNames.add("depart_name");
		colNames.add("manager_user_name");
		colNames.add("parent_depart_name");
		colNames.add("remark");
		// 数字转换成文字
		// colNames.add("state");
		// pageData.putExprotKV("state", "0", "删除");
		// pageData.putExprotKV("state", "1", "新增");
		this.export(response,"部门管理管理" + String.format("%1$tY%1$tm%1$td", new Date()), title, colNames, pageData);
		return null;
	}

	/**
	 * 
	 * @Title:        selectUserList 
	 * @Description:  获取所有用户
	 * @param:        @return    
	 * @return:       List<TSysPmUser>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:25:51
	 */
	@RequestMapping("selectUserList" + WebConstant.NO_AUTH_SUFFIX)
	@ResponseBody
	public List<TSysPmUser> selectUserList() {
		return departService.selectUserList();
	}
}
