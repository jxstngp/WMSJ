package com.samton.web.manage.customer.controller;

import com.samton.platform.framework.base.BaseController;
import com.samton.platform.framework.constant.WebConstant;
import com.samton.platform.framework.exception.ServiceException;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.pm.bean.entity.TSysLog;
import com.samton.platform.pm.constant.PmStateConstant;
import com.samton.platform.pm.service.ILogService;
import com.samton.web.manage.customer.bean.entity.TCustomer;
import com.samton.web.manage.customer.bean.vo.CustomerVo;
import com.samton.web.manage.customer.constant.CustomerExpCodeConstant;
import com.samton.web.manage.customer.service.ICustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 客户管理（客户信息、客户账户）Controller
 * @Author: YangYangen
 * @Date: 2019/10/26 15:03
 * Copyright (c) 2019, Samton. All rights reserved
*/
@Controller
@RequestMapping("/manage/customer/")
public class CustomerController extends BaseController {

    @Resource
    private ICustomerService customerService;
    @Resource
    private ILogService logService;

    /**
     * 客户信息管理分页
     * @param paramBean
     * @param customerVo
     * @return
     * @throws Exception
     */
    @RequestMapping("queryCustomerInfoList" + WebConstant.PAGE_SUFFIX)
    public String queryCustomerInfoList(Pagination<TCustomer> paramBean, CustomerVo customerVo) throws Exception{
        paramBean.setSearch(customerVo);
        Pagination<TCustomer> pageData = customerService.queryPageList(paramBean);
        this.addAttr("pageData", pageData);
        return "customer/custInfoManage";
    }

    /**
     * 客户信息管理导出
     * @param paramBean
     * @param customerVo
     * @return
     * @throws Exception
     */
    @RequestMapping("exportCustomerInfoList" + WebConstant.BUSINESS_SUFFIX)
    public String exportCustomerInfoList(Pagination<TCustomer> paramBean, CustomerVo customerVo) throws Exception {
        paramBean.setSearch(customerVo);
        Pagination<Map<String, Object>> pageData = customerService.exportPageList(paramBean);
        logService.addLog(new TSysLog("客户信息管理-导出", "导出客户管理信息！", PmStateConstant.LOG_PLATFORM));
        String title = "客户学号,微信昵称,手机号码,绑定微信,注册类型,注册日期";
        List<String> colNames = new ArrayList<String>();
        colNames.add("cust_code");
        colNames.add("wechat_name");
        colNames.add("mobile");
        colNames.add("wechat_code");
        // 注册类型，数字转换成文字
        colNames.add("register_type");
        pageData.putExprotKV("register_type", "0", "手机号码");
        pageData.putExprotKV("register_type", "1", "微信");
        colNames.add("register_date");
        this.export(response,"客户信息管理" + String.format("%1$tY%1$tm%1$td", new Date()), title, colNames, pageData);
        return null;
    }

    /**
     * 客户账户管理分页
     * @param paramBean
     * @param customerVo
     * @return
     * @throws Exception
     */
    @RequestMapping("queryCustomerAccountList" + WebConstant.PAGE_SUFFIX)
    public String queryCustomerAccountList(Pagination<TCustomer> paramBean, CustomerVo customerVo) throws Exception{
        paramBean.setSearch(customerVo);
        Pagination<TCustomer> pageData = customerService.queryPageAccountList(paramBean);
        this.addAttr("pageData", pageData);
        return "customer/custAccountManage";
    }

    /**
     * 客户账户管理导出
     * @param paramBean
     * @param customerVo
     * @return
     * @throws Exception
     */
    @RequestMapping("exportCustomerAccountList" + WebConstant.BUSINESS_SUFFIX)
    public String exportCustomerAccountList(Pagination<TCustomer> paramBean, CustomerVo customerVo) throws Exception {
        paramBean.setSearch(customerVo);
        Pagination<Map<String, Object>> pageData = customerService.exportPageAccountList(paramBean);
        logService.addLog(new TSysLog("客户账户管理-导出", "导出客户账户信息！", PmStateConstant.LOG_PLATFORM));
        String title = "客户学号,微信昵称,手机号码,客户姓名,所在省份,所在城市,详细地址";
        List<String> colNames = new ArrayList<String>();
        colNames.add("cust_code");
        colNames.add("wechat_name");
        colNames.add("mobile");
        colNames.add("cust_name");
        colNames.add("province_name");
        colNames.add("city_name");
        colNames.add("address");
        this.export(response,"客户账户管理" + String.format("%1$tY%1$tm%1$td", new Date()), title, colNames, pageData);
        return null;
    }

    /**
     * 客户信息查看
     * @param custId
     * @return
     * @throws Exception
     */
    @RequestMapping("viewCustomer" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> viewCustomer(Integer custId) throws Exception {
        CustomerVo customerVo = customerService.findCustomerVoById(custId);
        //转换成VO对象属性值，比如转义相关字段含义
        customerVo.setRegisterTypeCN(customerVo.getRegisterType() == 0 ? "手机号码" : "微信");

        return this.getResultMap(true, customerVo);
    }

    /**
     * 客户信息编辑
     * @param tCustomer
     * @return
     * @throws Exception
     */
    @RequestMapping("editCustomer" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> editCustomer(TCustomer tCustomer) throws Exception {
        //校验主键ID
        if(tCustomer.getCustId() == null){
            throw new ServiceException(CustomerExpCodeConstant.CUST_EDIT_COLUMN_ERROR);
        }
        //执行编辑
        boolean result = customerService.updateSelectiveById(tCustomer);
        if (result) {
            logService.addLog(new TSysLog("客户信息管理-编辑", "编辑客户信息【" + tCustomer.getCustCode() + "】成功 ！", PmStateConstant.LOG_PLATFORM));
        } else {
            logService.addLog(new TSysLog("客户信息管理-编辑", "编辑客户信息【" + tCustomer.getCustCode() + "】 失败！", PmStateConstant.LOG_PLATFORM));
        }
        if (!result) {
            throw new ServiceException(CustomerExpCodeConstant.CUST_EDIT_COLUMN_ERROR);
        }
        return this.getResultMap(true, result);
    }

    /**
     * 客户信息禁用/启用
     * @param tCustomer
     * @return
     * @throws Exception
     */
    @RequestMapping("setCustomer" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> setCustomer(TCustomer tCustomer) throws Exception {
        //校验主键ID
        if(tCustomer.getCustId() == null){
            throw new ServiceException(CustomerExpCodeConstant.CUST_EDIT_COLUMN_ERROR);
        }
        //执行编辑
        boolean result = customerService.updateSelectiveById(tCustomer);
        String headStr = tCustomer.getIsForbid() == 0 ? "启用" : "禁用";
        if (result) {
            logService.addLog(new TSysLog("客户信息管理-禁用/启用", headStr+"客户【" + tCustomer.getCustCode() + "】成功 ！", PmStateConstant.LOG_PLATFORM));
        } else {
            logService.addLog(new TSysLog("客户信息管理-禁用/启用", headStr+"客户【" + tCustomer.getCustCode() + "】 失败！", PmStateConstant.LOG_PLATFORM));
        }
        if (!result) {
            throw new ServiceException(CustomerExpCodeConstant.CUST_EDIT_COLUMN_ERROR);
        }
        return this.getResultMap(true, result);
    }


}
