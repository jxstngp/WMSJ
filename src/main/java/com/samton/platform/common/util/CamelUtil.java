package com.samton.platform.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:参数格式转换工具类
 * @author:     Joshua_Cheng@qq.com
 * @date:        2015年7月15日 上午10:18:34
 * Copyright (c) 2015, Samton. All rights reserved
 */
public class CamelUtil {
	  public static final char UNDERLINE='_';  
	  
	  /**
	   * @Title:        camelToUnderline 
	   * @Description:  驼峰转下划线 
	   * @param:        @param param
	   * @param:        @return    
	   * @return:       String    
	   * @throws 
	   * @author        Joshua_Cheng@qq.com
	   * @Date          2015年7月15日 上午10:19:20
	   */
	   public static String camelToUnderline(String param){  
	       if (param==null||"".equals(param.trim())){  
	           return "";  
	       }  
	       int len=param.length();  
	       StringBuilder sb=new StringBuilder(len);  
	       for (int i = 0; i < len; i++) {  
	           char c=param.charAt(i);  
	           if (Character.isUpperCase(c)){  
	               sb.append(UNDERLINE);  
	               sb.append(Character.toLowerCase(c));  
	           }else{  
	               sb.append(c);  
	           }  
	       }  
	       return sb.toString();  
	   }  
	   
	   /**
	    * @Title:        underlineToCamel 
	    * @Description:  下划线转驼峰append实现
	    * @param:        @param param
	    * @param:        @return    
	    * @return:       String    
	    * @throws 
	    * @author        Joshua_Cheng@qq.com
	    * @Date          2015年7月15日 上午10:19:49
	    */
	   public static String underlineToCamel(String param){  
	       if (param==null||"".equals(param.trim())){  
	           return "";  
	       }  
	       int len=param.length();  
	       StringBuilder sb=new StringBuilder(len);  
	       for (int i = 0; i < len; i++) {  
	           char c=param.charAt(i);  
	           if (c==UNDERLINE){  
	              if (++i<len){  
	                  sb.append(Character.toUpperCase(param.charAt(i)));  
	              }  
	           }else{  
	               sb.append(c);  
	           }  
	       }  
	       return sb.toString();  
	   }  
	   /**
	    * @Title:        underlineToCamel2 
	    * @Description:  下划线转驼峰replace实现 
	    * @param:        @param param
	    * @param:        @return    
	    * @return:       String    
	    * @throws 
	    * @author        Joshua_Cheng@qq.com
	    * @Date          2015年7月15日 上午10:20:19
	    */
	   public static String underlineToCamel2(String param){  
	       if (param==null||"".equals(param.trim())){  
	           return "";  
	       }  
	       StringBuilder sb=new StringBuilder(param);  
	       Matcher mc= Pattern.compile("_").matcher(param);  
	       int i=0;  
	       while (mc.find()){  
	           int position=mc.end()-(i++);  
	           sb.replace(position-1,position+1,sb.substring(position,position+1).toUpperCase());  
	       }  
	       return sb.toString();  
	   }  
	   
	   
}