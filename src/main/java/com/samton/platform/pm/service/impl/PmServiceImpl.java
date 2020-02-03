package com.samton.platform.pm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.samton.platform.common.util.ListUtil;
import com.samton.platform.common.util.OtherUtil;
import com.samton.platform.framework.exception.ServiceException;
import com.samton.platform.framework.mybatis.pagination.PageContext;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.framework.util.CurrentUtil;
import com.samton.platform.framework.util.MD5Util;
import com.samton.platform.pm.bean.entity.TSysLog;
import com.samton.platform.pm.bean.entity.TSysParam;
import com.samton.platform.pm.bean.entity.TSysPmMenu;
import com.samton.platform.pm.bean.entity.TSysPmRole;
import com.samton.platform.pm.bean.entity.TSysPmRoleMenu;
import com.samton.platform.pm.bean.entity.TSysPmRoleUser;
import com.samton.platform.pm.bean.entity.TSysPmUser;
import com.samton.platform.pm.constant.PmExpCodeConstant;
import com.samton.platform.pm.constant.PmStateConstant;
import com.samton.platform.pm.dao.TSysParamMapper;
import com.samton.platform.pm.dao.TSysPmMenuMapper;
import com.samton.platform.pm.dao.TSysPmRoleMapper;
import com.samton.platform.pm.dao.TSysPmRoleMenuMapper;
import com.samton.platform.pm.dao.TSysPmRoleUserMapper;
import com.samton.platform.pm.dao.TSysPmUserMapper;
import com.samton.platform.pm.service.ILogService;
import com.samton.platform.pm.service.IPmService;

/**
 * 
 * @Description:权限相关Service接口实现类
 * @author:     shenchu
 * @date:        2017年2月22日 下午9:46:32
 * Copyright (c) 2017, Samton. All rights reserved
 */
@Service("pmService")
public class PmServiceImpl implements IPmService{
	
	@Resource
	private TSysPmMenuMapper pmMenuMapper;
	@Resource
	private TSysPmRoleMapper pmRoleMapper;
	@Resource
	private TSysPmUserMapper pmUserMapper;
	@Resource
	private TSysPmRoleMenuMapper pmRoleMenuMapper;
	@Resource
	private TSysPmRoleUserMapper pmRoleUserMapper;
	@Resource
	private TSysParamMapper paramMapper;
	@Resource
	private ILogService logService;
	
	@Override
	public List<TSysPmMenu> getMenusBySystemId(long systemId, short state) {
		return pmMenuMapper.getMenusBySystemId(systemId, state);
	}
	
	@Override
	public List<TSysPmMenu> getMenusByMenuTypes(List<Short> menuTypes,List<Short> states){
		if(ListUtil.isEmpty(menuTypes)) return null;
		return pmMenuMapper.getMenusByMenuTypes(CurrentUtil.getCurrentUser().getSystemId(),states,menuTypes);
	}
	
	@Override
	public long addMenu(TSysPmMenu menu) throws ServiceException{
		if(isMenuCodeExist(menu.getMenuCode(),null)){
			throw new ServiceException(PmExpCodeConstant.PM_MENUCODE_REPEAT_ERROR);
		}
		menu.setState(PmStateConstant.ADD);
		menu.setIsDefault((short)0);
		Integer sortby=pmMenuMapper.getMaxSortByParentId(menu.getParentId());
		if(sortby==null){
			sortby=0;
		}
		menu.setSortby(sortby+1);
		CurrentUtil.setBaseBeanByInsert(menu);
		pmMenuMapper.insertSelective(menu);
		return menu.getMenuId();
	}
	
	@Override
	public boolean updateMenu(TSysPmMenu menu) throws ServiceException{
		if(isMenuCodeExist(menu.getMenuCode(),menu.getMenuId())){
			throw new ServiceException(PmExpCodeConstant.PM_MENUCODE_REPEAT_ERROR);
		}
		CurrentUtil.setBaseBeanByModify(menu);
		return pmMenuMapper.updateByPrimaryKeySelective(menu)>0;
	}
	
