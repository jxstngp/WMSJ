/**
 * 
 */
package com.samton.platform.common.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.samton.platform.common.bean.entity.TSysCity;
import com.samton.platform.common.bean.entity.TSysProvince;
import com.samton.platform.common.constant.CommonConstant;
import com.samton.platform.common.dao.TSysCityMapper;
import com.samton.platform.common.dao.TSysProvinceMapper;
import com.samton.platform.common.service.ICommonService;
import com.samton.platform.framework.util.DateUtil;
import com.samton.platform.pm.bean.entity.TSysDepart;
import com.samton.platform.pm.bean.entity.TSysPmUser;
import com.samton.platform.pm.dao.TSysDepartMapper;
import com.samton.platform.pm.dao.TSysPmUserMapper;

/**
 * @Description:共用相关Service实现类
 * @author:     shenchu
 * @date:        2017年3月1日 下午9:42:06
 * Copyright (c) 2017, Samton. All rights reserved
 */
@Service("commonService")
public class CommonServiceImpl implements ICommonService {

	@Resource
	private TSysProvinceMapper provinceMapper;

	@Resource
	private TSysCityMapper cityMapper;
	
	@Resource
	private TSysPmUserMapper userMapper;

	@Resource
	private TSysDepartMapper departMapper;
	
	@Override
	public List<TSysProvince> getProvince() {
		return provinceMapper.getProvince();
	}

	@Override
	public List<TSysCity> getCityByProvinceId(Long provinceId) {
		return cityMapper.getCityByProvinceId(provinceId);
	}

	@Override
	public List<TSysPmUser> getUsersForSelect() {
		return userMapper.getUsersForSelect();
	}

	@Override
	public List<TSysDepart> getDepartForSelect() {
		return departMapper.selectDepartListForSelect();
	}

	@Override
	public List<Map<String, Object>> queryUserName(String name) {
		return userMapper.queryUserName(name);
	}

	@Override
	public Map<String, Object> uploadFileSingle(CommonsMultipartFile file) {
		Map<String,Object> result = new HashMap<String, Object>();
		//上传到服务器路径
		String filePath  = CommonConstant.baseUploadPath;
		//得到原文件名
		String originalName = file.getOriginalFilename();
		//得到文件后缀名
		String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		//非法文件处理 TODO
		
		//统一处理后缀名称
		if(StringUtils.isNotEmpty(suffixName)){
			suffixName = suffixName.toLowerCase();
		}
		
		//新的图片名称
		String newFileName = DateUtil.currentDatetimeNum() + ((int)((Math.random()*9+1)*100000)) + suffixName;
		//得到文件大小 单位为kb
		Long fileSize = file.getSize() / 1024;
		if(fileSize==0) fileSize=1L;
		
		//新文件全路径
		filePath = filePath + File.separator + newFileName;
		File newFile = new File(filePath);
		if(!newFile.getParentFile().exists()){
			newFile.getParentFile().mkdirs();
		}		
		//将内存中的文件写入磁盘
		try {
			file.transferTo(newFile);
			//构建返回值
			String relativePath =  CommonConstant.imgUrl  + newFileName;
			result.put("attachmentName", originalName);
			result.put("attachmentPath", relativePath);
			result.put("attachmentSuffix", suffixName);
			result.put("attachmentSize", fileSize);
			
		} catch (IllegalStateException | IOException e) {
			
		}		
		return result;
	}

}
