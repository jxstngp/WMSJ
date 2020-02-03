package com.samton.platform.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Decoder;

/**
 * @ClassName: OtherUtil
 * @Description:其它工具类
 * @author: Joshua_Cheng@qq.com
 * @date: 2015年8月11日 下午3:45:57 Copyright (c) 2015, Samton. All rights reserved
 */
@SuppressWarnings({"rawtypes","unchecked","unused"})
public class OtherUtil {

	/**
	 * @Title: copyObjProperties
	 * @Description: 对象属性复制
	 * @param: @param toObj
	 * @param: @param fromObj
	 * @return: void
	 * @throws
	 * @author Joshua_Cheng@qq.com
	 * @Date 2015年8月11日 下午3:49:31
	 */
	public static void copyObjProperties(Object toObj, Object fromObj) {
		try {
			PropertyUtils.copyProperties(toObj, fromObj);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: strIds2List
	 * @Description: 逗号分隔字符串转List
	 * @param: @param strIds
	 * @param: @return
	 * @return: List<String>
	 * @throws
	 * @author Joshua_Cheng@qq.com
	 * @Date 2015年8月12日 下午6:45:34
	 */
	public static List<String> strIds2List(String strIds) {
		List<String> strings = new ArrayList<String>();
		if (StringUtils.isNotBlank(strIds)) {
			String[] strArr = strIds.split(",");
			for (String str : strArr) {
				strings.add(str);
			}
		}
		return strings;
	}

	/**
	 * 
	 * @Title: getValue
	 * @Description: 这里用一句话描述这个方法的作用
	 * @param: @param thisObj
	 * @param: @return
	 * @return: Map<String,Object>
	 * @throws
	 * @author yokaihei
	 * @Date 2015年8月17日 下午4:02:53
	 */
	public static Map<String, Object> objectToMap(Object thisObj) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Class c = Class.forName(thisObj.getClass().getName());
			Method[] m = c.getMethods();
			for (int i = 0; i < m.length; i++) {
				String method = m[i].getName();
				if (method.startsWith("get")) {
					Object value = m[i].invoke(thisObj);
					if (value != null) {
						String key = method.substring(3);
						key = key.substring(0, 1).toLowerCase() + key.substring(1);
						map.put(method, value);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 
	 * @Title:        toMap 
	 * @Description:  将Json对象转换成Map
	 * @param:        @param jsonString
	 * @param:        @return
	 * @param:        @throws JSONException    
	 * @return:       Map    
	 * @author        Alex
	 * @Date          2017年2月21日 下午4:59:12
	 */
	public static Map toMap(String jsonString) throws JSONException {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Map result = new HashMap();
		Iterator iterator = jsonObject.keys();
		String key = null;
		String value = null;
		while (iterator.hasNext()) {
			key = (String) iterator.next();
			value = jsonObject.getString(key);
			result.put(key, value);
		}
		return result;
	}

	/**
	 * @Title:        getNumbers 
	 * @Description:  截取数字
	 * @param:        @param content
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author        Joshua_Cheng@qq.com
	 * @Date          2015年9月2日 下午7:40:49
	 */
	public static String getNumbers(String content) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			return matcher.group(0);
		}
		return "";
	}
	
	/**
	 * @Title:        decodeBase64ToImage 
	 * @Description:  将Base64位编码的图片进行解码，并保存到指定目录
	 * @param:        @param base64
	 * @param:        @param path    
	 * @return:       void    
	 * @throws 
	 * @author        Joshua_Cheng@qq.com
	 * @Date          2015年10月13日 下午6:39:07
	 */
	public static void decodeBase64ToImage(String base64, String path,String name) {
		BASE64Decoder decoder = new BASE64Decoder();
	    try {
	    	 // Base64解码
	        byte[] bytes = decoder.decodeBuffer(base64);
	        for (int i = 0; i < bytes.length; ++i) {
	            if (bytes[i] < 0) {// 调整异常数据
	                bytes[i] += 256;
	            }
	        }
	        File file = new File(path);
	        if(!file.exists()) {
	        	file.mkdirs();
	        }
            OutputStream out = new FileOutputStream(path+"/"+name);      
            out.write(bytes);  
            out.flush();  
            out.close();  
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }

	/** 
	 * @Title:        compareByNewObj 
	 * @Description:  对象属性比较
	 * @param:        @param oldObj 原对象
	 * @param:        @param newObj 新对象
	 * @param:        @return    
	 * @return:       List<Map<String,Object>>    返回不同属性名 新旧值
	 * @throws 
	 * @author        Joshua_Cheng@qq.com
	 * @Date          2015年9月6日 下午2:30:56
	 */
	public static  List<Map<String,Object>> compareByNewObj(Object oldObj,Object newObj){
		 return compareByNewObj( oldObj, newObj,null);
	}
	
	/** 
	 * @Title:        compareByNewObj 
	 * @Description:  对象属性比较
	 * @param:        @param oldObj 原对象
	 * @param:        @param newObj 新对象
	 * @param:        @return    
	 * @return:       List<Map<String,Object>>    返回不同属性名 新旧值
	 * @throws 
	 * @author        Joshua_Cheng@qq.com
	 * @Date          2015年9月6日 下午2:30:56
	 */
	public static  List<Map<String,Object>> compareByNewObj(Object oldObj,Object newObj,Map<String, String> columns){
		List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
		//JSONObject obj1 = JSONObject.fromObject(oldObj);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
		    @Override
		    public boolean apply(Object source, String name, Object value) {
		        if(value == null){
		            return true;
		        }
		        return false;
		    }
		});
		JSONObject obj1 = JSONObject.fromObject(oldObj,jsonConfig);//旧值
        JSONObject obj2 = JSONObject.fromObject(newObj,jsonConfig);//新值
        for(int i=0;i<obj2.names().size();i++){
            if(!isTheSame(obj2, obj1, i,columns)){
            	Map<String,Object> record = new HashMap<String,Object>();
                loadRecord(obj2, obj1, record, i,columns);
                //如果是更新省市县 id不记录日志
                if("provinceId".equals(record.get("name")) || "cityId".equals(record.get("name")) || "areaId".equals(record.get("name"))) continue;
                maps.add(record);
            };
        }
       
		return maps;
	}

	/**
	 * 
	 * @Title:        loadRecord 
	 * @Description:  新值 和  旧值 比较
	 * @param:        @param obj1
	 * @param:        @param obj2
	 * @param:        @param record
	 * @param:        @param i    
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年2月21日 下午5:04:35
	 */
	private static void loadRecord(JSONObject obj1, JSONObject obj2, Map<String,Object> record, int i) {
		loadRecord(obj1, obj2, record, i, null);
    }
	
	/**
	 * 
	 * @Title:        loadRecord 
	 * @Description:  新值 和  旧值 比较
	 * @param:        @param obj1
	 * @param:        @param obj2
	 * @param:        @param record
	 * @param:        @param i
	 * @param:        @param columns    
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年2月21日 下午5:05:10
	 */
	private static void loadRecord(JSONObject obj1, JSONObject obj2,
            Map<String,Object> record, int i,Map<String, String> columns) {
		Object name=obj1.names().get(i);
		record.put("name", name);
		Object new_val = obj1.get( name );
		Object old_val = null;
		int len = obj2.names().indexOf(  name )  ;
    	if(columns!=null){
    		old_val= obj2.get(name);
    		if(StringUtils.isNotEmpty(columns.get(name+"_type")) && columns.get(name+"_type").equals("5")){
    			old_val = changeValue(old_val);
    			new_val = changeValue(new_val);
    		} 
    	}else{
    		if( len>-1 ){
   			 	old_val= obj2.get(name);
	       	}else{
	       		old_val = null;
	       	}
    	}
		record.put("old_val", old_val );
		record.put("new_val", new_val );
    }
	
	/**
	 * 
	 * @Title:        changeValue 
	 * @Description:   1.00 变成1  1.010 变成1.01 
	 * @param:        @param obj    
	 * @return:       void    
	 * @throws 
	 * @author        yokaihei
	 * @Date          2015年12月22日 下午4:53:19
	 */
	private static Object changeValue(Object obj){
		if(obj!=null){
			String head = obj.toString().split("\\.")[0];
			String foot = "";
			if( obj.toString().split("\\.").length>1){
				foot =  obj.toString().split("\\.")[1]  ;
				while(foot.endsWith("0")){
					foot=foot.substring(0, foot.length()-1);
				}
				 
			}
			obj =  head + ( foot.equals("") ?"":("."+foot) );
		}
		return obj;
	};
		
	/**
	 * 
	 * @Title:        isTheSame 
	 * @Description:  新值 和  旧值 比较
	 * @param:        @param obj1
	 * @param:        @param obj2
	 * @param:        @param i
	 * @param:        @return    
	 * @return:       boolean    
	 * @author        Alex
	 * @Date          2017年2月21日 下午5:06:07
	 */
	private static boolean isTheSame(JSONObject obj1, JSONObject obj2, int i) {
    	 return isTheSame( obj1,  obj2, i,null);
    }
    
    /**
     * 
     * @Title:        isTheSame 
     * @Description:  新值 和  旧值 比较
     * @param:        @param obj1
     * @param:        @param obj2
     * @param:        @param i
     * @param:        @param columns
     * @param:        @return    
     * @return:       boolean    
     * @author        Alex
     * @Date          2017年2月21日 下午5:06:22
     */
    private static boolean isTheSame(JSONObject obj1, JSONObject obj2, int i,Map<String, String> columns) {
    	Object name= obj1.names().get(i);
    	int len = obj2.names().indexOf(  name )  ;
    	if(columns!=null && len>-1){
    		if(StringUtils.isNotEmpty(columns.get(name+"_type")) && columns.get(name+"_type").equals("5")){
    			//类型＝5 int类型
    			Object v1 = obj1.get(name);
    			Object v2 = obj2.get(name);
    			if(v1!=null && v2!=null ){
    				int i1 = Integer.parseInt( v1.toString().split("\\.")[0] );
    				int i2 = Integer.parseInt( v2.toString().split("\\.")[0] );
    				boolean f1=Integer.compare(i1,i2)==0;//点之前的位数是否相等
    				int i3=0;
    				int i4=0;
    				if( v1.toString().split("\\.").length>1){
    					i3=Integer.parseInt( v1.toString().split("\\.")[1] );
    				}
    				if( v2.toString().split("\\.").length>1){
    					i4=Integer.parseInt( v2.toString().split("\\.")[1] );
    				}
    				boolean f2=Integer.compare(i3,i4)==0;//点之后的位数是否相等
    				return f1&&f2;
    			}
    			return false;
    		}
    	}
    	if( len>-1){
    		return String.valueOf(obj1.get(name)).equals(  String.valueOf(obj2.get( name )) );
    	}
        return false ;
    }
	
    /**
     * 
     * @Title:        isNotNull 
     * @Description:  判断是否为空
     * @param:        @param object
     * @param:        @return    
     * @return:       boolean    
     * @author        Alex
     * @Date          2017年2月21日 下午5:06:44
     */
    public static boolean isNotNull(Object object) {
    	try {
    		if(object != null && !"".equals(object.toString().trim()))return true;
		} catch (Exception e) {
			return false;
		}
		return false;
    }
    
    /**
     * 
     * @Title:        getDefaultValue 
     * @Description:  对象toString
     * @param:        @param obj
     * @param:        @return    
     * @return:       String    
     * @author        shenchu
     * @Date          2017年2月22日 下午3:14:39
     */
    public static String getDefaultValue(Object obj) {
		if (obj == null)
			return "";
		return obj.toString();
	}
}
