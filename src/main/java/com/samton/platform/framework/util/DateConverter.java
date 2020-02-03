package com.samton.platform.framework.util;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @Description: 自定义日期转换器yyyy-MM-dd
 * @Author: YangYangen
 * @Date: 2019/10/28 16:32
 * Copyright (c) 2019, Samton. All rights reserved
*/
public class DateConverter  implements Converter<String, Date> {
    public Date convert(String source) {
        //实现将字符串转成日期类型(格式是yyyy-MM-dd)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null; //如果参数绑定失败返回null
    }
}
