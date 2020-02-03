package com.samton.platform.pm.controller;

import com.samton.platform.framework.base.BaseController;
import com.samton.platform.framework.constant.WebConstant;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.pm.bean.entity.TCustLoginLog;
import com.samton.platform.pm.bean.entity.TSysLog;
import com.samton.platform.pm.bean.vo.TCustLoginLogVo;
import com.samton.platform.pm.constant.PmStateConstant;
import com.samton.platform.pm.service.ICustLoginLogService;
import com.samton.platform.pm.service.ILogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description: 用户日志管理Controller
 * @Author: zhixin
 * @Date: 2019/10/29 15:03
 * Copyright (c) 2019, Samton. All rights reserved
*/
@Controller
@RequestMapping("/platform/loginlog/")
public class CustLoginLogController extends BaseController {

    @Resource
    private ICustLoginLogService custLoginLogService;
    @Resource
    private ILogService logService;

    /**
     * 用户登陆日志分页
     * @param paramBean
     * @param custLoginLogVo
     * @return
     * @throws Exception
     */
    @RequestMapping("queryCustLoginLogList" + WebConstant.PAGE_SUFFIX)
    public String queryCustLoginLogList(Pagination<TCustLoginLog> paramBean, TCustLoginLogVo custLoginLogVo) throws Exception{
        paramBean.setSearch(custLoginLogVo);
        Pagination<TCustLoginLog> pageData = custLoginLogService.queryPageList(paramBean);
        /*this.addAttr("pageData", pageData);*/
        this.request.setAttribute("pageData", pageData);
        this.request.setAttribute("custLoginLogVo", custLoginLogVo);
        return "log/custLoginLogManage";
    }

    /**
     * 用户登陆日志导出
     * @param paramBean
     * @param custLoginLogVo
     * @return
     * @throws Exception
     */
    @RequestMapping("exportCustLoginLogList" + WebConstant.BUSINESS_SUFFIX)
    public String exportCustLoginLogList(Pagination<TCustLoginLog> paramBean, TCustLoginLogVo custLoginLogVo) throws Exception {
        paramBean.setSearch(custLoginLogVo);
        Pagination<Map<String, Object>> pageData = custLoginLogService.exportPageList(paramBean);
        logService.addLog(new TSysLog("用户登陆日志信息管理-导出", "导出意见反馈信息！", PmStateConstant.LOG_PLATFORM));
        String title = "客户学号,微信昵称,绑定微信,手机号码,登陆IP,登陆方式,登陆时间";
        List<String> colNames = new ArrayList<String>();
        colNames.add("cust_code");
        colNames.add("wechat_name");
        colNames.add("wechat_code");
        colNames.add("mobile");
        // 注册类型，数字转换成文字
        colNames.add("login_ip");
        colNames.add("type");
        colNames.add("login_date");
        this.export(response,"用户登陆日志信息管理" + String.format("%1$tY%1$tm%1$td", new Date()), title, colNames, pageData);
        return null;
    }





    /**
     * 用户登陆日志信息查看
     * @param logId
     * @return
     * @throws Exception
     */
    @RequestMapping("getCustLoginLogById" + WebConstant.NO_AUTH_SUFFIX)
    @ResponseBody
    public Map<String, Object> getCustLoginLogById(Integer logId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        TCustLoginLogVo loginLogVo = custLoginLogService.loadCustLoginLogById(logId);
        map.put("loginLogVo", loginLogVo);
        return this.getResultMap(true, map);
    }



}
