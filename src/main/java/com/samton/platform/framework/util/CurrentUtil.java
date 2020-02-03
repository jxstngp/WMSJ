package com.samton.platform.framework.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.samton.platform.framework.base.BaseBean;
import com.samton.platform.framework.bean.UserCacheBean;

/**
 * 
 * @Description:全局变量参数
 * @author:     Alex
 * @date:        2017年2月23日 上午9:18:41
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class CurrentUtil {

	private static UserCacheBean userCacheBean;

	private static final ThreadLocal<UserCacheBean> userHolder = new ThreadLocal<UserCacheBean>();
	private static final ThreadLocal<Map<String, Object>> currentObjHolder = new ThreadLocal<Map<String, Object>>();

	static {
		userCacheBean = new UserCacheBean();
		userCacheBean.setEnterpriseId(1L);
		userCacheBean.setUserId(1L);
		userCacheBean.setLoginName("customer");
		userCacheBean.setUserName("游客");
		userCacheBean.setSystemId(1L);
		userCacheBean.setProxyId(0L);
	}

	public static void setCurrentObj(String key, Object obj) {
		Map<String, Object> currentMap = currentObjHolder.get();
		if (currentMap == null) {
			currentMap = new HashMap<String, Object>();
		}
		currentMap.put(key, obj);
	}

	/**
	 * 
	 * @Title:        getCurrentObj 
	 * @Description:  根据KEY获得对于参数
	 * @param:        @param key
	 * @param:        @return    
	 * @return:       Object    
	 * @author        Alex
	 * @Date          2017年2月23日 上午9:27:44
	 */
	public static Object getCurrentObj(String key) {
		Map<String, Object> currentMap = currentObjHolder.get();
		if (currentMap == null)
			return null;
		return currentObjHolder.get().get(key);
	}

	/**
	 * 
	 * @Title:        setCurrentUser 
	 * @Description:  设置当提登录用户信息
	 * @param:        @param userCacheBean    
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年2月23日 上午9:28:08
	 */
	public static void setCurrentUser(UserCacheBean userCacheBean) {
		userHolder.set(userCacheBean);
	}

	/**
	 * 
	 * @Title:        removeCurrentUser 
	 * @Description:  移除登当前登录信息
	 * @param:            
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年2月23日 上午9:28:24
	 */
	public static void removeCurrentUser() {
		userHolder.remove();
	}

	/**
	 * 
	 * @Title:        getCurrentUser 
	 * @Description:  获取当前登录用户信息
	 * @param:        @return    
	 * @return:       UserCacheBean    
	 * @author        Alex
	 * @Date          2017年2月23日 上午9:28:46
	 */
	public static UserCacheBean getCurrentUser() {
		return userHolder.get();
	}

	private static UserCacheBean getDefaultUser() {
		return userCacheBean;
	}

	/**
	 * 
	 * @Title:        setBaseBeanByInsert 
	 * @Description:  自动设置数据库对象bean插入时所必需的createUserId、createUserName、createDate、lastModifyDate属性
	 * @param:        @param obj    
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年2月23日 上午9:29:19
	 */
	public static void setBaseBeanByInsert(Object obj) {
		if (obj instanceof BaseBean) {
			UserCacheBean userCacheBean = getCurrentUser();
			if (userCacheBean == null) {
				userCacheBean = getDefaultUser();
			}
			((BaseBean) obj).setCreateUserId(userCacheBean.getUserId());
			((BaseBean) obj).setCreateUserName(userCacheBean.getUserName());
			Date nowDate = new Date();
			((BaseBean) obj).setCreateDate(nowDate);
		}
	}

	/**
	 * 
	 * @Title:        setBaseBeanByModify 
	 * @Description:  自动设置数据库对象bean更新时所必需的modifyUserId、modifyUserName、modifyDate、lastModifyDate属性
	 * @param:        @param obj    
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年2月23日 上午9:30:07
	 */
	public static void setBaseBeanByModify(Object obj) {
		if (obj instanceof BaseBean) {
			UserCacheBean userCacheBean = getCurrentUser();
			if (userCacheBean == null) {
				userCacheBean = getDefaultUser();
			}
			((BaseBean) obj).setModifyUserId(userCacheBean.getUserId());
			((BaseBean) obj).setModifyUserName(userCacheBean.getUserName());
			Date nowDate = new Date();
			((BaseBean) obj).setModifyDate(nowDate);
		}
	}

	/**
	 * 
	 * @Title:        getCurrentSystemId 
	 * @Description:  获得系统ID
	 * @param:        @return    
	 * @return:       Long    
	 * @author        Alex
	 * @Date          2017年2月23日 上午9:33:00
	 */
	public static Long getCurrentSystemId() {
		return 0L;
	}

}
