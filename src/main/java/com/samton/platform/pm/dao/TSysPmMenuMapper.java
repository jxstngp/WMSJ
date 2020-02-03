package com.samton.platform.pm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.pm.bean.entity.TSysPmMenu;

/**
 * 
 * @Description:菜单Mapper
 * @author:     shenchu
 * @date:        2017年2月22日 下午8:14:33
 * Copyright (c) 2017, Samton. All rights reserved
 */
public interface TSysPmMenuMapper {
	/**
	 * 
	 * @Title:        deleteByPrimaryKey 
	 * @Description:  通过ID删除菜单
	 * @param:        @param menuId
	 * @param:        @return    
	 * @return:       int    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午8:14:46
	 */
    int deleteByPrimaryKey(Long menuId);

    /**
     * 
     * @Title:        insert 
     * @Description:  新增菜单
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:15:00
     */
    int insert(TSysPmMenu record);

    /**
     * 
     * @Title:        insertSelective 
     * @Description:  新增菜单(选择性)
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:15:12
     */
    int insertSelective(TSysPmMenu record);

    /**
     * 
     * @Title:        selectByPrimaryKey 
     * @Description:  通过ID查询菜单
     * @param:        @param menuId
     * @param:        @return    
     * @return:       TSysPmMenu    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:16:19
     */
    TSysPmMenu selectByPrimaryKey(Long menuId);

    /**
     * 
     * @Title:        updateByPrimaryKeySelective 
     * @Description:  修改菜单(选择性)
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:17:15
     */
    int updateByPrimaryKeySelective(TSysPmMenu record);

    /**
     * 
     * @Title:        updateByPrimaryKey 
     * @Description:  修改菜单
     * @param:        @param record
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:17:48
     */
    int updateByPrimaryKey(TSysPmMenu record);
    
    /**
     * 
     * @Title:        batchUpdateByPrimaryKey 
     * @Description:  批量修改菜单
     * @param:        @param list
     * @param:        @return    
     * @return:       int    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:18:04
     */
    int batchUpdateByPrimaryKey(List<TSysPmMenu> list);
    
    /**
     * 
     * @Title:        getMenusBySystemId 
     * @Description:  通过systemId获取菜单集合
     * @param:        @param systemId
     * @param:        @param state
     * @param:        @return    
     * @return:       List<TSysPmMenu>    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:24:06
     */
    List<TSysPmMenu> getMenusBySystemId(@Param("systemId") long systemId,@Param("state") short state);
    
    /**
     * 
     * @Title:        getMenusByMenuTypes 
     * @Description:  通过systemID以及菜单类型获取菜单集合
     * @param:        @param systemId
     * @param:        @param states
     * @param:        @param menuTypes
     * @param:        @return    
     * @return:       List<TSysPmMenu>    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:24:34
     */
    List<TSysPmMenu> getMenusByMenuTypes(@Param("systemId") long systemId,@Param("states") List<Short> states,@Param("menuTypes") List<Short> menuTypes);
    
    /**
     * 
     * @Title:        getChildrenExistByParentId 
     * @Description:  查询子菜单的ID，有且只有一个
     * @param:        @param parentId
     * @param:        @param state
     * @param:        @return    
     * @return:       Long    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:25:24
     */
    Long getChildrenExistByParentId(@Param("parentId") long parentId,@Param("state") short state);
    
    /**
     * 
     * @Title:        getUserMenusByParentId 
     * @Description:  根据userID、systemID、以及菜单父ID，获取该用户下的菜单。
     * @param:        @param systemId
     * @param:        @param parentId
     * @param:        @param userId
     * @param:        @return    
     * @return:       List<TSysPmMenu>    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:28:13
     */
    List<TSysPmMenu> getUserMenusByParentId(@Param("systemId") long systemId,@Param("parentId") long parentId,@Param("userId") long userId);

    /**
     * 
     * @Title:        getMenusByParentId 
     * @Description:  分页获取菜单数据
     * @param:        @param paramBean
     * @param:        @param rowBounds
     * @param:        @return    
     * @return:       List<TSysPmMenu>    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:34:27
     */
    List<TSysPmMenu> getMenusByParentId(Pagination<TSysPmMenu> paramBean,RowBounds rowBounds);
    
    /**
     * 
     * @Title:        getMaxSortByParentId 
     * @Description:  获取菜单排序中最大的
     * @param:        @param parentId
     * @param:        @return    
     * @return:       Integer    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:35:07
     */
    Integer getMaxSortByParentId(@Param("parentId") long parentId);
    
    /**
     * 
     * @Title:        existByMenuCode 
     * @Description:  判断menuCode是否存在
     * @param:        @param menuCode
     * @param:        @param menuId
     * @param:        @return    
     * @return:       Integer    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:36:02
     */
    Integer existByMenuCode(@Param("menuCode") String menuCode,@Param("menuId") Long menuId);
    
    /**
     * 
     * @Title:        getSortbyMenuByChange 
     * @Description:  获取排序后菜单
     * @param:        @param parentId
     * @param:        @param sortby
     * @param:        @param isUp
     * @param:        @return    
     * @return:       TSysPmMenu    
     * @author        shenchu
     * @Date          2017年2月22日 下午8:37:06
     */
    TSysPmMenu getSortbyMenuByChange(@Param("parentId") long parentId,@Param("sortby") int sortby,@Param("isUp") boolean isUp);

	/** 
	 * @Title:        queryMenu 
	 * @Description:  TODO(这里用一句话描述这个方法的作用) 
	 * @param:        @param name
	 * @param:        @return    
	 * @return:       List<Map<String,Object>>    
	 * @author        shenchu
	 * @Date          2017年8月10日 上午10:14:44 
	 */
	List<Map<String, Object>> queryMenu(String name);
}