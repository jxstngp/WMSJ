package com.samton.platform.framework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 
 * @Description:图片上传工具类
 * @author:     Alex
 * @date:        2017年2月23日 上午10:28:42
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class FileUploadUtil {

	/**
	 * 
	 * @Title:        getData 
	 * @Description:  读取文件中的信息，并把信息存入数组中
	 * @param:        @param file
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       List<String[]>    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:29:08
	 */
	public static List<String[]> getData(CommonsMultipartFile file) throws Exception {
		BufferedReader input = null;
		try {
			if (!file.isEmpty()) {
				List<String[]> arrayData = new ArrayList<String[]>();
				input = new BufferedReader(new InputStreamReader(file.getInputStream(), "GBK"));
				String lineText;
				while ((lineText = input.readLine()) != null) {
					String[] lineArray = lineText.split(",", -1);
					arrayData.add(lineArray);
				}
				return arrayData;
			}
		} catch (Exception e) {
		} finally {
			if (null != input) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;

	}
}
