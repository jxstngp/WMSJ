/**
 * 
 */
package com.samton.platform.pm.service;

import java.util.List;

import com.samton.platform.framework.exception.ServiceException;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.pm.bean.entity.TSysDictInfo;
import com.samton.platform.pm.bean.entity.TSysDictType;

/**
 * @Description: 字典管理和字典类型管理Service
 * @author:      lijc
 * @date:        2017年2月7日 下午3:49:57
 * Copyright (c) 2017, Samton. All rights reserved
 */
public interface IDictService {
	
	/**
	 * @Title:       queryDictTypeList 
	 * @Description: 查询字典类型列表方法
	 * @param:       @param paramBean
	 * @param:       @return    
	 * @return:      Pagination<TSysDictType>    
	 * @author       lijc
	 * @Date         2017年2月7日 下午4:03:19
	 */
	public Pagination<TSysDictType> queryDictTypeList(Pagination<TSysDictType> paramBean);
	
	/**
	 * @Title:       getDictTypeList 
	 * @Description: 获取所有字典类型
	 * @param:       @param dictType
	 * @param:       @return    
	 * @return:      List<TSysDictType>    
	 * @author       lijc
	 * @Date         2017年2月8日 下午3:28:15
	 */
	public List<TSysDictType> getDictTypeList(TSysDictType dictType);
	
	/**
	 * @Title:       getDictType 
	 * @Description: 根据类型ID获取字典类型数据 
	 * @param:       @param typeId
	 * @param:       @return
	 * @param:       @throws Exception    
	 * @return:      TSysDictType    
	 * @author       lijc
	 * @Date         2017年2月8日 上午10:50:29
	 */
	public TSysDictType getDictType(long typeId) throws Exception;
	
	/**
	 * @Title:       addDictType 
	 * @Description: 新增字典类型 
	 * @param:       @param dictType
	 * @param:       @return
	 * @param:       @throws ServiceException    
	 * @return:      long    
	 * @author       lijc
	 * @Date         2017年2月8日 上午11:17:20
	 */
	public long addDictType(TSysDictType dictType) throws ServiceException;
	
   /**
    * @Title:       updateDictType 
    * @Description: 编辑字典类型 
    * @param:       @param dictType
    * @param:       @return
    * @param:       @throws ServiceException    
    * @return:      boolean    
    * @author       lijc
    * @Date         2017年2月8日 上午11:15:57
    */
   public boolean updateDictType(TSysDictType dictType) throws ServiceException;
   
   /**
    * @Title:       delDictType 
    * @Description: 删除字典类型
    * @param:       @param typeId
    * @param:       @return
    * @param:       @throws Exception    
    * @return:      boolean    
    * @author       lijc
    * @Date         2017年2月8日 上午11:16:42
    */
   public boolean delDictType(long typeId) throws Exception;
   
   /**
	 * @Title:       queryDictInfoList 
	 * @Description: 查询字典列表方法
	 * @param:       @param paramBean
	 * @param:       @return    
	 * @return:      Pagination<TSysDictInfo>    
	 * @author       lijc
	 * @Date         2017年2月7日 下午4:03:19
	 */
	public Pagination<TSysDictInfo> queryDictInfoList(Pagination<TSysDictInfo> paramBean);
	
	/**
	 * @Title:       getDictInfo 
	 * @Description: 根据字典ID获取字典数据 
	 * @param:       @param dictId
	 * @param:       @return
	 * @param:       @throws Exception    
	 * @return:      TSysDictInfo    
	 * @author       lijc
	 * @Date         2017年2月8日 上午10:50:29
	 */
	public TSysDictInfo getDictInfo(long dictId) throws Exception;
	
	/**
	 * @Title:       addDictInfo 
	 * @Description: 新增字典
	 * @param:       @param dictInfo
	 * @param:       @return
	 * @param:       @throws ServiceException    
	 * @return:      long    
	 * @author       lijc
	 * @Date         2017年2月8日 上午11:17:20
	 */
	public long addDictInfo(TSysDictInfo dictInfo) throws ServiceException;
	
    /**
     * @Title:       updateDictInfo 
     * @Description: 编辑字典
     * @param:       @param dictInfo
     * @param:       @return
     * @param:       @throws ServiceException    
     * @return:      boolean    
     * @author       lijc
     * @Date         2017年2月8日 上午11:15:57
     */
    public boolean updateDictInfo(TSysDictInfo dictInfo) throws ServiceException;
	  
    /**
     * @Title:       delDictInfo 
     * @Description: 删除字典
     * @param:       @param dictId
     * @param:       @return
     * @param:       @throws Exception    
     * @return:      boolean    
     * @author       lijc
     * @Date         2017年2月8日 上午11:16:42
     */
    public boolean delDictInfo(long dictId) throws Exception;
   
}
