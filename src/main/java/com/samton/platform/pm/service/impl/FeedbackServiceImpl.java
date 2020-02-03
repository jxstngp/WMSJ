package com.samton.platform.pm.service.impl;

import com.samton.platform.common.service.impl.SuperServiceIntegerImpl;
import com.samton.platform.framework.util.CurrentUtil;
import com.samton.platform.pm.bean.entity.TFeedback;
import com.samton.platform.pm.bean.vo.TFeedbackVo;
import com.samton.platform.pm.dao.TFeedbackMapper;
import com.samton.platform.pm.dao.TSysLogMapper;
import com.samton.platform.pm.service.IFeedbackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: 用户登陆日志 Service接口实现类
 * @Author: zhixin
 * @Date: 2019/10/28 15:00
 * Copyright (c) 2019, Samton. All rights reserved
*/
@Service("feedbackService")
public class FeedbackServiceImpl extends SuperServiceIntegerImpl<TFeedbackMapper, TFeedback> implements IFeedbackService {
    @Resource
    private TSysLogMapper logMapper;
    @Resource
    private TFeedbackMapper feedbackMapper;
    /**
     * 根据手机号码ID查询模板信息
     *
     * @param feedbackId
     * @return
     * @throws Exception
     */
    @Override
    public TFeedbackVo loadFeedbackById(Integer feedbackId) throws Exception {
        return this.mapper.selectFeedbackById(feedbackId);
    }
    /**
     * 删除功能
     *
     * @param feedback
     * @return
     */
    @Override
    public Integer delFeedback(TFeedback feedback) throws Exception {
        CurrentUtil.setBaseBeanByModify(feedback);
        int res= this.mapper.deleteByPrimaryKey(feedback.getFeedbackId());
        if(res>0){
            return 1;
        }
        return 0;
    }
}
