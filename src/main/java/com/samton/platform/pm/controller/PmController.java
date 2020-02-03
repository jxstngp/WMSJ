package com.samton.platform.pm.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.samton.platform.common.util.BeanPropFilterUtil;
import com.samton.platform.common.util.ListUtil;
import com.samton.platform.common.util.OtherUtil;
import com.samton.platform.framework.base.SdkBaseController;
import com.samton.platform.framework.bean.UserCacheBean;
import com.samton.platform.framework.constant.WebConstant;
import com.samton.platform.framework.exception.BaseException;
import com.samton.platform.framework.exception.ControllerException;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.framework.util.CurrentUtil;
import com.samton.platform.framework.util.MD5Util;
import com.samton.platform.framework.util.NetworkUtil;
import com.samton.platform.pm.bean.entity.TSysLog;
import com.samton.platform.pm.bean.entity.TSysLogLogin;
import com.samton.platform.pm.bean.entity.TSysPmMenu;
import com.samton.platform.pm.bean.entity.TSysPmRole;
import com.samton.platform.pm.bean.entity.TSysPmUser;
import com.samton.platform.pm.constant.PmExpCodeConstant;
import com.samton.platform.pm.constant.PmStateConstant;
import com.samton.platform.pm.service.ILogService;
import com.samton.platform.pm.service.IPmService;
import com.samton.platform.pm.util.AuthImg;
import com.samton.platform.pm.util.RegisterUtil;

/**
 * 
 * @Description:权限管理、菜单管理、用户管理
 * @author:     shenchu
 * @date:        2017年2月22日 下午2:39:49
 * Copyright (c) 2017, Samton. All rights reserved
 */
@Controller
@RequestMapping("/platform/pm")
public class PmController extends SdkBaseController {

	@Resource
	private IPmService pmService;
	@Resource
	private ILogService logService;

	/**
	 * 
	 * @Title: queryMenuList
	 * @Description: 查询菜单页面
	 * @param: @return
	 * @param: @throws Exception
	 * @return: String
	 * @author shenchu
	 * @Date 2017年1月14日 下午10:22:11
	 */
	@RequestMapping("/queryMenuList"+WebConstant.PAGE_SUFFIX)
	public String queryMenuList() throws Exception {
		return "menu/menuManage";
	}

