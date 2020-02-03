package com.samton.platform.framework.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * @Description:Spring配置文件
 * @author: Alex
 * @date: 2017年2月22日 下午2:57:58
 * Copyright (c) 2017, Samton. All rights reserved
 */
@Component
@SuppressWarnings({"rawtypes","unchecked"})
public class SpringContext implements ApplicationContextAware {

	// Spring应用上下文环境
	private static ApplicationContext applicationContext;

	// 实现ApplicationContextAware接口的回调方法，设置上下文环境
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContext.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 
	 * @Title:        getBean 
	 * @Description:  获取对象
	 * @param:        @param name
	 * @param:        @return					一个以所给名字注册的bean的实例
	 * @param:        @throws BeansException    
	 * @return:       Object    
	 * @author        Alex
	 * @Date          2017年2月22日 下午2:59:16
	 */
	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}

	/**
	 * 
	 * @Title:        getBean 
	 * @Description:  获取类型为requiredType的对象
	 * 				     如果bean不能被类型转换，相应的异常将会被抛出（BeanNotOfRequiredTypeException）
	 * @param:        @param name				bean注册名
	 * @param:        @param requiredType		返回对象类型
	 * @param:        @return					返回requiredType类型对象
	 * @param:        @throws BeansException    
	 * @return:       Object    
	 * @author        Alex
	 * @Date          2017年2月22日 下午3:00:45
	 */
	public static Object getBean(String name, Class requiredType) throws BeansException {
		return applicationContext.getBean(name, requiredType);
	}

	/**
	 * 
	 * @Title:        containsBean 
	 * @Description:  如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
	 * @param:        @param name
	 * @param:        @return    
	 * @return:       boolean    
	 * @author        Alex
	 * @Date          2017年2月22日 下午3:02:41
	 */
	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	/**
	 * 
	 * @Title:        isSingleton 
	 * @Description:  判断以给定名字注册的bean定义是一个singleton还是一个prototype。
	 * 				     如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
	 * @param:        @param name
	 * @param:        @return
	 * @param:        @throws NoSuchBeanDefinitionException    
	 * @return:       boolean    
	 * @author        Alex
	 * @Date          2017年2月22日 下午3:03:02
	 */
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.isSingleton(name);
	}

	/**
	 * 
	 * @Title:        getType 
	 * @Description:  获得注册对象的类型
	 * @param:        @param name
	 * @param:        @return									注册对象的类型
	 * @param:        @throws NoSuchBeanDefinitionException    
	 * @return:       Class    
	 * @author        Alex
	 * @Date          2017年2月22日 下午3:03:39
	 */
	public static Class getType(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.getType(name);
	}

	/**
	 * 
	 * @Title:        getAliases 
	 * @Description:  如果给定的bean名字在bean定义中有别名，则返回这些别名
	 * @param:        @param name
	 * @param:        @return
	 * @param:        @throws NoSuchBeanDefinitionException    
	 * @return:       String[]    
	 * @author        Alex
	 * @Date          2017年2月22日 下午3:04:13
	 */
	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.getAliases(name);
	}
}
