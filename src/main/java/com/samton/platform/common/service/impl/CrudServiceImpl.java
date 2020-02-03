package com.samton.platform.common.service.impl;

import com.samton.platform.common.dao.CrudMapper;
import com.samton.platform.common.service.ICrudService;
import com.samton.platform.framework.mybatis.pagination.PageContext;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @Description: Service实现类（ 泛型：M 是 mapper 对象， T 是实体 ， I 是主键泛型 ）
 * @Author: YangYangen
 * @Date: 2019/10/26 11:06
 * Copyright (c) 2019, Samton. All rights reserved
*/
public class CrudServiceImpl<M extends CrudMapper<T, I>, T, I> extends BaseServiceImpl implements ICrudService<T, I> {

    /**
     * 持久化对象
     */
    @Autowired
    protected M mapper;

    /**
     * 判断数据库操作是否成功
     *
     * @param result
     *            数据库操作返回影响条数
     * @return boolean
     */
    protected boolean retBool(int result) throws Exception {
        return (result >= 1) ? true : false;
    }

    /**
     * 分页查询
     *
     * @param paramBean
     * @return
     */
    @Override
    public Pagination<T> queryPageList(Pagination<T> paramBean) throws Exception {
        Pagination<T> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
        List<T> list = mapper.queryPageList(paramBean, pagination.getRowBounds());
        pagination.setData(list);
        return pagination;
    }

    /**
     * 导出
     *
     * @param paramBean
     * @return
     */
    @Override
    public Pagination<Map<String, Object>> exportPageList(Pagination<T> paramBean) throws Exception {
        Pagination<Map<String, Object>> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
        List<Map<String, Object>> list = mapper.exportPageList(paramBean, pagination.getRowBounds());
        pagination.setData(list);
        return pagination;
    }

    @Override
    public T selectById(I id) throws Exception {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> selectBatchIds(List<I> idList) throws Exception {
        return mapper.selectBatchIds(idList);
    }

    @Override
    public List<T> selectByMap(Map<String, Object> columnMap) throws Exception {
        return mapper.selectByMap(columnMap);
    }

    @Override
    public T selectOne(T entity) throws Exception {
        return mapper.selectOne(entity);
    }

    @Override
    public int selectCount(T entity) throws Exception {
        return mapper.selectCount(entity);
    }

    @Override
    public boolean insert(T entity) throws Exception {
        return retBool(mapper.insert(entity));
    }

    @Override
    public boolean insertSelective(T entity) throws Exception {
        return retBool(mapper.insertSelective(entity));
    }

    @Override
    public boolean insertBatch(List<T> entityList) throws Exception {
        return retBool(mapper.insertBatch(entityList));
    }

    @Override
    public boolean updateById(T entity) throws Exception {
        return retBool(mapper.updateByPrimaryKey(entity));
    }

    @Override
    public boolean updateSelectiveById(T entity) throws Exception {
        return retBool(mapper.updateByPrimaryKeySelective(entity));
    }

    @Override
    public boolean updateBatchById(List<T> entityList) throws Exception {
        return retBool(mapper.updateBatchById(entityList));
    }

    @Override
    public boolean deleteById(I id) throws Exception {
        return retBool(mapper.deleteByPrimaryKey(id));
    }

    @Override
    public boolean deleteByMap(Map<String, Object> columnMap) throws Exception {
        return retBool(mapper.deleteByMap(columnMap));
    }

    @Override
    public boolean deleteSelective(T entity) throws Exception {
        return retBool(mapper.deleteSelective(entity));
    }

    @Override
    public boolean deleteBatchIds(List<I> idList) throws Exception {
        return retBool(mapper.deleteBatchIds(idList));
    }
}
