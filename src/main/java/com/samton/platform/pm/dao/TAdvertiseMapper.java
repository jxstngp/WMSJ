package com.samton.platform.pm.dao;

import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.pm.bean.entity.TAdvertise;
import com.samton.platform.pm.bean.vo.TAdvertiseVo;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface TAdvertiseMapper {
    /**
     * 分页查询Vo
     * @param paramBean
     * @param rowBounds
     * @return
     */
    List<TAdvertiseVo> queryPageListVo(Pagination<TAdvertiseVo> paramBean, RowBounds rowBounds)  throws Exception;

    /**
     * 导出Vo
     * @param paramBean
     * @param rowBounds
     * @return
     */
    List<Map<String, Object>> exportPageListVo(Pagination<TAdvertiseVo> paramBean, RowBounds rowBounds)  throws Exception;
    /**
     *
     * @param advertiseId
     * @return
     * @throws Exception
     */
    TAdvertiseVo selectAdvertiseById(Integer advertiseId) throws Exception;

    TAdvertiseVo selectAdvertiseByPostion(Short position) throws Exception;


    int deleteByPrimaryKey(Integer advertiseId);

    int insert(TAdvertise record);

    int insertSelective(TAdvertise record);

    TAdvertise selectByPrimaryKey(Integer advertiseId);

    int updateByPrimaryKeySelective(TAdvertise record);

    int updateByPrimaryKey(TAdvertise record);

}