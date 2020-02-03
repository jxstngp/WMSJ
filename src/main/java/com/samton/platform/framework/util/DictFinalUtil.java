package com.samton.platform.framework.util;

import java.io.Serializable;

/**
 * 字典组常量类
 * 
 * @author 刘涛
 * @since 2011-11-06
 * @version 1.0
 */
public final class DictFinalUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    public static class PineDealStatus {
	/**
	 * 性别（测试）
	 */
	public static final int SEX_TYPE = 1;
	private static final String SEX_TYPE_VALUE = "SEX_TYPE";

	public static ConstantsMap map;
	static {
	    map = new ConstantsMap();
	    map.putConstant(SEX_TYPE_VALUE, SEX_TYPE);
	}
    }
}
