package com.samton.platform.common.service;

import com.samton.platform.framework.mybatis.pagination.Pagination;

import java.util.List;
import java.util.Map;

/**
 * @Description: Service基类
 * @Author: YangYangen
 * @Date: 2019/10/26 11:07
 * Copyright (c) 2019, Samton. All rights reserved
*/
public interface ICrudService<T, I> extends IBaseService {
    /**
     * 分页查询
     * @param paramBean
     * @return
     */
    public Pagination<T> queryPageList(Pagination<T> paramBean) throws Exception;

    /**
     * 导出
     * @param paramBean
     * @return
     */
    public Pagination<Map<String, Object>> exportPageList(Pagination<T> paramBean) throws Exception;
    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    T selectById(I id) throws Exception;

    /**
     * 查询（根据ID 批量查询）
     * @param idList 主键ID列表
     * @return
     */
    List<T> selectBatchIds(List<I> idList) throws Exception;

    /**
     * 查询（根据 columnMap 条件）
     * @param columnMap 表字段 map 对象
     * @return
     */
    List<T> selectByMap(Map<String, Object> columnMap) throws Exception;

    /**
     * 根据 entity 条件，查询一条记录
     * @param entity
     * @return
     */
    T selectOne(T entity) throws Exception;

    /**
     * 根据 entity 条件，查询总记录数
     * @param entity
     * @return
     */
    int selectCount(T entity) throws Exception;

    /**
     * 插入一条记录
     * @param entity
     * @return
     */
    boolean insert(T entity) throws Exception;

    /**
     * 插入一条记录（选择字段， null 字段不插入）
     * @param entity
     * @return
     */
    boolean insertSelective(T entity) throws Exception;

    /**
     * 插入（批量）
     * @param entityList
     * @return
     */
    boolean insertBatch(List<T> entityList) throws Exception;

    /**
     * 根据 ID 修改
     * @param entity 实体对象
     * @return
     */
    boolean updateById(T entity) throws Exception;

    /**
     * 根据 ID 选择修改
     * @param entity
     * @return
     */
    boolean updateSelectiveById(T entity) throws Exception;

    /**
     * 根据ID 批量更新
     * @param entityList 实体对象列表
     * @return
     */
    boolean updateBatchById(List<T> entityList) throws Exception;

    /**
     * 根据 ID 删除
     * @param id
     * @return
     */
    boolean deleteById(I id) throws Exception;

    /**
     * 根据 columnMap 条件，删除记录
     * @param columnMap 表字段 map 对象
     * @return
     */
    boolean deleteByMap(Map<String, Object> columnMap) throws Exception;

    /**
     * 根据 entity 条件，删除记录
     * @param entity
     * @return
     */
    boolean deleteSelective(T entity) throws Exception;

    /**
     * 删除（根据ID 批量删除）
     * @param idList
     * @return
     */
    boolean deleteBatchIds(List<I> idList) throws Exception;
}
