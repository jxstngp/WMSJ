package com.samton.web.manage.customer.constant;

import com.samton.platform.framework.exception.constant.ExpCodeConstant;
import org.springframework.stereotype.Component;

//业务级别按模块自己划分，参考Module实体，1\2\3\4\5\6\7\8\9依次递增
//后三位错误编码，倒数第四位以上，模块ID
//每个模块报错编号预计1000个
@Component
public class CustomerExpCodeConstant extends ExpCodeConstant {

	public static final String CUST_EDIT_COLUMN_ERROR = "1001";
	public static final String CUST_EDIT_ID_ERROR = "1002";
	public static final String CUST_EDIT_SET_ERROR = "1005";


	static {
		msgMap.put(CUST_EDIT_COLUMN_ERROR, "编辑失败，请重新输入！");
		msgMap.put(CUST_EDIT_ID_ERROR, "参数错误，请重新输入！");
		msgMap.put(CUST_EDIT_SET_ERROR, "禁用或启用操作失败，请联系管理员！");
	}

}