	@Override
	public boolean delMenus(List<Long> menuIds) throws ServiceException{
		if (ListUtil.isEmpty(menuIds)) {
			return false;
		}
		List<TSysPmMenu> pmMenus=new ArrayList<TSysPmMenu>();
		for (Long menuId : menuIds) {
			TSysPmMenu menu=pmMenuMapper.selectByPrimaryKey(menuId);
			if(!menu.getMenuType().equals(2)&&pmMenuMapper.getChildrenExistByParentId(menuId, PmStateConstant.ADD)!=null){			
				throw new ServiceException(PmExpCodeConstant.PM_MENU_HAS_CHILDREN_DEL_ERROR);
			}
			menu.setState(PmStateConstant.DEL);
			CurrentUtil.setBaseBeanByModify(menu);
			pmMenus.add(menu);
		}
		return pmMenuMapper.batchUpdateByPrimaryKey(pmMenus)>0;
	}
	
	@Override
	public boolean disableMenus(List<Long> menuIds,boolean isDisable) throws ServiceException {
		if(ListUtil.isEmpty(menuIds)) return false;
		List<TSysPmMenu> pmMenus=new ArrayList<TSysPmMenu>();
		for (Long menuId : menuIds) {
			TSysPmMenu menu=pmMenuMapper.selectByPrimaryKey(menuId);
			if(isDisable){				
				if(!menu.getMenuType().equals(2)&&pmMenuMapper.getChildrenExistByParentId(menuId, PmStateConstant.ADD)!=null){			
					throw new ServiceException(PmExpCodeConstant.PM_MENU_HAS_CHILDREN_DISABLE_ERROR);
				}
				menu.setState(PmStateConstant.DISABLE);
			}else {
				menu.setState(PmStateConstant.ADD);
			}
			CurrentUtil.setBaseBeanByModify(menu);
			pmMenus.add(menu);
		}
		pmMenuMapper.batchUpdateByPrimaryKey(pmMenus);
		return true;
	}
	
	@Override
	public long addRole(TSysPmRole role) throws Exception{
		
		if(role == null){
			throw new ServiceException(PmExpCodeConstant.PM_ROLE_NULL_ERROR);
		}
		
		if(!OtherUtil.isNotNull(role.getRoleName())){
			throw new ServiceException(PmExpCodeConstant.PM_ROLE_NAME_ERROR);
		}
		
		if(pmRoleMapper.checkRoleName(role.getRoleName(), null) >= 1){
			throw new ServiceException(PmExpCodeConstant.PM_ROLE_NAME_REPEAT_ERROR);
		}
		role.setState(PmStateConstant.ADD);
		CurrentUtil.setBaseBeanByInsert(role);
		pmRoleMapper.insertSelective(role);
		role.setSortby(role.getRoleId().intValue());
		pmRoleMapper.updateByPrimaryKeySelective(role);
		return role.getRoleId();
	}
	
	@Override
	public boolean updateRole(TSysPmRole role) throws Exception{
		
		if(role == null){
			throw new ServiceException(PmExpCodeConstant.PM_ROLE_NULL_ERROR);
		}
		
		if(!OtherUtil.isNotNull(role.getRoleName())){
			throw new ServiceException(PmExpCodeConstant.PM_ROLE_NAME_ERROR);
		}
		
		if(pmRoleMapper.checkRoleName(role.getRoleName(), role.getRoleId()) >= 1){
			throw new ServiceException(PmExpCodeConstant.PM_ROLE_NAME_REPEAT_ERROR);
		}
		
		CurrentUtil.setBaseBeanByModify(role);
		return pmRoleMapper.updateByPrimaryKeySelective(role)>0;
	}
	
