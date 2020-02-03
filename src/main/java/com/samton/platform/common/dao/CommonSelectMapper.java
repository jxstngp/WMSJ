package com.samton.platform.common.dao;

import com.samton.platform.framework.bean.CommonSelect;

import java.util.List;
import java.util.Map;
/**
 * @Description: 自定义标签：下拉列表框Mapper接口
 * @Author: YangYangen
 * @Date: 2019/10/26 10:26
 * Copyright (c) 2019, Samton. All rights reserved
*/
public interface CommonSelectMapper {
    /**
     * 根据条件查询出option
     * @param commonSelect
     * @return
     */
    List<Map<String, String>> selectOptions(CommonSelect commonSelect);

}
