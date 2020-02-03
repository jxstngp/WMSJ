package com.samton.platform.common.service.impl;

import com.samton.platform.common.dao.CommonSelectMapper;
import com.samton.platform.common.service.ICommonSelectService;
import com.samton.platform.framework.bean.CommonSelect;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/**
 * @Description: 自定义标签：下拉列表框Service实现类
 * @Author: YangYangen
 * @Date: 2019/10/26 10:26
 * Copyright (c) 2019, Samton. All rights reserved
*/
@Service("commonSelectService")
public class CommonSelectServiceImpl implements ICommonSelectService {
    @Resource
    private CommonSelectMapper commonSelectMapper;

    /**
     * @param commonSelect
     * @return
     */
    @Override
    public List<Map<String, String>> loadOptions(CommonSelect commonSelect) {
        return commonSelectMapper.selectOptions(commonSelect);
    }

}
