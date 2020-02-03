package com.samton.web.manage.customer.service.impl;

import com.samton.platform.common.service.impl.SuperServiceIntegerImpl;
import com.samton.platform.framework.mybatis.pagination.PageContext;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.web.manage.customer.bean.entity.TCustomer;
import com.samton.web.manage.customer.bean.vo.CustomerVo;
import com.samton.web.manage.customer.dao.TCustomerMapper;
import com.samton.web.manage.customer.service.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 客户管理（客户信息、客户账户）Service接口实现类
 * @Author: YangYangen
 * @Date: 2019/10/26 15:00
 * Copyright (c) 2019, Samton. All rights reserved
*/
@Service("customerService")
public class CustomerServiceImpl extends SuperServiceIntegerImpl<TCustomerMapper, TCustomer> implements ICustomerService {
    /**
     * 客户账户管理分页查询
     *
     * @param paramBean
     * @return
     */
    @Override
    public Pagination<TCustomer> queryPageAccountList(Pagination<TCustomer> paramBean) throws Exception {
        Pagination<TCustomer> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
        List<TCustomer> list = mapper.queryPageAccountList(paramBean, pagination.getRowBounds());
        pagination.setData(list);
        return pagination;
    }

    /**
     * 客户账户管理导出
     *
     * @param paramBean
     * @return
     */
    @Override
    public Pagination<Map<String, Object>> exportPageAccountList(Pagination<TCustomer> paramBean) throws Exception {
        Pagination<Map<String, Object>> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
        List<Map<String, Object>> list = mapper.exportPageAccountList(paramBean, pagination.getRowBounds());
        pagination.setData(list);
        return pagination;
    }

    /**
     * 查询客户信息
     *
     * @param custId
     * @return
     * @throws Exception
     */
    @Override
    public CustomerVo findCustomerVoById(Integer custId) throws Exception {
        return this.mapper.selectCustomerVoById(custId);
    }
}
