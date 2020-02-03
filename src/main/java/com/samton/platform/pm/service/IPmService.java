package com.samton.platform.pm.service;

import java.util.List;
import java.util.Map;

import com.samton.platform.framework.exception.ServiceException;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.pm.bean.entity.TSysParam;
import com.samton.platform.pm.bean.entity.TSysPmMenu;
import com.samton.platform.pm.bean.entity.TSysPmRole;
import com.samton.platform.pm.bean.entity.TSysPmUser;

/**
 * 
 * @Description:权限相关Service接口
 * @author:     shenchu
 * @date:        2017年2月22日 下午9:40:52
 * Copyright (c) 2017, Samton. All rights reserved
 */
public interface IPmService {
	
	/**
	 * 
	 * @Title:        getMenusBySystemId 
	 * @Description:  根据菜单状态和所属系统id获取菜单列表
	 * @param:        @param systemId
	 * @param:        @param state
	 * @param:        @return    
	 * @return:       List<TSysPmMenu>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午9:41:09
	 */
    List<TSysPmMenu> getMenusBySystemId(long systemId,short state);
    
    /**
     * 
     * @Title:        getMenusByMenuTypes 
     * @Description:  根据菜单类型、状态列表获取菜单列表
     * @param:        @param menuTypes
     * @param:        @param states
     * @param:        @return    
     * @return:       List<TSysPmMenu>    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:41:18
     */
    List<TSysPmMenu> getMenusByMenuTypes(List<Short> menuTypes,List<Short> states);

    /**
     * 
     * @Title:        addMenu 
     * @Description:  新增菜单
     * @param:        @param menu
     * @param:        @return
     * @param:        @throws ServiceException    
     * @return:       long    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:41:31
     */
    long addMenu(TSysPmMenu menu) throws ServiceException;
    
    /**
     * 
     * @Title:        updateMenu 
     * @Description:  更新菜单
     * @param:        @param menu
     * @param:        @return
     * @param:        @throws ServiceException    
     * @return:       boolean    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:41:44
     */
    boolean updateMenu(TSysPmMenu menu) throws ServiceException;

    /**
     * 
     * @Title:        delMenus 
     * @Description:  删除菜单
     * @param:        @param menuIds
     * @param:        @return
     * @param:        @throws ServiceException    
     * @return:       boolean    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:41:59
     */
    boolean delMenus(List<Long> menuIds)  throws ServiceException;
    
    /**
     * 
     * @Title:        disableMenus 
     * @Description:  禁用、恢复菜单
     * @param:        @param menuIds
     * @param:        @param isDisable
     * @param:        @return
     * @param:        @throws ServiceException    
     * @return:       boolean    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:42:10
     */
    boolean disableMenus(List<Long> menuIds,boolean isDisable)  throws ServiceException;
    
    /**
     * 
     * @Title:        changeMenusSortby 
     * @Description:  上下移菜单
     * @param:        @param menuIds
     * @param:        @param isUp
     * @param:        @return    
     * @return:       boolean    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:42:20
     */
    boolean changeMenusSortby(List<Long> menuIds,boolean isUp);
    
    /**
     * 
     * @Title:        addRole 
     * @Description:  新增角色
     * @param:        @param role
     * @param:        @return
     * @param:        @throws Exception    
     * @return:       long    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:42:30
     */
    long addRole(TSysPmRole role) throws Exception;
    
    /**
     * 
     * @Title:        updateRole 
     * @Description:  更新角色
     * @param:        @param role
     * @param:        @return
     * @param:        @throws Exception    
     * @return:       boolean    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:42:40
     */
    boolean updateRole(TSysPmRole role) throws Exception;
    
    /**
     * 
     * @Title:        delRole 
     * @Description:  删除角色
     * @param:        @param roleId
     * @param:        @return
     * @param:        @throws Exception    
     * @return:       boolean    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:42:51
     */
    boolean delRole(long roleId) throws Exception;
    
    /**
     * 
     * @Title:        getRolesBySystemId 
     * @Description:  根据系统id获取角色列表
     * @param:        @param systemId
     * @param:        @param state
     * @param:        @return    
     * @return:       List<TSysPmRole>    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:43:01
     */
    List<TSysPmRole> getRolesBySystemId(long systemId, short state);
    
    /**
     * 
     * @Title:        queryRoleList 
     * @Description:  查询角色
     * @param:        @param paramBean
     * @param:        @return    
     * @return:       Pagination<TSysPmRole>    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:43:12
     */
    Pagination<TSysPmRole> queryRoleList(Pagination<TSysPmRole> paramBean);
    
    /**
     * 
     * @Title:        exportRoleList 
     * @Description:  导出角色
     * @param:        @param paramBean
     * @param:        @return    
     * @return:       Pagination<Map<String,Object>>    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:43:21
     */
    Pagination<Map<String, Object>> exportRoleList(Pagination<TSysPmRole> paramBean);
    
    /**
     * 
     * @Title:        getRoleById 
     * @Description:  根据角色ID获得角色
     * @param:        @param roleId
     * @param:        @return    
     * @return:       TSysPmRole    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:43:32
     */
    TSysPmRole getRoleById(long roleId);
    
    /**
     * 
     * @Title:        getRoleMenuIds 
     * @Description:  获取单个角色所拥有的权限菜单id
     * @param:        @param roleId
     * @param:        @param state
     * @param:        @return    
     * @return:       List<Long>    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:43:42
     */
    List<Long> getRoleMenuIds(long roleId,short state);
    
