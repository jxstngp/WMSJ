package com.samton.web.manage.customer.dao;

import com.samton.platform.common.dao.AutoMapperInteger;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.web.manage.customer.bean.entity.TCustomer;
import com.samton.web.manage.customer.bean.vo.CustomerVo;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * @Description: 客户管理（客户信息、客户账户）Mapper接口
 * @Author: YangYangen
 * @Date: 2019/10/26 14:59
 * Copyright (c) 2019, Samton. All rights reserved
*/
public interface TCustomerMapper extends AutoMapperInteger<TCustomer> {

    /**
     * 客户账户管理分页查询
     * @param paramBean
     * @param rowBounds
     * @return
     */
    List<TCustomer> queryPageAccountList(Pagination<TCustomer> paramBean, RowBounds rowBounds) throws Exception;

    /**
     * 客户账户管理导出
     * @param paramBean
     * @param rowBounds
     * @return
     */
    List<Map<String, Object>> exportPageAccountList(Pagination<TCustomer> paramBean, RowBounds rowBounds) throws Exception;

    /**
     * 查询客户信息
     * @param custId
     * @return
     * @throws Exception
     */
    public CustomerVo selectCustomerVoById(Integer custId) throws Exception;
}