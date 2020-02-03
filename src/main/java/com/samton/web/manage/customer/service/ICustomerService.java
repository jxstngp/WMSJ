package com.samton.web.manage.customer.service;

import com.samton.platform.common.service.ISuperIntegerService;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.web.manage.customer.bean.entity.TCustomer;
import com.samton.web.manage.customer.bean.vo.CustomerVo;

import java.util.Map;

/**
 * @Description: 客户管理（客户信息、客户账户）Service接口
 * @Author: YangYangen
 * @Date: 2019/10/26 14:59
 * Copyright (c) 2019, Samton. All rights reserved
*/
public interface ICustomerService extends ISuperIntegerService<TCustomer> {
    /**
     * 客户账户管理分页查询
     * @param paramBean
     * @return
     */
    public Pagination<TCustomer> queryPageAccountList(Pagination<TCustomer> paramBean) throws Exception;

    /**
     * 客户账户管理导出
     * @param paramBean
     * @return
     */
    public Pagination<Map<String, Object>> exportPageAccountList(Pagination<TCustomer> paramBean) throws Exception;

    /**
     * 查询客户信息
     * @param custId
     * @return
     * @throws Exception
     */
    public CustomerVo findCustomerVoById(Integer custId) throws Exception;
}