    /**
     * 
     * @Title:        setRoleMenus 
     * @Description:  设置单个角色的权限菜单
     * @param:        @param roleId
     * @param:        @param menuIds
     * @param:        @return    
     * @return:       boolean    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:43:52
     */
    boolean setRoleMenus(long roleId,List<Long> menuIds);
    
    /**
     * 
     * @Title:        queryUsers 
     * @Description:  查询用户
     * @param:        @param paramBean
     * @param:        @return    
     * @return:       Pagination<TSysPmUser>    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:44:03
     */
    Pagination<TSysPmUser> queryUsers(Pagination<TSysPmUser> paramBean);
    
    /**
	 * @Title:       exportUserList 
	 * @Description: 导出用户列表 
	 * @param:       @param paramBean
	 * @param:       @return    
	 * @return:      Pagination<Map<String,Object>>    
	 * @author       lijc
	 * @Date         2017年2月7日 下午2:02:04
	 */
	public Pagination<Map<String, Object>> exportUserList(Pagination<TSysPmUser> paramBean);
    
	/**
	 * 
	 * @Title:        getUserById 
	 * @Description:  获取PM用户信息
	 * @param:        @param userId
	 * @param:        @return    
	 * @return:       TSysPmUser    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午9:44:17
	 */
    TSysPmUser getUserById(long userId);

    /**
     * 
     * @Title:        delUsers 
     * @Description:  删除PM用户列表
     * @param:        @param userIds
     * @param:        @return    
     * @return:       boolean    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:44:28
     */
    boolean delUsers(List<Long> userIds);
    
    /**
     * 
     * @Title:        addUser 
     * @Description:  新增用户
     * @param:        @param user
     * @param:        @return
     * @param:        @throws ServiceException    
     * @return:       long    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:44:39
     */
    long addUser(TSysPmUser user) throws ServiceException;
    
    /**
     * 
     * @Title:        updateUser 
     * @Description:  修改用户
     * @param:        @param user
     * @param:        @return    
     * @return:       boolean    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:44:53
     */
    boolean updateUser(TSysPmUser user);
 
    /**
     * 
     * @Title:        setUserRoles 
     * @Description:  设置用户角色列表
     * @param:        @param userId
     * @param:        @param roleIds
     * @param:        @return    
     * @return:       boolean    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:45:02
     */
    boolean setUserRoles(long userId,List<Long> roleIds);

    /**
     * 
     * @Title:        getUserRoleIds 
     * @Description:  获取用户所拥有的角色id列表
     * @param:        @param userId
     * @param:        @param state
     * @param:        @return    
     * @return:       List<Long>    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:45:11
     */
    List<Long> getUserRoleIds(long userId,short state);
    
    /**
     * 
     * @Title:        loginUser 
     * @Description:  PM用户登陆
     * @param:        @param pmUser
     * @param:        @return
     * @param:        @throws ServiceException    
     * @return:       TSysPmUser    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:45:21
     */
    TSysPmUser loginUser(TSysPmUser pmUser) throws ServiceException;
    
    /**
     * 
     * @Title:        getUserMenusByUserId 
     * @Description:  获取根节点下面所有的用户菜单
     * @param:        @param systemId
     * @param:        @param userId
     * @param:        @param parentId
     * @param:        @return    
     * @return:       List<TSysPmMenu>    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:45:33
     */
    List<TSysPmMenu> getUserMenusByUserId(long systemId,long userId, long parentId);

    /**
     * 
     * @Title:        getFuncMenusByParentId 
     * @Description:  获取某个功能菜单的子菜单按钮列表
     * @param:        @param paramBean
     * @param:        @return    
     * @return:       Pagination<TSysPmMenu>    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:45:43
     */
    Pagination<TSysPmMenu> getFuncMenusByParentId(Pagination<TSysPmMenu> paramBean);
	
    /**
     * 
     * @Title:        isMenuCodeExist 
     * @Description:  menuCode是否已经存在
     * @param:        @param menuCode
     * @param:        @param exclusiveMenuId
     * @param:        @return    
     * @return:       boolean    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:45:54
     */
    boolean isMenuCodeExist(String menuCode,Long exclusiveMenuId);

    /**
     * 
     * @Title:        isLoginNameUnique 
     * @Description:  登录名是否重复
     * @param:        @param loginName
     * @param:        @return    
     * @return:       boolean    
     * @author        shenchu
     * @Date          2017年2月22日 下午9:46:08
     */
    boolean isLoginNameUnique(String loginName);

	/** 
	 * @Title:        getMenuById 
	 * @Description:  根据id获取菜单
	 * @param:        @param menuId
	 * @param:        @return    
	 * @return:       TSysPmMenu    
	 * @author        shenchu
	 * @Date          2017年1月15日 下午8:13:19 
	 */
	TSysPmMenu getMenuById(long menuId);
    
	
	/**
	 * 
	 * @Title:        getParamByName 
	 * @Description:  根据参数名称获得参数值
	 * @param:        @param name
	 * @param:        @return    
	 * @return:       TPlatformPmParam    
	 * @author        Alex
	 * @Date          2017年1月17日 上午11:11:30
	 */
	TSysParam getParamByName(String name);

	/** 
	 * @Title:        queryMenu 
	 * @Description:  TODO(这里用一句话描述这个方法的作用) 
	 * @param:        @param name
	 * @param:        @return    
	 * @return:       List<Map<String,Object>>    
	 * @author        shenchu
	 * @Date          2017年8月10日 上午10:14:03 
	 */
	List<Map<String, Object>> queryMenu(String name);
	
}

