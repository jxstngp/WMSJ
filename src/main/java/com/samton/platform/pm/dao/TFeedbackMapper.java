package com.samton.platform.pm.dao;

import com.samton.platform.common.dao.AutoMapperInteger;
import com.samton.platform.pm.bean.entity.TFeedback;
import com.samton.platform.pm.bean.vo.TFeedbackVo;

/**
 * @Description: 意见反馈Mapper接口
 * @Author: 支鑫
 * @Date: 2019/10/28 14:59
 * Copyright (c) 2019, Samton. All rights reserved
 */
public interface TFeedbackMapper extends AutoMapperInteger<TFeedback> {
    /**
     *通过ID查询意见反馈
     * @param feedbackId
     * @return
     * @throws Exception
     */
    TFeedbackVo selectFeedbackById(Integer feedbackId) throws Exception;
}