package com.samton.platform.pm.controller;

import com.samton.platform.framework.base.SdkBaseController;
import com.samton.platform.framework.constant.WebConstant;
import com.samton.platform.framework.exception.ServiceException;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.pm.bean.entity.TAdvertise;
import com.samton.platform.pm.bean.entity.TSysLog;
import com.samton.platform.pm.bean.vo.TAdvertiseVo;
import com.samton.platform.pm.constant.PmExpCodeConstant;
import com.samton.platform.pm.constant.PmStateConstant;
import com.samton.platform.pm.service.IAdvertiseService;
import com.samton.platform.pm.service.ILogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * 
 * @Description:部门管理控制类
 * @author: shenchu
 * @date: 2017年2月22日 下午1:33:25 Copyright (c) 2017, Samton. All rights reserved
 */
@Controller
@RequestMapping("/platform/advertise")
public class AdvertiseController extends SdkBaseController {
	@Resource
	// 广告管理业务层
	public IAdvertiseService advertiseService;

	@Resource
	// 日志管理
	private ILogService logService;

	/**
	 * 
	 * @Title: queryDepartList
	 * @Description: 分页查询广告信息
	 * @param: @param paramBean 分页参数
	 * @param: @param pmDepart 广告查询条件
	 * @param: @return
	 * @param: @throws Exception
	 * @return: String
	 * @author zhixin
	 * @Date 2019年10月24日 下午1:34:27
	 */
	@RequestMapping("/queryAdvertiseList"+WebConstant.PAGE_SUFFIX)
	public String queryAdvertiseList(Pagination<TAdvertiseVo> paramBean, TAdvertiseVo advertiseVo) throws Exception {
		paramBean.setSearch(advertiseVo);
		Pagination<TAdvertiseVo> pageData = advertiseService.queryPageListVo(paramBean);
		this.request.setAttribute("pageData", pageData);
		this.request.setAttribute("advertiseVo", advertiseVo);
		return "sys/advertiseManage";
	}





