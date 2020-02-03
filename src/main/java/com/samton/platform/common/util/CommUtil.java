package com.samton.platform.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

/**
 * @Description:通用工具类
 * @author:     Alex
 * @date:        2017年2月21日 下午4:03:27
 * Copyright (c) 2016, Samton. All rights reserved
 */
@SuppressWarnings("rawtypes")
public class CommUtil {
	
	// 定义分割常量 （#在集合中的含义是每个元素的分割，|主要用于map类型的集合用于key与value中的分割） 
    private static final String SEP1 = ",";  
    private static final String SEP2 = "|";  
	
	public static boolean stringEmptyOrNull(String str){
		if(str == null || "".equals(str)){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @Title:        getLimitSql 
	 * @Description:  拼接分页语句
	 * @param:        @param pageSize
	 * @param:        @param pageNo
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月21日 下午4:11:52
	 */
	public static String getLimitSql(Integer pageSize,Integer pageNo){
		return "limit " + pageSize + "offset " + (pageNo -1) * pageSize;
	}

	/**
	 * 
	 * @Title:        ListToString 
	 * @Description:  List转换String 
	 * @param:        @param list	需要转换的List 
	 * @param:        @return    	转换后的字符串 
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月21日 下午4:12:52
	 */
    public static String ListToString(List<?> list) {  
        StringBuffer sb = new StringBuffer();  
        if (list != null && list.size() > 0) {  
            for (int i = 0; i < list.size(); i++) {  
                if (list.get(i) == null || list.get(i) == "") {  
                    continue;  
                }  
                // 如果值是list类型则调用自己  
                if (list.get(i) instanceof List) {  
                    sb.append(ListToString((List<?>) list.get(i)));  
                    sb.append(list.size()-1==i?"":SEP1);  
                } else if (list.get(i) instanceof Map) {  
                    sb.append(MapToString((Map<?, ?>) list.get(i)));  
                    sb.append(list.size()-1==i?"":SEP1);  
                } else {  
                    sb.append(list.get(i));  
                    sb.append(list.size()-1==i?"":SEP1);  
                }  
            }  
        }  
        return sb.toString();  
    }  
  
    /**
     * 
     * @Title:        MapToString 
     * @Description:  Map转换String 
     * @param:        @param map	需要转换的Map 
     * @param:        @return		转换后的字符串 
     * @return:       String    
     * @author        Alex
     * @Date          2017年2月21日 下午4:13:39
     */
    public static String MapToString(Map<?, ?> map) {  
        StringBuffer sb = new StringBuffer();  
        // 遍历map  
        for (Object obj : map.keySet()) {  
            if (obj == null) {  
                continue;  
            }  
            Object key = obj;  
            Object value = map.get(key);  
            if (value instanceof List<?>) {  
                sb.append(key.toString() + SEP1 + ListToString((List<?>) value));  
                sb.append(SEP2);  
            } else if (value instanceof Map<?, ?>) {  
                sb.append(key.toString() + SEP1  
                        + MapToString((Map<?, ?>) value));  
                sb.append(SEP2);  
            } else {  
                sb.append(key.toString() + SEP1 + value.toString());  
                sb.append(SEP2);  
            }  
        }  
        return sb.toString();  
    }  
    
    /**
     * 
     * @Title:        jsonToHashMap 
     * @Description:  将json格式的字符串解析成Map对象 <li> 	json格式：{"name":"admin","retries":"3fff","testname":"ddd","testretries":"fffffffff"}  
     * @param:        @param object
     * @param:        @return    
     * @return:       HashMap<String,Object>    
     * @author        Alex
     * @Date          2017年2月21日 下午4:14:46
     */
	public static HashMap<String, Object> jsonToHashMap(Object object)  
    {  
        HashMap<String, Object> data = new HashMap<String, Object>();  
        // 将json字符串转换成jsonObject  
        JSONObject jsonObject = JSONObject.fromObject(object);  
        Iterator it = jsonObject.keys();  
        // 遍历jsonObject数据，添加到Map对象  
        while (it.hasNext())  
        {  
            String key = String.valueOf(it.next());  
            Object value = jsonObject.get(key);  
            if(value==null || "null".endsWith(value.toString())) continue;
            data.put(key, value);  
        }  
        return data;  
    }  
    
    /**
     * 
     * @Title:        jsonToHashMap 
     * @Description:  将json格式的字符串解析成Map对象 <li>		json格式：{"name":"admin","retries":"3fff","testname":"ddd","testretries":"fffffffff"} 
     * @param:        @param jsonObject
     * @param:        @return    
     * @return:       HashMap<String,String>    
     * @author        Alex
     * @Date          2017年2月21日 下午4:15:31
     */
    public static HashMap<String, String> jsonToHashMap(JSONObject jsonObject)  
    {  
        HashMap<String, String> data = new HashMap<String, String>();  
        // 将json字符串转换成jsonObject  
        Iterator it = jsonObject.keys();  
        // 遍历jsonObject数据，添加到Map对象  
        while (it.hasNext())  
        {  
            String key = String.valueOf(it.next());  
            Object value = jsonObject.get(key);  
            if(value==null || "null".endsWith(value.toString())||StringUtils.isEmpty(value+"")) continue;
            data.put(key, value+"");  
        }  
        return data;  
    }  
    /**
     * 
     * @Title:        jsonToString 
     * @Description:  将json格式的转成string
     * @param:        @param jsonObj
     * @param:        @return    
     * @return:       String    
     * @throws 
     * @author        yokaihei
     * @Date          2015年10月22日 下午7:19:12
     */
    public static String jsonToString(JSONObject jsonObj){
		if(jsonObj==null || jsonObj.size()<1) return "";
		StringBuffer str = new StringBuffer();
        Iterator it = jsonObj.keys();  
        while(it.hasNext()){
        	Object rs = it.next();
        	if( jsonObj.get(rs )!=null && StringUtils.isNotEmpty( jsonObj.get( rs.toString() ).toString() ) && !"null".equals(jsonObj.get(rs).toString())){
        		str.append(rs);
            	str.append("=");
            	str.append(jsonObj.get(rs));
            	str.append("&");
        	}
        }  
        return str.length()>0?str.substring(0, str.length()-1):"";
	}
	
}