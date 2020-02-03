package com.samton.platform.pm.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.samton.platform.common.util.BeanPropFilterUtil;
import com.samton.platform.framework.base.SdkBaseController;
import com.samton.platform.framework.constant.WebConstant;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.framework.util.CurrentUtil;
import com.samton.platform.pm.bean.entity.TSysDictInfo;
import com.samton.platform.pm.bean.entity.TSysDictType;
import com.samton.platform.pm.bean.entity.TSysLog;
import com.samton.platform.pm.constant.PmStateConstant;
import com.samton.platform.pm.service.IDictService;
import com.samton.platform.pm.service.ILogService;

/**
 * @Description: 字典管理和字典类型管理
 * @author: lijc
 * @date: 2017年2月7日 下午3:49:03 Copyright (c) 2017, Samton. All rights reserved
 */
@Controller
@RequestMapping("/platform/dict")
public class DictController extends SdkBaseController {

	@Resource
	private IDictService dictService;
	
	@Resource
	private ILogService logService;

	/**
	 * @Title: queryDictTypeList
	 * @Description: 查询字典类型列表方法
	 * @param: @param dictType
	 * @param: @return
	 * @return: String
	 * @author lijc
	 * @Date 2017年2月7日 下午3:57:27
	 */
	@RequestMapping("/queryDictTypeList" + WebConstant.PAGE_SUFFIX)
	public String queryDictTypeList(Pagination<TSysDictType> paramBean, TSysDictType dictType) {
		if (!StringUtils.isEmpty(dictType)) {
			if (!"".equals(dictType.getTypeName()) && null != dictType.getTypeName()) {
				dictType.setTypeName(dictType.getTypeName().trim());
			}
		}
		paramBean.setSearch(dictType);
		Pagination<TSysDictType> pageData = dictService.queryDictTypeList(paramBean);
		this.request.setAttribute("pageData", pageData);
		this.request.setAttribute("dictType", dictType);
		return "dict/dictTypeList";
	}

	/**
	 * @Title: getDictTypeList
	 * @Description: 获取所有字典类型
	 * @param: @param paramBean
	 * @param: @param dictType
	 * @param: @return
	 * @return: String
	 * @author lijc
	 * @Date 2017年2月8日 下午3:21:21
	 */
	@RequestMapping("/getDictTypeList" + WebConstant.NO_AUTH_SUFFIX)
	@ResponseBody
	public List<TSysDictType> getDictTypeList() throws Exception {
		TSysDictType dictType = new TSysDictType();
		return dictService.getDictTypeList(dictType);
	}

	/**
	 * @Title: getDictType
	 * @Description: 根据类型ID获取字典类型数据
	 * @param: @param typeId
	 * @param: @return
	 * @param: @throws Exception
	 * @return: Map<String,Object>
	 * @author lijc
	 * @Date 2017年2月8日 上午10:48:44
	 */
	@RequestMapping("/getDictType" + WebConstant.NO_AUTH_SUFFIX)
	@ResponseBody
	public Map<String, Object> getDictType(long typeId) throws Exception {
		TSysDictType dictType = dictService.getDictType(typeId);
		BeanPropFilterUtil.convertBeanByPersistProps(dictType, Arrays.asList("typeId", "typeName", "remark"));
		return this.getResultMap(dictType);
	}