	/**
	 * 
	 * @Title:        getMenus 
	 * @Description:  获取所有正常状态按钮
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:40:40
	 */
	@RequestMapping("/getMenus"+WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> getMenus() throws Exception {
		List<TSysPmMenu> menus = pmService.getMenusBySystemId(CurrentUtil.getCurrentUser().getSystemId(), PmStateConstant.ADD);
		return this.getResultMap(menus);
	}
	
	
	/**
	 * 
	 * @Title:        queryMenu 
	 * @Description:  建议搜索菜单名称
	 * @param:        @param name
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       List<Map<String,Object>>    
	 * @author        shenchu
	 * @Date          2017年8月10日 上午10:09:58
	 */
	@RequestMapping("/queryMenu"+WebConstant.NO_AUTH_SUFFIX)
	@ResponseBody
	public List<Map<String,Object>>  queryMenu(String name)throws Exception{
		List<Map<String,Object>> menuList = pmService.queryMenu(name);
		return menuList;
	}

	/**
	 * 
	 * @Title:        getMenusByMenuTypes 
	 * @Description:  根据菜单类型获取菜单列表
	 * @param:        @param menuTypes
	 * @param:        @param states
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:41:02
	 */
	@RequestMapping("/getMenusByMenuTypes"+WebConstant.NO_AUTH_SUFFIX)
	@ResponseBody
	public Map<String, Object> getMenusByMenuTypes(Short[] menuTypes, Short[] states) throws Exception {
		List<TSysPmMenu> menus = pmService.getMenusByMenuTypes(Arrays.asList(menuTypes), Arrays.asList(states));
		return this.getResultMap(menus);
	}

	/**
	 * 
	 * @Title:        getFuncMenusByParentId 
	 * @Description:  根据父节点id获取菜单按钮
	 * @param:        @param paramBean
	 * @param:        @param menu
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:41:21
	 */
	@RequestMapping("/getFuncMenusByParentId"+WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> getFuncMenusByParentId(Pagination<TSysPmMenu> paramBean, TSysPmMenu menu) throws Exception {
		paramBean.setSearch(menu);
		Pagination<TSysPmMenu> menus = pmService.getFuncMenusByParentId(paramBean);
		return this.getResultMap(menus);
	}

	/**
	 * 
	 * @Title:        getMenuById 
	 * @Description:  通过菜单ID获取菜单信息
	 * @param:        @param menuId
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:41:55
	 */
	@RequestMapping("/getMenuById"+WebConstant.NO_AUTH_SUFFIX)
	@ResponseBody
	public Map<String, Object> getMenuById(long menuId) throws Exception {
		TSysPmMenu menu = pmService.getMenuById(menuId);
		return this.getResultMap(menu);
	}

	/**
	 * 
	 * @Title: querFuncMenuList
	 * @Description: 查询功能菜单
	 * @param: @param paramBean
	 * @param: @param menu
	 * @param: @return
	 * @param: @throws Exception
	 * @return: String
	 * @author shenchu
	 * @Date 2017年1月15日 下午2:39:46
	 */
	@RequestMapping("/querFuncMenuList"+WebConstant.NO_AUTH_SUFFIX)
	public String querFuncMenuList(Pagination<TSysPmMenu> paramBean, TSysPmMenu menu) throws Exception {
		if (menu != null && menu.getMenuName() != null) {
			menu.setMenuName(menu.getMenuName().trim());
		}
		paramBean.setSearch(menu);
		Pagination<TSysPmMenu> pageData = pmService.getFuncMenusByParentId(paramBean);
		this.request.setAttribute("pageData", pageData);
		this.request.setAttribute("menu", menu);
		return "menu/menuList";
	}

	/**
	 * 
	 * @Title:        addMenu 
	 * @Description:  新增菜单
	 * @param:        @param menu
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:42:37
	 */
	@RequestMapping("/addMenu"+WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> addMenu(TSysPmMenu menu) throws Exception {
		return this.getResultMap(true, pmService.addMenu(menu));
	}

	/**
	 * 
	 * @Title:        updateMenu 
	 * @Description:  更新菜单
	 * @param:        @param menu
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:43:08
	 */
	@RequestMapping("/updateMenu"+WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> updateMenu(TSysPmMenu menu) throws Exception {
		return this.getResultMap(pmService.updateMenu(menu));
	}

	/**
	 * 
	 * @Title:        changeMenusSortby 
	 * @Description:  上移、下移菜单顺序
	 * @param:        @param menuIds
	 * @param:        @param isUp
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:43:58
	 */
	@RequestMapping("/changeMenusSortby"+WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> changeMenusSortby(Long[] menuIds, int isUp)throws Exception {
		return this.getResultMap(pmService.changeMenusSortby(Arrays.asList(menuIds), isUp > 0));
	}

	/**
	 * 
	 * @Title:        delMenus 
	 * @Description:  删除菜单
	 * @param:        @param menuIds
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:44:28
	 */
	@RequestMapping("/delMenus"+WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> delMenus(Long[] menuIds) throws Exception {
		return this.getResultMap(pmService.delMenus(Arrays.asList(menuIds)));
	}

	/**
	 * 
	 * @Title:        disableMenus 
	 * @Description:  禁用、恢复菜单
	 * @param:        @param menuIds
	 * @param:        @param isDisable(1：禁用；0：恢复)
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:45:05
	 */
	@RequestMapping("/disableMenus"+WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> disableMenus(Long[] menuIds, int isDisable)throws Exception {
		return this.getResultMap(pmService.disableMenus(Arrays.asList(menuIds), isDisable == 1 ? true : false));
	}

	/**
	 * 
	 * @Title: queryRoleList
	 * @Description: 角色列表分页
	 * @param: @param paramBean
	 * @param: @param role
	 * @param: @return
	 * @param: @throws Exception
	 * @return: String
	 * @author Alex
	 * @Date 2017年1月12日 下午3:32:13
	 */
	@RequestMapping("/queryRoleList"+WebConstant.PAGE_SUFFIX)
	public String queryRoleList(Pagination<TSysPmRole> paramBean, TSysPmRole role) throws Exception {
		if (role != null) {
			// 设置系统ID和状态
			role.setState(PmStateConstant.ADD);
			if (role.getRoleName() != null) {
				role.setRoleName(role.getRoleName().trim());
			}
		}
		// 设置查询条件
		paramBean.setSearch(role);
		Pagination<TSysPmRole> pageData = pmService.queryRoleList(paramBean);
		this.request.setAttribute("pageData", pageData);
		this.request.setAttribute("role", role);
		return "role/roleList";
	}

	/**
	 * 
	 * @Title:        exportRoleList 
	 * @Description:  导出excel
	 * @param:        @param paramBean
	 * @param:        @param role
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       String    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:46:15
	 */
	@RequestMapping("/exportRoleList"+WebConstant.BUSINESS_SUFFIX)
	public String exportRoleList(Pagination<TSysPmRole> paramBean, TSysPmRole role) throws Exception {
		// 设置系统ID和状态
		role.setState(PmStateConstant.ADD);
		// 设置查询条件
		paramBean.setSearch(role);
		Pagination<Map<String, Object>> pageData = pmService.exportRoleList(paramBean);
		String title = "角色名称,角色描述";
		List<String> colNames = new ArrayList<String>();
		colNames.add("role_name");
		colNames.add("descriptions");
		// 数字转换成文字
		// colNames.add("state");
		// pageData.putExprotKV("state", "0", "删除");
		// pageData.putExprotKV("state", "1", "新增");
		logService.addLog(new TSysLog("角色管理-导出", "导出角色信息！",PmStateConstant.LOG_PLATFORM));
		this.export(response,"角色管理" + String.format("%1$tY%1$tm%1$td", new Date()), title,colNames, pageData);
		return null;
	}

	/**
	 * 
	 * @Title: getSubMenus
	 * @Description: 递归菜单
	 * @param: @param parentId
	 * @param: @param menus
	 * @param: @return
	 * @return: List<TSysPmMenu>
	 * @author Alex
	 * @Date 2017年1月18日 下午4:10:36
	 */
	private List<TSysPmMenu> getSubMenus(Long parentId, List<TSysPmMenu> menus) {
		List<TSysPmMenu> menuList = new ArrayList<TSysPmMenu>(0);
		for (int i = 0; i < menus.size(); i++) {
			if (parentId.equals(menus.get(i).getParentId())) {
				TSysPmMenu menu = menus.get(i);
				menu.setSubMenus(this.getSubMenus(menus.get(i).getMenuId(),menus));
				menuList.add(menu);
			}
		}
		return menuList;
	}

	/**
	 * 
	 * @Title: getRoleById
	 * @Description: 根据角色ID获得角色信息
	 * @param: @param roleId
	 * @param: @return
	 * @param: @throws Exception
	 * @return: Map<String,Object>
	 * @author Alex
	 * @Date 2017年1月18日 下午4:10:51
	 */
	@RequestMapping("/getRoleById"+WebConstant.NO_AUTH_SUFFIX)
	@ResponseBody
	public Map<String, Object> getRoleById(long roleId) throws Exception {
		TSysPmRole role = pmService.getRoleById(roleId);
		return this.getResultMap(role);
	}

	/**
	 * 
	 * @Title:        getMenusByRoleId 
	 * @Description:  根据角色获取菜单
	 * @param:        @param roleId
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       String    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:48:28
	 */
	@RequestMapping("/getMenusByRoleId"+WebConstant.NO_AUTH_SUFFIX)
	public String getMenusByRoleId(long roleId) throws Exception {
		// 当前角色拥有的菜单
		List<Long> menuIds = pmService.getRoleMenuIds(roleId, PmStateConstant.ADD);
		// 所有菜单
		List<TSysPmMenu> menus = pmService.getMenusBySystemId(CurrentUtil.getCurrentUser().getSystemId(), PmStateConstant.ADD);
		// 选择设置所有拥有的菜单选中
		for (Long menuId : menuIds) {
			for (int i = 0; i < menus.size(); i++) {
				if (menuId.equals(menus.get(i).getMenuId())) {
					menus.get(i).setIsCheck(true);
					break;
				}
			}
		}
		// 创建菜单根目录，使用递归将菜单组装
		TSysPmMenu menu = new TSysPmMenu();
		menu.setSubMenus(this.getSubMenus(0L, menus));
		this.request.setAttribute("menu", menu);
		return "role/roleMenu";
	}

	/**
	 * 
	 * @Title:        addRole 
	 * @Description:  新增角色
	 * @param:        @param role
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:50:44
	 */
	@RequestMapping("/addRole"+WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> addRole(TSysPmRole role) throws Exception {
		long num = pmService.addRole(role);
		logService.addLog(new TSysLog("角色管理-新增", "新增角色【" + role.getRoleName() + "】！", PmStateConstant.LOG_PLATFORM));
		return this.getResultMap(true, num);
	}

	/**
	 * 
	 * @Title:        updateRole 
	 * @Description:  更新角色
	 * @param:        @param role
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:52:36
	 */
	@RequestMapping("/updateRole"+WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> updateRole(TSysPmRole role) throws Exception {
		boolean isRight = pmService.updateRole(role);
		logService.addLog(new TSysLog("角色管理-编辑", "编辑角色【" + role.getRoleName() + "】！", PmStateConstant.LOG_PLATFORM));
		return this.getResultMap(isRight);
	}

	/**
	 * 
	 * @Title:        delRole 
	 * @Description:  删除角色
	 * @param:        @param role
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:53:08
	 */
	@RequestMapping("/delRole"+WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> delRole(TSysPmRole role) throws Exception {
		boolean isRight = pmService.delRole(role.getRoleId());
		logService.addLog(new TSysLog("角色管理-删除", "删除角色【" + role.getRoleName() + "】！", PmStateConstant.LOG_PLATFORM));
		return this.getResultMap(isRight);
	}

	/**
	 * 
	 * @Title:        setRoleMenus 
	 * @Description:  设置角色所拥有菜单权限 
	 * @param:        @param role
	 * @param:        @param menuIds
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:53:35
	 */
	@RequestMapping("/setRoleMenus"+WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> setRoleMenus(TSysPmRole role, String menuIds) throws Exception {
		List<Long> menuIdList = new ArrayList<Long>(0);
		for (String menuId : menuIds.split(",")) {
			if (OtherUtil.isNotNull(menuId)) {
				menuIdList.add(Long.parseLong(menuId));
			}
		}
		boolean isRight = pmService.setRoleMenus(role.getRoleId(), menuIdList);
		logService.addLog(new TSysLog("角色管理-权限设置", "设置角色【" + role.getRoleName() + "】权限！", PmStateConstant.LOG_PLATFORM));
		return this.getResultMap(isRight);
	}

	/**
	 * 
	 * @Title:        addUser 
	 * @Description:  新增用户
	 * @param:        @param user
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:54:33
	 */
	@RequestMapping("/addUser"+WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> addUser(TSysPmUser user) throws Exception {
		long flag = pmService.addUser(user);
		if (flag > 0) {
			logService.addLog(new TSysLog("用户管理-新增", "新增用户【" + user.getUserName() + "】成功！", PmStateConstant.LOG_PLATFORM));
		} else {
			logService.addLog(new TSysLog("用户管理-新增", "新增用户【" + user.getUserName() + "】失败！", PmStateConstant.LOG_PLATFORM));
		}
		return this.getResultMap(true, flag);
	}

	/**
	 * 
	 * @Title:        updateUser 
	 * @Description:  更新用户
	 * @param:        @param user
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:55:09
	 */
	@RequestMapping("/updateUser"+WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> updateUser(TSysPmUser user) throws Exception {
		boolean flag = pmService.updateUser(user);
		if (flag) {
			logService.addLog(new TSysLog("用户管理-编辑", "编辑用户【" + user.getUserName() + "】成功！", PmStateConstant.LOG_PLATFORM));
		} else {
			logService.addLog(new TSysLog("用户管理-编辑", "编辑用户【" + user.getUserName() + "】失败！", PmStateConstant.LOG_PLATFORM));
		}
		return this.getResultMap(flag);
	}

	/**
	 * 
	 * @Title:        queryUsers 
	 * @Description:  分页查询用户
	 * @param:        @param paramBean
	 * @param:        @param pmUser
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:56:17
	 */
	@RequestMapping("/queryUsers"+WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> queryUsers(Pagination<TSysPmUser> paramBean, TSysPmUser pmUser) throws Exception {
		// pmUser.setState(PmStateConstant.ADD);
		paramBean.setSearch(pmUser);
		Pagination<TSysPmUser> users = pmService.queryUsers(paramBean);
		return this.getResultMap(true, users);
	}

	/**
	 * 
	 * @Title:        queryUserList 
	 * @Description:  分页查询用户
	 * @param:        @param paramBean
	 * @param:        @param pmUser
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       String    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:57:37
	 */
	@RequestMapping("/queryUserList"+WebConstant.PAGE_SUFFIX)
	public String queryUserList(Pagination<TSysPmUser> paramBean, TSysPmUser pmUser) throws Exception {
		// pmUser.setState(PmStateConstant.ADD);
		if (pmUser != null) {
			if (pmUser.getUserName() != null) {
				pmUser.setUserName(pmUser.getUserName().trim());
			}
			if (pmUser.getLoginName() != null) {
				pmUser.setLoginName(pmUser.getLoginName().trim());
			}
		}
		paramBean.setSearch(pmUser);
		Pagination<TSysPmUser> pageData = pmService.queryUsers(paramBean);
		this.request.setAttribute("pageData", pageData);
		this.request.setAttribute("pmUser", pmUser);
		return "user/userManage";
	}

	/**
	 * 
	 * @Title:        exportUserList 
	 * @Description:  导出部门
	 * @param:        @param paramBean
	 * @param:        @param pmUser
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       String    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午2:58:23
	 */
	@RequestMapping("/exportUserList"+WebConstant.BUSINESS_SUFFIX)
	public String exportUserList(Pagination<TSysPmUser> paramBean, TSysPmUser pmUser) throws Exception {
		paramBean.setSearch(pmUser);
		pmUser.setState(PmStateConstant.ADD);
		Pagination<Map<String, Object>> pageData = pmService.exportUserList(paramBean);
		logService.addLog(new TSysLog("用户管理-导出", "导出用户列表信息！",PmStateConstant.LOG_PLATFORM));
		String title = "登录名,用户名,角色名,所属部门,手机号码,最后登录时间";
		List<String> colNames = new ArrayList<String>();
		colNames.add("login_name");
		colNames.add("user_name");
		colNames.add("role_names");
		colNames.add("depart_name");
		colNames.add("mobile_phone");
		colNames.add("last_login_time");
		// 数字转换成文字
		// colNames.add("state");
		// pageData.putExprotKV("state", "0", "删除");
		// pageData.putExprotKV("state", "1", "新增");
		this.export(response,"用户信息列表" + String.format("%1$tY%1$tm%1$td", new Date()), title,colNames, pageData);
		return null;
	}

	/**
	 * 
	 * @Title:        getUser 
	 * @Description:  根据用户id获取用户信息
	 * @param:        @param userId
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午3:00:56
	 */
	@RequestMapping("/getUser"+WebConstant.NO_AUTH_SUFFIX)
	@ResponseBody
	public Map<String, Object> getUser(long userId) throws Exception {
		TSysPmUser user = pmService.getUserById(userId);
		BeanPropFilterUtil.convertBeanByPersistProps(user, Arrays.asList("loginName", "userName", "departId", "departName","mobilePhone", "email"));
		return this.getResultMap(user);
	}

	/**
	 * 
	 * @Title:        delUsers 
	 * @Description:  删除用户列表
	 * @param:        @param userIds
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午3:01:56
	 */
	@RequestMapping("/delUsers"+WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> delUsers(Long[] userIds) throws Exception {
		boolean flag = pmService.delUsers(Arrays.asList(userIds));
		return this.getResultMap(flag);
	}

	/**
	 * @Title: resetUserPwd
	 * @Description: 重置用户密码
	 * @param: @param userId
	 * @param: @return
	 * @param: @throws Exception
	 * @return: Map<String,Object>
	 * @author lijc
	 * @Date 2017年1月13日 下午4:18:18
	 */
	@RequestMapping("/resetUserPwd"+WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> resetUserPwd(long userId) throws Exception {
		String resetPwd = RegisterUtil.randomPassword(PmStateConstant.RESET_PWD_LEN);
		TSysPmUser user = new TSysPmUser();
		user.setUserId(userId);
		user.setPwd(resetPwd);
		TSysPmUser tempUser = pmService.getUserById(userId);
		boolean flag = pmService.updateUser(user);
		if (flag) {
			logService.addLog(new TSysLog("用户管理-重置密码", "重置用户【" + tempUser.getUserName() + "】密码成功！", PmStateConstant.LOG_PLATFORM));
		} else {
			logService.addLog(new TSysLog("用户管理-重置密码", "重置用户【" + tempUser.getUserName() + "】密码失败！", PmStateConstant.LOG_PLATFORM));
		}
		return this.getResultMap(flag, resetPwd);
	}

	/**
	 * @Title: disAndEnableAccount
	 * @Description: 禁用/启用用户账号
	 * @param: @param userId 用户ID
	 * @param: @param status 禁用/启用
	 * @param: @return
	 * @param: @throws Exception
	 * @return: Map<String,Object>
	 * @author lijc
	 * @Date 2017年1月16日 上午9:40:23
	 */
	@RequestMapping("/disAndEnableAccount"+WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> disAndEnableAccount(long userId, short status) throws Exception {
		TSysPmUser user = new TSysPmUser();
		user.setUserId(userId);
		user.setState(status);
		TSysPmUser tempUser = pmService.getUserById(userId);
		if (status == 1) {
			logService.addLog(new TSysLog("用户管理-启用", "启用用户【" + tempUser.getUserName() + "】成功！", PmStateConstant.LOG_PLATFORM));
		} else {
			logService.addLog(new TSysLog("用户管理-禁用", "禁用用户【" + tempUser.getUserName() + "】成功！", PmStateConstant.LOG_PLATFORM));
		}
		return this.getResultMap(pmService.updateUser(user));
	}

	/**
	 * 获取用户角色信息
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getUserRoles"+WebConstant.NO_AUTH_SUFFIX)
	@ResponseBody
	public Map<String, Object> getUserRoles(long userId) throws Exception {
		List<TSysPmRole> pmRoles = pmService.getRolesBySystemId(CurrentUtil.getCurrentUser().getSystemId(), PmStateConstant.ADD);
		boolean flag = ListUtil.isNotEmpty(pmRoles);
		Map<String, Object> rsMap = this.getResultMap(flag);
		if (flag) {
			rsMap.put("roles", pmRoles);
			rsMap.put("userRoles", pmService.getUserRoleIds(userId, PmStateConstant.ADD));
		}
		return rsMap;
	}

	/**
	 * 
	 * @Title:        setUserRoles 
	 * @Description:  设置用户角色列表
	 * @param:        @param userId
	 * @param:        @param roleIds
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午3:06:24
	 */
	@RequestMapping("/setUserRoles"+WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> setUserRoles(long userId, Long[] roleIds) throws Exception {
		TSysPmUser tempUser = pmService.getUserById(userId);
		List<Long> roleIdList = new ArrayList<Long>();
		if (roleIds != null && roleIds.length != 0) {
			for (Long roleId : roleIds) {
				roleIdList.add(roleId);
			}
		}
		logService.addLog(new TSysLog("用户管理-角色设置", "设置用户【" + tempUser.getUserName() + "】角色！", PmStateConstant.LOG_PLATFORM));
		return this.getResultMap(pmService.setUserRoles(userId, roleIdList));
	}

	/**
	 * 
	 * @Title:        login 
	 * @Description:  pm系统登录
	 * @param:        @param pmUser
	 * @param:        @param rand
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午3:07:45
	 */
	@RequestMapping(value = "/login"+WebConstant.PAGE_SUFFIX)
	@ResponseBody
	public Map<String, Object> login(TSysPmUser pmUser, String rand) throws Exception {
		Map<String, Object> objMap = new HashMap<String, Object>(0);
		boolean isRight = false;
//		if (!OtherUtil.isNotNull(rand)) {
//			objMap.put("errorMsg", "请输入验证码！");
//			objMap.put("rs", isRight);
//			return this.getResultMap(objMap);
//		} else if (!rand.toLowerCase().equals(
//				((String) this.getSessionAttribute("rand")).toLowerCase())) {
//			objMap.put("errorMsg", "验证码不正确，请重新输入！");
//			objMap.put("rs", isRight);
//			return this.getResultMap(objMap);
//		}
		try {
			pmUser.setPwd(MD5Util.getMD5String(pmUser.getPwd()));
			pmUser = pmService.loginUser(pmUser);
			isRight = true;
			UserCacheBean userCacheBean = new UserCacheBean();
			userCacheBean.setLoginName(pmUser.getLoginName());
			userCacheBean.setUserId(pmUser.getUserId());
			userCacheBean.setUserName(pmUser.getUserName());
			this.setSessionAttribute(WebConstant.USER_SESSION, userCacheBean);
			CurrentUtil.setCurrentUser(userCacheBean);
			Map<String, String> m = new HashMap<String, String>(0);
			m.put("userName", userCacheBean.getUserName());
			m.put("userId", String.valueOf(userCacheBean.getUserId()));
			// 极光推送
//			Jpush.SendPush("您已经登录！", m, PlatformType.IOS, AudienceType.ALIAS,"0221");
			// 添加登陆日志
			logService.addLogLogin(new TSysLogLogin(PmStateConstant.LOG_PLATFORM, "用户登录：【登录名称:"
							+ pmUser.getLoginName() + ",用户名称:"
							+ pmUser.getUserName() + "】",
					OtherUtil.getDefaultValue(NetworkUtil.getIpAddress(request))));
		} catch (BaseException e) {
			objMap.put("errorMsg", e.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
			objMap.put("errorMsg", "未知错误");
		} finally {
		}
		objMap.put("rs", isRight);
		return this.getResultMap(objMap);
	}

	/**
	 * 
	 * @Title:        loginBySystemId 
	 * @Description:  跳转首页
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,Object>    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午3:08:46
	 */
	@RequestMapping(value = "/index"+WebConstant.PAGE_SUFFIX)
	@ResponseBody
	public Map<String, Object> loginBySystemId() throws Exception {
		//通过用户id和systemid 获取所有菜单信息
		List<TSysPmMenu> foreMenus = pmService.getUserMenusByUserId(CurrentUtil.getCurrentUser().getSystemId(), CurrentUtil.getCurrentUser().getUserId(), 0L);
		Map<String, List<TSysPmMenu>> menuMap = new LinkedHashMap<String, List<TSysPmMenu>>();
		Map<String, List<TSysPmMenu>> buttonMap = new LinkedHashMap<String, List<TSysPmMenu>>();
		//功能菜单
		List<TSysPmMenu> pmMenu = new ArrayList<TSysPmMenu>();
		Set<String> authCodeSet = new HashSet<String>();
		for (TSysPmMenu foreMenu : foreMenus) {
			// 添加菜单编码
			authCodeSet.add(foreMenu.getMenuCode());
			// 父级菜单id
			String key = foreMenu.getParentId().toString();
			//MENU
			if(!foreMenu.getMenuType().equals(Short.valueOf("0"))){
				
				 if(foreMenu.getMenuType().equals(Short.valueOf("2"))){
					foreMenu.setMenuUrl(foreMenu.getMenuUrl() + WebConstant.BUSINESS_SUFFIX);
				}else{
					foreMenu.setMenuUrl(foreMenu.getMenuUrl() + WebConstant.PAGE_SUFFIX);
				}
				pmMenu.add(foreMenu);
			}
			
			// MENU ACTION
			if (foreMenu.getMenuType().equals(Short.valueOf("2")) || foreMenu.getMenuType().equals(Short.valueOf("3"))) {
				if (buttonMap.containsKey(key)) {
					buttonMap.get(key).add(foreMenu);
				} else {
					List<TSysPmMenu> childrenMenus = new ArrayList<TSysPmMenu>(0);
					childrenMenus.add(foreMenu);
					buttonMap.put(key, childrenMenus);
				}
			} else {
				if (menuMap.containsKey(key)) {
					menuMap.get(key).add(foreMenu);
				} else {
					List<TSysPmMenu> childrenMenus = new ArrayList<TSysPmMenu>(0);
					childrenMenus.add(foreMenu);
					menuMap.put(key, childrenMenus);
				}
			}
		}

		this.setAttribute("menus", JSONObject.fromObject(menuMap));
		this.setAttribute("buttons", JSONObject.fromObject(buttonMap));
		this.setSessionAttribute(WebConstant.AUTH_CODE_SET, authCodeSet);
		CurrentUtil.getCurrentUser().setMenus(pmMenu);
		RequestDispatcher rd = request.getRequestDispatcher("/resources/platform/views/main.jsp");
		rd.forward(request, response);
		return null;
	}

	/**
	 * @Title: changePwd
	 * @Description: 修改密码
	 * @param: @return
	 * @param: @throws Exception
	 * @return: Map<String,Object>
	 * @author lijc
	 * @Date 2017年1月18日 上午10:30:43
	 */
	@RequestMapping("/changePwd"+WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> changePwd(TSysPmUser pmUser) throws Exception {
		String oldPwd = this.request.getParameter("oldPwd");
		String newPwd = this.request.getParameter("newPwd");
		String newPwdAgain = this.request.getParameter("newPwdAgain");
		if (StringUtils.isEmpty(oldPwd)) {
			logService.addLog(new TSysLog("用户管理-修改密码", "用户修改密码失败，错误提示信息【" + PmExpCodeConstant.PM_CHANGE_OLD_PWD_ERROR + "】！", PmStateConstant.LOG_PLATFORM));
			throw new ControllerException(PmExpCodeConstant.PM_CHANGE_OLD_PWD_ERROR);
		}
		if (StringUtils.isEmpty(newPwd)) {
			logService.addLog(new TSysLog("用户管理-修改密码", "用户修改密码失败，错误提示信息【" + PmExpCodeConstant.PM_CHANGE_NEW_PWD_ERROR + "】！", PmStateConstant.LOG_PLATFORM));
			throw new ControllerException(PmExpCodeConstant.PM_CHANGE_NEW_PWD_ERROR);
		}
		if (StringUtils.isEmpty(newPwdAgain)) {
			logService.addLog(new TSysLog("用户管理-修改密码", "用户修改密码失败，错误提示信息【"+ PmExpCodeConstant.PM_CHANGE_NEW_PWD_AGAIN_ERROR + "】！", PmStateConstant.LOG_PLATFORM));
			throw new ControllerException(PmExpCodeConstant.PM_CHANGE_NEW_PWD_AGAIN_ERROR);
		}
		if (oldPwd.equals(newPwd)) {
			logService.addLog(new TSysLog("用户管理-修改密码", "用户修改密码失败，错误提示信息【" + PmExpCodeConstant.PM_CHANGE_OLD_NEW_PWD_ERROR + "】！", PmStateConstant.LOG_PLATFORM));
			throw new ControllerException(PmExpCodeConstant.PM_CHANGE_OLD_NEW_PWD_ERROR);
		}
		if (!newPwd.equals(newPwdAgain)) {
			logService.addLog(new TSysLog("用户管理-修改密码", "用户修改密码失败，错误提示信息【" + PmExpCodeConstant.PM_CHANGE_NEW_ANAIN_PWD_ERROR + "】！", PmStateConstant.LOG_PLATFORM));
			throw new ControllerException(PmExpCodeConstant.PM_CHANGE_NEW_ANAIN_PWD_ERROR);
		}
		TSysPmUser user = pmService.getUserById(pmUser.getUserId());
		if (!MD5Util.getMD5String(oldPwd.trim()).equals(user.getPwd())) {
			logService.addLog(new TSysLog("用户管理-修改密码", "用户修改密码失败，错误提示信息【" + PmExpCodeConstant.PM_OLD_PWD_ERROR + "】！", PmStateConstant.LOG_PLATFORM));
			throw new ControllerException(PmExpCodeConstant.PM_OLD_PWD_ERROR);
		}
		user.setPwd(newPwd.trim());
		logService.addLog(new TSysLog("用户管理-修改密码", "用户【" + user.getUserName() + "】修改密码成功！", PmStateConstant.LOG_PLATFORM));
		return this.getResultMap(pmService.updateUser(user));
	}

	/**
	 * 
	 * @Title:        logout 
	 * @Description:  用户登出
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       ModelAndView    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午3:12:05
	 */
	@RequestMapping("/logout"+WebConstant.NO_AUTH_SUFFIX)
	public String logout() throws Exception {
		this.removeSessionAttribute(WebConstant.USER_SESSION);
		return "redirect:/";
	}

	/**
	 * 
	 * @Title:        checkMenuForm 
	 * @Description:  菜单表单校验(menu_code是否唯一)
	 * @param:        @param menu
	 * @param:        @return    
	 * @return:       boolean    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午3:12:33
	 */
	@RequestMapping("checkMenuForm"+WebConstant.NO_AUTH_SUFFIX)
	@ResponseBody
	public boolean checkMenuForm(TSysPmMenu menu) {
		if (pmService.isMenuCodeExist(menu.getMenuCode(), menu.getMenuId())) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @Title:        getVaildImg 
	 * @Description:  获取验证码
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       String    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午3:12:48
	 */
	@RequestMapping("/getVaildImg"+WebConstant.PAGE_SUFFIX)
	public String getVaildImg() throws Exception {
		AuthImg ai = new AuthImg();
		ai.service(request, response);
		return null;
	}



}