	@Override
	public boolean delRole(long roleId) throws Exception{
		TSysPmRole role=pmRoleMapper.selectByPrimaryKey(roleId);
		
		if(role == null){
			throw new ServiceException(PmExpCodeConstant.PM_ROLE_DEL_NULL_ERROR);
		}
		
		role.setState(PmStateConstant.DEL);
		CurrentUtil.setBaseBeanByModify(role);

		return pmRoleMapper.updateByPrimaryKeySelective(role)>0;
	}
	
	@Override
	public List<TSysPmRole> getRolesBySystemId(long systemId, short state) {
		return pmRoleMapper.getRolesBySystemId(systemId, state);
	}

	@Override
	public Pagination<TSysPmRole> queryRoleList(Pagination<TSysPmRole> paramBean) {
		Pagination<TSysPmRole> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
		List<TSysPmRole> roles = pmRoleMapper.queryRoleList(paramBean, pagination.getRowBounds());
		pagination.setData(roles);
		return pagination;
	}

	@Override
	public Pagination<Map<String, Object>> exportRoleList(Pagination<TSysPmRole> paramBean) {
		Pagination<Map<String, Object>> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
		List<Map<String, Object>> roles = pmRoleMapper.exportRoleList(paramBean, pagination.getRowBounds());
		pagination.setData(roles);
		return pagination;
	}
	
	@Override
	public TSysPmRole getRoleById(long roleId) {
		return pmRoleMapper.getRoleById(roleId);
	}
	
	@Override
	public List<Long> getRoleMenuIds(long roleId,short state){
		return pmRoleMenuMapper.getRoleMenuIds(roleId, state);
	}

	@Override
	public boolean setRoleMenus(long roleId, List<Long> menuIds) {
		List<TSysPmRoleMenu> roleMenus=pmRoleMenuMapper.getRoleMenus(roleId);
		for (TSysPmRoleMenu roleMenu : roleMenus) {
			CurrentUtil.setBaseBeanByModify(roleMenu);
			if(menuIds.contains(roleMenu.getMenuId())){
				if(!roleMenu.getState().equals(PmStateConstant.ADD)){
					roleMenu.setState(PmStateConstant.ADD);
				}
				menuIds.remove(roleMenu.getMenuId());
			}else {
				roleMenu.setState(PmStateConstant.DEL);
			}
		}
		List<TSysPmRoleMenu> addRoleMenus=new ArrayList<TSysPmRoleMenu>();
		TSysPmRoleMenu pmRoleMenu=null;
		for (Long menuId : menuIds) {
			pmRoleMenu=new TSysPmRoleMenu();
			pmRoleMenu.setRoleId(roleId);
			pmRoleMenu.setMenuId(menuId);
			pmRoleMenu.setState(PmStateConstant.ADD);
			CurrentUtil.setBaseBeanByInsert(pmRoleMenu);
			addRoleMenus.add(pmRoleMenu);
		}
		if(roleMenus!=null&&roleMenus.size()>0){			
			pmRoleMenuMapper.batchUpdateByPrimaryKey(roleMenus);
		}
		if(addRoleMenus!=null&&addRoleMenus.size()>0){			
			pmRoleMenuMapper.batchInsert(addRoleMenus);
		}
		return true;
	}

	@Override
	public Pagination<TSysPmUser> queryUsers(Pagination<TSysPmUser> paramBean) {
		Pagination<TSysPmUser> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
		List<TSysPmUser> pmUsers = pmUserMapper.queryUsers(paramBean, pagination.getRowBounds());
		pagination.setData(pmUsers);
		return pagination;
	}
	
	/**
	 * @Title:       exportUserList 
	 * @Description: 导出用户列表 
	 * @param:       @param paramBean
	 * @param:       @return    
	 * @return:      Pagination<Map<String,Object>>    
	 * @author       lijc
	 * @Date         2017年2月7日 下午2:02:04
	 */
	@Override
	public Pagination<Map<String, Object>> exportUserList(Pagination<TSysPmUser> paramBean) {
		Pagination<Map<String, Object>> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
		List<Map<String, Object>> users = pmUserMapper.exportUserList(paramBean, pagination.getRowBounds());
		pagination.setData(users);
		return pagination;
	}
	