	/**
	 * 
	 * @Title:        addAdvertise
	 * @Description:  新增广告
	 * @param:        @param depart
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        zhixin
	 * @Date
	 */
	@RequestMapping("/addAdvertise" + WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> addAdvertise(TAdvertise advertise) throws Exception {
		long result = advertiseService.addAdvertise(advertise);
		if (result > 0) {
			logService.addLog(new TSysLog("广告管理-新增", "新增广告位置【"+ advertise.getPosition() + "】成功 ！", PmStateConstant.LOG_PLATFORM));
		} else {
			logService.addLog(new TSysLog("广告管理-新增", "新增广告位置【"+ advertise.getPosition() + "】失败 ！", PmStateConstant.LOG_PLATFORM));
		}
		if (result == -2) {
			throw new ServiceException(PmExpCodeConstant.PM_ADVERTISE_ADD_REPEAT_ERROR);
		}
		return this.getResultMap(true, result);
	}

	/**
	 * 
	 * @Title:        delAdvertise
	 * @Description:  删除广告
	 * @param:        @param advertiseVo
	 * @param:        @param
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        zhixin
	 * @Date
	 */
	@RequestMapping("/delAdvertise" + WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> delAdvertise(TAdvertise advertise)throws Exception {
		long result = advertiseService.delAdvertise(advertise);
		if (result > 0) {
			logService.addLog(new TSysLog("广告管理-删除", "删除广告位置【" + advertise.getPosition() + "】成功  ！", PmStateConstant.LOG_PLATFORM));
		} else {
			logService.addLog(new TSysLog("广告管理-删除", "删除广告位置【" + advertise.getPosition() + "】失败！", PmStateConstant.LOG_PLATFORM));
			throw new ServiceException(PmExpCodeConstant.PM_ADVERTISE_DEL_NULL_ERROR);
		}
		return this.getResultMap(true, result);
	}

	/**
	 * 
	 * @Title:        getAdvertiseById
	 * @Description:  通过ID查询广告
	 * @param:        @param departId
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        zhixin
	 * @Date
	 */
	@RequestMapping("/getAdvertiseById" + WebConstant.NO_AUTH_SUFFIX)
	@ResponseBody
	public Map<String, Object> getAdvertiseById(Integer advertiseId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		TAdvertiseVo advertise = advertiseService.loadAdvertiseById(advertiseId);
		map.put("advertise", advertise);
		return this.getResultMap(true, map);
	}

	/**
	 * 
	 * @Title:        editAdvertise
	 * @Description:  编辑广告
	 * @param:        @param depart
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        zhixin
	 * @Date
	 */
	@RequestMapping("/editAdvertise" + WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> editAdvertise(TAdvertise advertise) throws Exception {
		long result = advertiseService.editAdvertise(advertise);
		if (result > 0) {
			logService.addLog(new TSysLog("广告管理-编辑", "编辑广告位置【" + advertise.getPosition() + "】成功 ！", PmStateConstant.LOG_PLATFORM));
		} else {
			logService.addLog(new TSysLog("广告管理-编辑", "编辑广告位置【" + advertise.getPosition() + "】 失败！", PmStateConstant.LOG_PLATFORM));
		}
		if (result == -2) {
			throw new ServiceException(PmExpCodeConstant.PM_DEPART_DEPARTNAME_REPEAT);
		}
		return this.getResultMap(true, result);
	}

	/**
	 * 
	 * @Title:        exportAdvertiseList
	 * @Description:  导出部门
	 * @param:        @param paramBean
	 * @param:        @param pmDepart
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       String    
	 * @author        zhixin
	 * @Date          2017年2月22日 下午2:24:48
	 */
	@RequestMapping("/exportAdvertiseList" + WebConstant.BUSINESS_SUFFIX)
	public String exportAdvertiseList(Pagination<TAdvertiseVo> paramBean, TAdvertiseVo advertiseVo) throws Exception {
		paramBean.setSearch(advertiseVo);
		Pagination<Map<String, Object>> pageData = advertiseService.exportPageListVo(paramBean);
		logService.addLog(new TSysLog("广告管理-导出", "导出广告详情信息！", PmStateConstant.LOG_PLATFORM));
		String title = "广告位置,链接地址,备注,是否禁用";
		List<String> colNames = new ArrayList<String>();
		colNames.add("posited");
		colNames.add("link");
		colNames.add("remark");
		colNames.add("forbid");
		// 数字转换成文字
		// colNames.add("state");
		// pageData.putExprotKV("state", "0", "删除");
		// pageData.putExprotKV("state", "1", "新增");
		this.export(response,"广告管理" + String.format("%1$tY%1$tm%1$td", new Date()), title, colNames, pageData);
		return null;
	}

	/**
	 * @Title: disAndEnableAdvertise
	 * @Description: 禁用/启用广告位置
	 * @param: @param userId 用户ID
	 * @param: @param status 禁用/启用
	 * @param: @return
	 * @param: @throws Exception
	 * @return: Map<String,Object>
	 * @author zhixin
	 * @Date
	 */
	@RequestMapping("/disAndEnableAdvertise"+WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> disAndEnableAdvertise(TAdvertise advertise, short status) throws Exception {
		TAdvertise tAdvertise = new TAdvertise();
		tAdvertise.setAdvertiseId(advertise.getAdvertiseId());
		tAdvertise.setIsForbid(status);
		TAdvertiseVo advertises = advertiseService.loadAdvertiseById(advertise.getAdvertiseId());
		if (status == 1) {
			logService.addLog(new TSysLog("广告管理-启用", "启用广告位置【" + advertises.getPosition() + "】成功！", PmStateConstant.LOG_PLATFORM));
		} else {
			logService.addLog(new TSysLog("广告管理-禁用", "禁用广告位置【" + advertises.getPosition()+ "】成功！", PmStateConstant.LOG_PLATFORM));
		}
		return this.getResultMap(advertiseService.editAdvertise(tAdvertise));
	}
}
