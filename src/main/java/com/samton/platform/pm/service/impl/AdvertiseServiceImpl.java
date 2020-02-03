package com.samton.platform.pm.service.impl;


import com.samton.platform.framework.mybatis.pagination.PageContext;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.framework.util.CurrentUtil;
import com.samton.platform.pm.bean.entity.TAdvertise;
import com.samton.platform.pm.bean.vo.TAdvertiseVo;
import com.samton.platform.pm.dao.TAdvertiseMapper;
import com.samton.platform.pm.dao.TSysLogMapper;
import com.samton.platform.pm.service.IAdvertiseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Transactional
@Service("advertiseService")
public class AdvertiseServiceImpl implements IAdvertiseService {
    @Resource
    private TSysLogMapper logMapper;
    @Resource
    private TAdvertiseMapper advertiseMapper;

    /**
     * 分页查询Vo
     *
     * @param paramBean
     * @return
     */
    @Override
    public Pagination<TAdvertiseVo> queryPageListVo(Pagination<TAdvertiseVo> paramBean) throws Exception {
        Pagination<TAdvertiseVo> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
        List<TAdvertiseVo> list = advertiseMapper.queryPageListVo(paramBean, pagination.getRowBounds());
        pagination.setData(list);
        return pagination;
    }

    /**
     * 新增功能
     *
     * @param advertise
     * @return
     */
    @Override
    public Integer addAdvertise(TAdvertise advertise) throws Exception {
        CurrentUtil.setBaseBeanByInsert(advertise);
        CurrentUtil.setBaseBeanByModify(advertise);
        TAdvertiseVo advertises=advertiseMapper.selectAdvertiseByPostion(advertise.getPosition());
        if (advertises !=null  &&  advertises.getPosition()!=null) {
            return -2;
        }
            return advertiseMapper.insertSelective(advertise);
    }

    /**
     * 按主键ID查询功能信息
     *
     * @param advertiseId
     * @return
     */
    @Override
    public TAdvertiseVo loadAdvertiseById(Integer advertiseId) throws Exception {
        return advertiseMapper.selectAdvertiseById(advertiseId);
    }


    /**
     * 编辑功能
     *
     * @param advertise
     * @return
     */
    @Override
    public Integer editAdvertise(TAdvertise advertise) throws Exception {
        CurrentUtil.setBaseBeanByModify(advertise);
        int flag = advertiseMapper.updateByPrimaryKeySelective(advertise);
        return flag;
    }

    /**
     * 删除功能
     *
     * @param advertise
     * @return
     */
    @Override
    public Integer delAdvertise(TAdvertise advertise) throws Exception {
        /*TAdvertiseVo users =advertiseMapper.selectAdvertiseById(advertiseVo.gettAdvertise().getAdvertiseId());
        if (users.equals(null)||users !=null) {
            return 0;
        }*/
        CurrentUtil.setBaseBeanByModify(advertise);
         int res= advertiseMapper.deleteByPrimaryKey(advertise.getAdvertiseId());
         if(res>0){
             return 1;
         }
        return 0;
    }

    /**
     * 导出功能
     *
     * @param paramBean
     * @return
     */
    @Override
    public Pagination<Map<String, Object>> exportPageListVo(Pagination<TAdvertiseVo> paramBean) throws Exception {
        Pagination<Map<String, Object>> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
        List<Map<String, Object>> datas = advertiseMapper.exportPageListVo(paramBean, pagination.getRowBounds());
        pagination.setData(datas);
        return pagination;
    }
}