	@Override	
	public TSysPmUser getUserById(long userId) {
		return pmUserMapper.selectByPrimaryKey(userId);
	}
	
	@Override
	public boolean delUsers(List<Long> userIds){
		if(userIds==null||userIds.size()==0) return false;
		List<TSysPmUser> pmUsers=new ArrayList<TSysPmUser>();
		for (Long userId : userIds) {
			TSysPmUser pmUser=pmUserMapper.selectByPrimaryKey(userId);
			logService.addLog(new TSysLog("用户管理-删除", "删除用户【"+ pmUser.getUserName() +"】！", PmStateConstant.LOG_PLATFORM));
			pmUser.setState(PmStateConstant.DEL);
			CurrentUtil.setBaseBeanByModify(pmUser);
			pmUsers.add(pmUser);
		}
		return pmUserMapper.batchUpdateByPrimaryKey(pmUsers)>0;
	}
	
	@Override
	public long addUser(TSysPmUser user) throws ServiceException{
		/*short loginFlag=0;
		if(RegisterUtil.isEmail(user.getLoginName())){
			loginFlag=1;
		}else if (RegisterUtil.isMobileNo(user.getLoginName())) {
			loginFlag=2;
		}
		if(loginFlag==0){
			throw new ServiceException(PmExpCodeConstant.PM_LOGINNAME_FORMAT_ERROR);
		}
		user.setLoginFlag(loginFlag);*/
		TSysPmUser tempUser = pmUserMapper.getUserByLoginName(user);
		if (!org.springframework.util.StringUtils.isEmpty(tempUser)) {
			throw new ServiceException(PmExpCodeConstant.PM_LOGIN_NAME_REPEAT);
		}
		user.setState(PmStateConstant.ADD);
		user.setPwd(MD5Util.getMD5String(user.getPwd()));
		CurrentUtil.setBaseBeanByInsert(user);
		int flag = pmUserMapper.insertSelective(user);
		return flag;
	}
	
	@Override
	public boolean updateUser(TSysPmUser user){
		if(StringUtils.isNotEmpty(user.getPwd())){
			user.setPwd(MD5Util.getMD5String(user.getPwd()));
		}
		CurrentUtil.setBaseBeanByModify(user);
		return pmUserMapper.updateByPrimaryKeySelective(user)>0;
	}
	
	
	@Override
	public boolean setUserRoles(long userId, List<Long> roleIds) {
		List<TSysPmRoleUser> roleUsers=pmRoleUserMapper.getRoleUsers(userId);
		for (TSysPmRoleUser roleUser : roleUsers) {
			CurrentUtil.setBaseBeanByModify(roleUser);
			if(roleIds.contains(roleUser.getRoleId())){
				if(!roleUser.getState().equals(PmStateConstant.ADD)){
					roleUser.setState(PmStateConstant.ADD);
				}
				roleIds.remove(roleUser.getRoleId());
			}else {
				roleUser.setState(PmStateConstant.DEL);
			}
		}
		List<TSysPmRoleUser> addRoleUsers=new ArrayList<TSysPmRoleUser>();
		TSysPmRoleUser pmRoleUser=null;
		for (Long roleId : roleIds) {
			pmRoleUser=new TSysPmRoleUser();
			pmRoleUser.setRoleId(roleId);
			pmRoleUser.setUserId(userId);
			pmRoleUser.setState(PmStateConstant.ADD);
			CurrentUtil.setBaseBeanByInsert(pmRoleUser);
			addRoleUsers.add(pmRoleUser);
		}
		if(ListUtil.isNotEmpty(roleUsers)){			
			pmRoleUserMapper.batchUpdateByPrimaryKey(roleUsers);
		}
		if(ListUtil.isNotEmpty(addRoleUsers)){	
			pmRoleUserMapper.batchInsert(addRoleUsers);
		}
		return true;
	}
	
