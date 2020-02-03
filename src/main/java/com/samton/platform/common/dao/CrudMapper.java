package com.samton.platform.common.dao;

import com.samton.platform.framework.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * @Description: DAO支持类实现
 * @Author: YangYangen
 * @Date: 2019/10/26 11:06
 * Copyright (c) 2019, Samton. All rights reserved
*/
public interface CrudMapper<T, I> extends BaseMapper {

    /**
     * 分页查询
     * @param paramBean
     * @param rowBounds
     * @return
     */
    List<T> queryPageList(Pagination<T> paramBean, RowBounds rowBounds) throws Exception;

    /**
     * 导出
     * @param paramBean
     * @param rowBounds
     * @return
     */
    List<Map<String, Object>> exportPageList(Pagination<T> paramBean, RowBounds rowBounds) throws Exception;

    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    T selectByPrimaryKey(I id) throws Exception;

    /**
     * 查询（根据ID 批量查询）
     * @param idList
     * @return
     */
    List<T> selectBatchIds(@Param("idList") List<I> idList) throws Exception;

    /**
     * 查询（根据 columnMap 条件）
     * @param columnMap
     * @return
     */
    List<T> selectByMap(@Param("columnMap") Map<String, Object> columnMap) throws Exception;

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
     * @param record
     * @return
     */
    int insert(T record) throws Exception;

    /**
     * 插入一条记录（选择字段， null 字段不插入）
     * @param record
     * @return
     */
    int insertSelective(T record) throws Exception;

    /**
     * 插入（批量）
     * @param entityList 实体对象列表
     * @return
     */
    int insertBatch(@Param("entityList") List<T> entityList) throws Exception;

    /**
     * 根据 ID 选择修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record) throws Exception;

    /**
     * 根据 ID 修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record) throws Exception;

    /**
     * 根据ID 批量更新
     * @param entityList 实体对象列表
     * @return
     */
    int updateBatchById(@Param("entityList") List<T> entityList) throws Exception;

    /**
     * 根据 ID 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(I id) throws Exception;

    /**
     * 根据 columnMap 条件，删除记录
     * @param columnMap 表字段 map 对象
     * @return
     */
    int deleteByMap(@Param("columnMap") Map<String, Object> columnMap) throws Exception;

    /**
     * 根据 entity 条件，删除记录
     * @param entity
     * @return
     */
    int deleteSelective(T entity) throws Exception;

    /**
     * 删除（根据ID 批量删除）
     * @param idList 主键ID列表
     * @return
     */
    int deleteBatchIds(@Param("idList") List<I> idList) throws Exception;
}