	/**
	 * @Title: addDictType
	 * @Description: 新增字典类型
	 * @param: @param dictType
	 * @param: @return
	 * @return: Map<String,Object>
	 * @author lijc
	 * @throws Exception
	 * @Date 2017年2月7日 下午5:21:39
	 */
	@RequestMapping("/addDictType" + WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> addDictType(TSysDictType dictType) throws Exception {
		long flag = dictService.addDictType(dictType);
		if (flag > 0) {
			logService.addLog(new TSysLog("字典类型管理-新增", "新增字典类型【"+ dictType.getTypeName() +"】成功！", PmStateConstant.LOG_PLATFORM));
		} else {
			logService.addLog(new TSysLog("字典类型管理-新增", "新增字典类型【"+ dictType.getTypeName() +"】失败！", PmStateConstant.LOG_PLATFORM));
		}
		return this.getResultMap(true, flag);
	}

	/**
	 * @Title: updateDictType
	 * @Description: 编辑字典类型
	 * @param: @param dictType
	 * @param: @return
	 * @param: @throws Exception
	 * @return: Map<String,Object>
	 * @author lijc
	 * @Date 2017年2月8日 上午10:57:20
	 */
	@RequestMapping("/updateDictType" + WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> updateDictType(TSysDictType dictType) throws Exception {
		boolean flag = dictService.updateDictType(dictType);
		if (flag) {
			logService.addLog(new TSysLog("字典类型管理-编辑", "编辑字典类型【"+ dictType.getTypeName() +"】成功！", PmStateConstant.LOG_PLATFORM));
		} else {
			logService.addLog(new TSysLog("字典类型管理-编辑", "编辑字典类型【"+ dictType.getTypeName() +"】失败！", PmStateConstant.LOG_PLATFORM));
		}
		return this.getResultMap(flag);
	}

	/**
	 * @Title: delDictType
	 * @Description: 删除字典类型
	 * @param: @param typeId
	 * @param: @return
	 * @param: @throws Exception
	 * @return: Map<String,Object>
	 * @author lijc
	 * @Date 2017年2月8日 上午11:14:20
	 */
	@RequestMapping("/delDictType" + WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> delDictType(long typeId, String typeName) throws Exception {
		boolean flag = dictService.delDictType(typeId);
		if (flag) {
			logService.addLog(new TSysLog("字典类型管理-删除", "删除字典类型【"+ typeName +"】成功！", PmStateConstant.LOG_PLATFORM));
		} else {
			logService.addLog(new TSysLog("字典类型管理-删除", "删除字典类型【"+ typeName +"】失败！", PmStateConstant.LOG_PLATFORM));
		}
		return this.getResultMap(flag);
	}

	/**
	 * @Title: queryDictInfoList
	 * @Description: 查询字典列表方法
	 * @param: @param dictInfo
	 * @param: @return
	 * @return: String
	 * @author lijc
	 * @Date 2017年2月7日 下午3:57:27
	 */
	@RequestMapping("/queryDictInfoList" + WebConstant.PAGE_SUFFIX)
	public String queryDictInfoList(Pagination<TSysDictInfo> paramBean, TSysDictInfo dictInfo) {
		if (!StringUtils.isEmpty(dictInfo)) {
			if (!"".equals(dictInfo.getDictName()) && null != dictInfo.getDictName()) {
				dictInfo.setDictName(dictInfo.getDictName().trim());
			}
		}
		paramBean.setSearch(dictInfo);
		Pagination<TSysDictInfo> pageData = dictService.queryDictInfoList(paramBean);
		TSysDictType dictType = new TSysDictType();
		List<TSysDictType> dictTypes = dictService.getDictTypeList(dictType);
		this.request.setAttribute("pageData", pageData);
		this.request.setAttribute("dictInfo", dictInfo);
		this.request.setAttribute("dictTypes", dictTypes);
		return "dict/dictInfoList";
	}

	/**
	 * @Title: getDictInfo
	 * @Description: 根据类型ID获取字典数据
	 * @param: @param dictId
	 * @param: @return
	 * @param: @throws Exception
	 * @return: Map<String,Object>
	 * @author lijc
	 * @Date 2017年2月8日 上午10:48:44
	 */
	@RequestMapping("/getDictInfo" + WebConstant.NO_AUTH_SUFFIX)
	@ResponseBody
	public Map<String, Object> getDictInfo(long dictId) throws Exception {
		TSysDictInfo dictInfo = dictService.getDictInfo(dictId);
		BeanPropFilterUtil.convertBeanByPersistProps(dictInfo, Arrays.asList("dictId", "dictName", "typeId", "sort", "remark"));
		return this.getResultMap(dictInfo);
	}

	/**
	 * @Title: addDictInfo
	 * @Description: 新增字典
	 * @param: @param dictInfo
	 * @param: @return
	 * @return: Map<String,Object>
	 * @author lijc
	 * @throws Exception
	 * @Date 2017年2月7日 下午5:21:39
	 */
	@RequestMapping("/addDictInfo" + WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> addDictInfo(TSysDictInfo dictInfo) throws Exception {
		long flag = dictService.addDictInfo(dictInfo);
		if (flag > 0) {
			logService.addLog(new TSysLog("字典管理-新增", "新增字典【"+ dictInfo.getDictName() +"】成功！", PmStateConstant.LOG_PLATFORM));
		} else {
			logService.addLog(new TSysLog("字典管理-新增", "新增字典【"+ dictInfo.getDictName() +"】失败！", PmStateConstant.LOG_PLATFORM));
		}
		return this.getResultMap(true, flag);
	}

	/**
	 * @Title: updateDictInfo
	 * @Description: 编辑字典
	 * @param: @param dictInfo
	 * @param: @return
	 * @param: @throws Exception
	 * @return: Map<String,Object>
	 * @author lijc
	 * @Date 2017年2月8日 上午10:57:20
	 */
	@RequestMapping("/updateDictInfo" + WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> updateDictInfo(TSysDictInfo dictInfo) throws Exception {
		boolean flag = dictService.updateDictInfo(dictInfo);
		if (flag) {
			logService.addLog(new TSysLog("字典管理-编辑", "编辑字典【"+ dictInfo.getDictName() +"】成功！", PmStateConstant.LOG_PLATFORM));
		} else {
			logService.addLog(new TSysLog("字典管理-编辑", "编辑字典【"+ dictInfo.getDictName() +"】失败！", PmStateConstant.LOG_PLATFORM));
		}
		return this.getResultMap(flag);
	}

	/**
	 * @Title: delDictInfo
	 * @Description: 删除字典
	 * @param: @param dictId
	 * @param: @return
	 * @param: @throws Exception
	 * @return: Map<String,Object>
	 * @author lijc
	 * @Date 2017年2月8日 上午11:14:20
	 */
	@RequestMapping("/delDictInfo" + WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> delDictInfo(long dictId, String dictName) throws Exception {
		boolean flag = dictService.delDictInfo(dictId);
		if (flag) {
			logService.addLog(new TSysLog("字典管理-删除", "删除字典【"+ dictName +"】成功！", PmStateConstant.LOG_PLATFORM));
		} else {
			logService.addLog(new TSysLog("字典管理-删除", "删除字典【"+ dictName +"】失败！", PmStateConstant.LOG_PLATFORM));
		}
		return this.getResultMap(flag);
	}

}