	@Override
	public List<Long> getUserRoleIds(long userId,short state){
		return pmRoleUserMapper.getUserRoleIds(userId, state);
	}
	
	@Override
	public TSysPmUser loginUser(TSysPmUser pmUser) throws ServiceException{
		pmUser=pmUserMapper.getUserByLoginNamePwd(pmUser);
		if(pmUser==null){
			throw new ServiceException(PmExpCodeConstant.PM_LOGINNAME_PWD_ERROR);
		}
		if(pmUser.getState() == 2){
			throw new ServiceException(PmExpCodeConstant.PM_LOGINNAME_PWD_STATE);
		}
		return pmUser;
	}
	
	@Override
	public List<TSysPmMenu> getUserMenusByUserId(long systemId,long userId, long parentId) {
		return pmMenuMapper.getUserMenusByParentId(systemId, parentId, userId);
	}
	
	@Override
	public Pagination<TSysPmMenu> getFuncMenusByParentId(Pagination<TSysPmMenu> paramBean) {
		TSysPmMenu pmMenu=(TSysPmMenu) paramBean.getSearch();
		pmMenu.setMenuType((short)2);
		Pagination<TSysPmMenu> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
		List<TSysPmMenu> pmMenus=pmMenuMapper.getMenusByParentId(paramBean,pagination.getRowBounds());
		pagination.setData(pmMenus);
		return pagination;
	}

	@Override
	public boolean isMenuCodeExist(String menuCode,Long exclusiveMenuId) {
		return pmMenuMapper.existByMenuCode(menuCode, exclusiveMenuId)!=null;
	}

	private boolean changeMenuSortby(TSysPmMenu menu,boolean isUp){
		if(menu==null) return false;
		if(menu.getSortby().equals(Integer.valueOf("1"))&&isUp){
			return true;
		}
		TSysPmMenu sortbyMenu=pmMenuMapper.getSortbyMenuByChange(menu.getParentId(), menu.getSortby(), isUp);
		if(sortbyMenu==null)  return false;
		int tmpSortby=menu.getSortby();
		menu.setSortby(sortbyMenu.getSortby());
		sortbyMenu.setSortby(tmpSortby);
		List<TSysPmMenu> pmMenus=new ArrayList<TSysPmMenu>();
		pmMenus.add(menu);
		pmMenus.add(sortbyMenu);
		pmMenuMapper.batchUpdateByPrimaryKey(pmMenus);
		return true;
	}
	
	@Override
	public boolean changeMenusSortby(List<Long> menuIds, boolean isUp) {
		if(ListUtil.isEmpty(menuIds)) return false;
		TSysPmMenu menu=null;
		if(isUp){			
			for (int i = 0,len=menuIds.size(); i < len; i++) {				
				menu=pmMenuMapper.selectByPrimaryKey(menuIds.get(i));
				if(!changeMenuSortby(menu,isUp)){
					if(i==0) return false;
					continue;
				}
			}
		}else {
			for (int i = menuIds.size(); i >0 ; i--) {
				menu=pmMenuMapper.selectByPrimaryKey(menuIds.get(i-1));
				if(!changeMenuSortby(menu,isUp)){
					if(i==0) return false;
					continue;
				}
			}			
		}
		return true;
	}

	@Override
	public boolean isLoginNameUnique(String loginName) {
		return pmUserMapper.getUserIdByLoginName(loginName)==null;
	}

	@Override
	public TSysPmMenu getMenuById(long menuId) {
		return pmMenuMapper.selectByPrimaryKey(menuId);
	}
	
	@Override
	public TSysParam getParamByName(String name) {
		return paramMapper.getParamByName(name);
	}

	@Override
	public List<Map<String, Object>> queryMenu(String name) {
		return pmMenuMapper.queryMenu(name);
	}
}
