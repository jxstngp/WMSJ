/**
 * 
 */
package com.samton.platform.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.samton.platform.common.bean.entity.TSysCity;
import com.samton.platform.common.bean.entity.TSysProvince;
import com.samton.platform.common.service.ICommonService;
import com.samton.platform.framework.base.BaseController;
import com.samton.platform.framework.constant.WebConstant;
import com.samton.platform.pm.bean.entity.TSysDepart;
import com.samton.platform.pm.bean.entity.TSysPmUser;

/**
 * @Description:共用方法控制类
 * @author:     shenchu
 * @date:        2017年3月1日 下午9:39:51
 * Copyright (c) 2017, Samton. All rights reserved
 */
@Controller
@RequestMapping("/platform/common")
public class CommonController extends BaseController {
	
	@Resource
	public ICommonService commonService;

	/**
	 * 
	 * @Title:        personnelSelectorData 
	 * @Description:  人员选择器数据
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       String    
	 * @author        shenchu
	 * @Date          2017年3月2日 下午4:48:29
	 */
	@RequestMapping("/personnelSelectorData")
	@ResponseBody
	public Map<String, Object> personnelSelectorData() throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		List<TSysPmUser> users = commonService.getUsersForSelect();
		List<TSysDepart> depart = commonService.getDepartForSelect();
		data.put("users", users);
		data.put("depart", depart);
		return data;
	}
	
	/**
	 * 
	 * @Title:        queryUserName 
	 * @Description:  查询用户信息集合，数量不超过10个
	 * @param:        @param name
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       List<Map<String,Object>>    
	 * @author        shenchu
	 * @Date          2017年3月4日 下午5:04:19
	 */
	@RequestMapping("/queryUserName")
	@ResponseBody
	public List<Map<String,Object>>  queryUserName(String name)throws Exception{
		List<Map<String,Object>> userList = commonService.queryUserName(name);
		return userList;
	}
	
	/**
	 * 
	 * @Title:        getProvince 
	 * @Description:  获取省信息
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       List<TSysProvince>    
	 * @author        shenchu
	 * @Date          2017年3月2日 下午1:23:04
	 */
	@RequestMapping("/getProvince" + WebConstant.NO_AUTH_SUFFIX)
	@ResponseBody
	public List<TSysProvince> getProvince() throws Exception {
		return commonService.getProvince();
	}
	
	/**
	 * 
	 * @Title:        getCityByProvinceId 
	 * @Description:  通过省ID获取市信息
	 * @param:        @param provinceId
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       List<TSysCity>    
	 * @author        shenchu
	 * @Date          2017年3月2日 下午1:25:30
	 */
	@RequestMapping("/getCityByProvinceId" + WebConstant.NO_AUTH_SUFFIX)
	@ResponseBody
	public List<TSysCity> getCityByProvinceId(Long provinceId) throws Exception {
		return commonService.getCityByProvinceId(provinceId);
	}
	
	/**
	 * 
	 * @Title:        fileUpload 
	 * @Description:  单文件上传
	 * @param:        @param file
	 * @param:        @param request
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年3月6日 上午11:43:26
	 */
	@RequestMapping(value = "/fileUpload")
	@ResponseBody
	public Map<String, Object> fileUpload(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request) throws Exception{
		Map<String,Object> result = commonService.uploadFileSingle(file);
		return this.getResultMap(result.isEmpty()?false:true,result);
	}

	@RequestMapping(value = "/download")
	public String download(String url, HttpServletRequest request, HttpServletResponse response) {
		url = url.substring(1);
		
		String fileName = url.substring(url.lastIndexOf('/') + 1);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
		try {
			String path = request.getSession().getServletContext().getRealPath("/")  + url;
			InputStream inputStream = new FileInputStream(new File(path));

			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}

			 // 这里主要关闭。
			os.close();

			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
            //  返回值要注意，要不然就出现下面这句错误！
            //java+getOutputStream() has already been called for this response
		return null;
	}
	
	/**
	 * 
	 * @Title:        openAudioView 
	 * @Description:  跳转音频播放界面
	 * @param:        @param url			音频文件地址
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年4月11日 下午2:57:29
	 */
	@RequestMapping("/openAudioView")
	public String openAudioView(String url) throws Exception{
		this.request.setAttribute("audioUrl", url);
		return "common/audio";
	}

}
