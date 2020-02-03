package com.samton.platform.pm.controller;

import com.samton.platform.framework.base.BaseController;
import com.samton.platform.framework.constant.WebConstant;
import com.samton.platform.framework.exception.ServiceException;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.pm.bean.entity.TFeedback;
import com.samton.platform.pm.bean.entity.TSysLog;
import com.samton.platform.pm.bean.vo.TFeedbackVo;
import com.samton.platform.pm.constant.PmExpCodeConstant;
import com.samton.platform.pm.constant.PmStateConstant;
import com.samton.platform.pm.service.IFeedbackService;
import com.samton.platform.pm.service.ILogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description: 客户管理（客户信息、客户账户）Controller
 * @Author: YangYangen
 * @Date: 2019/10/26 15:03
 * Copyright (c) 2019, Samton. All rights reserved
*/
@Controller
@RequestMapping("/platform/feedback/")
public class FeedbackController extends BaseController {

    @Resource
    private IFeedbackService feedbackService;
    @Resource
    private ILogService logService;

    /**
     * 客户信息管理分页
     * @param paramBean
     * @param feedbackVo
     * @return
     * @throws Exception
     */
    @RequestMapping("queryFeedbackList" + WebConstant.PAGE_SUFFIX)
    public String queryFeedbackList(Pagination<TFeedback> paramBean, TFeedbackVo feedbackVo) throws Exception{
        paramBean.setSearch(feedbackVo);
        Pagination<TFeedback> pageData = feedbackService.queryPageList(paramBean);
        /*this.addAttr("pageData", pageData);*/
        this.request.setAttribute("pageData", pageData);
        this.request.setAttribute("feedbackVo", feedbackVo);
        return "sys/feedbackManage";
    }

    /**
     * 客户信息管理导出
     * @param paramBean
     * @param feedbackVo
     * @return
     * @throws Exception
     */
    @RequestMapping("exportFeedbackList" + WebConstant.BUSINESS_SUFFIX)
    public String exportFeedbackList(Pagination<TFeedback> paramBean, TFeedbackVo feedbackVo) throws Exception {
        paramBean.setSearch(feedbackVo);
        Pagination<Map<String, Object>> pageData = feedbackService.exportPageList(paramBean);
        logService.addLog(new TSysLog("意见反馈信息管理-导出", "导出意见反馈信息！", PmStateConstant.LOG_PLATFORM));
        String title = "客户学号,微信昵称,绑定微信,手机号码,意见反馈,反馈时间";
        List<String> colNames = new ArrayList<String>();
        colNames.add("cust_code");
        colNames.add("wechat_name");
        colNames.add("wechat_code");
        colNames.add("mobile");
        // 注册类型，数字转换成文字
        colNames.add("feedback");
        colNames.add("feedback_date");
        this.export(response,"意见反馈信息管理" + String.format("%1$tY%1$tm%1$td", new Date()), title, colNames, pageData);
        return null;
    }





    /**
     * 意见反馈信息查看
     * @param feedbackId
     * @return
     * @throws Exception
     */
    @RequestMapping("getFeedbackById" + WebConstant.NO_AUTH_SUFFIX)
    @ResponseBody
    public Map<String, Object> getFeedbackById(Integer feedbackId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        TFeedbackVo feedback = feedbackService.loadFeedbackById(feedbackId);
        map.put("feedback", feedback);
        return this.getResultMap(true, map);
    }
    /**
     *
     * @Title:        delFeedback
     * @Description:  删除意见反馈
     * @param:        @param advertiseVo
     * @param:        @param
     * @param:        @return
     * @param:        @throws Exception
     * @return:       Map<String,Object>
     * @author        zhixin
     * @Date
     */
    @RequestMapping("/delFeedback" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> delFeedback(TFeedback feedback)throws Exception {
        long result = feedbackService.delFeedback(feedback);
        if (result > 0) {
            logService.addLog(new TSysLog("意见反馈-删除", "删除意见反馈成功  ！", PmStateConstant.LOG_PLATFORM));
        } else {
            logService.addLog(new TSysLog("意见反馈-删除", "删除意见反馈失败！", PmStateConstant.LOG_PLATFORM));
            throw new ServiceException(PmExpCodeConstant.PM_FEEDBACK_DEL_NULL_ERROR);
        }
        return this.getResultMap(true, result);
    }


}
