package com.samton.platform.common.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.samton.platform.pm.bean.entity.TSysPmRole;
import com.samton.platform.pm.service.IPmService;

/**
 * @Description:测试共公类，主要测试后台方法。
 *              使用时：编写的java类继承该类使用junit测试即可。
 * @author:     Alex
 * @date:        2017年2月21日 下午4:07:11
 * Copyright (c) 2016, Samton. All rights reserved
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-container.xml")
public class SpringJunitTest {

	@Resource
	private IPmService pmService;
	
	/**
	 * 
	 * @Title:        test 
	 * @Description:  测试方法
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年2月21日 下午3:58:23
	 */
	@Test
	public void test(){
		TSysPmRole role=pmService.getRoleById(1183L);
		System.out.println(role.getRoleName());
	}
}
