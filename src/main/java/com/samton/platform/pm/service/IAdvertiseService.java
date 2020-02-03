package com.samton.platform.pm.service;


import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.pm.bean.entity.TAdvertise;
import com.samton.platform.pm.bean.vo.TAdvertiseVo;

import java.util.Map;

public interface IAdvertiseService  {
    /**
     * 分页查询Vo
     * @param paramBean
     * @return
     */
    public Pagination<TAdvertiseVo> queryPageListVo(Pagination<TAdvertiseVo> paramBean) throws Exception;

    /**
     * 新增功能
     * @param advertise
     * @return
     */
    public Integer addAdvertise(TAdvertise advertise) throws Exception;

    /**
     * 按主键ID查询广告信息
     * @param advertiseId
     * @return
     */
    public TAdvertiseVo loadAdvertiseById(Integer advertiseId) throws Exception;
    /**
     * 编辑
     * @param advertise
     * @return
     */
    public Integer editAdvertise(TAdvertise advertise) throws Exception;

    /**
     * 删除
     * @param advertise
     * @return
     * @throws Exception
     */
    public Integer delAdvertise(TAdvertise advertise) throws Exception;

    /**
     * 导出
     * @param paramBean
     * @return
     */
    public Pagination<Map<String, Object>> exportPageListVo(Pagination<TAdvertiseVo> paramBean) throws Exception;


}
