package com.samton.platform.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @Description:反射工具类
 * @author:     shenchu
 * @date:        2017年2月22日 上午11:51:44
 * Copyright (c) 2016, Samton. All rights reserved
 */
public class ReflectUtils {
	
	/**
	 * @Title:        getAllField 
	 * @Description:  获取类clazz的所有Field，包括其父类的Field，如果重名，以子类Field为准。
	 * @param:        @param clazz
	 * @param:        @return    
	 * @return:       Field[]    
	 * @author        shenchu
	 * @Date          2017年2月22日 上午11:52:59
	 */
    public static Field[] getAllField(Class<?> clazz) {
        ArrayList<Field> fieldList = new ArrayList<Field>();
        Field[] dFields = clazz.getDeclaredFields();
        if (null != dFields && dFields.length > 0) {
            fieldList.addAll(Arrays.asList(dFields));
        }
 
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != Object.class) {
            Field[] superFields = getAllField(superClass);
            if (null != superFields && superFields.length > 0) {
                for(Field field:superFields){
                    if(!isContain(fieldList, field)){
                        fieldList.add(field);
                    }
                }
            }
        }
        Field[] result=new Field[fieldList.size()];
        fieldList.toArray(result);
        return result;
    }
     
    /**
     * 
     * @Title:        isContain 
     * @Description:  检测Field List中是否已经包含了目标field
     * @param:        @param fieldList
     * @param:        @param field
     * @param:        @return    
     * @return:       boolean    
     * @author        shenchu
     * @Date          2017年2月22日 上午11:53:38
     */
    public static boolean isContain(ArrayList<Field> fieldList,Field field){
        for(Field temp:fieldList){
            if(temp.getName().equals(field.getName())){
                return true;
            }
        }
        return false;
    }

}
