package com.samton.platform.common.service.impl;

import com.samton.platform.common.dao.AutoMapperInteger;

/**
 * @Description: 主键 Integer 类型 IService 实现类（ 泛型：M 是 mapper 对象， T 是实体 ）
 * @Author: YangYangen
 * @Date: 2019/10/26 11:06
 * Copyright (c) 2019, Samton. All rights reserved
*/
public class SuperServiceIntegerImpl<M extends AutoMapperInteger<T>, T> extends CrudServiceImpl<M, T, Integer> {

}
