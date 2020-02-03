package com.samton.platform.pm.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @Description:注册工具类
 * @author: shenchu
 * @date: 2017年2月22日 下午9:33:12 Copyright (c) 2017, Samton. All rights reserved
 */
public class RegisterUtil {

	/**
	 * 
	 * @Title:        isEmail 
	 * @Description:  校验邮箱格式
	 * @param:        @param email
	 * @param:        @return    
	 * @return:       boolean    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午9:33:46
	 */
	public static boolean isEmail(String email) {
		String strPattern = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * 
	 * @Title:        isMobileNo 
	 * @Description:  校验手机格式
	 * @param:        @param mobileNo
	 * @param:        @return    
	 * @return:       boolean    
	 * @author        shenchu
	 * @Date          2017年2月22日 下午9:34:02
	 */
	public static boolean isMobileNo(String mobileNo) {
		String strPattern = "^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(mobileNo);
		return m.matches();
	}

	/**
	 * @Title: randomPassword
	 * @Description: 生成指定长度密码
	 * @param: @param length
	 * @param: @return
	 * @return: String
	 * @author lijc
	 * @Date 2017年1月13日 下午4:15:26
	 */
	public static String randomPassword(int length) {
		StringBuffer str = new StringBuffer();
		str.append("a,b,c,d,e,f,g,h,i,g,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z");
		str.append(",A,B,C,D,E,F,G,H,I,G,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z");
		str.append(",0,1,2,3,4,5,6,7,8,9");
		String[] arr = str.toString().split(",");

		Random random = new Random();
		StringBuffer strbuf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int randomNum = random.nextInt();
			strbuf.append(String.valueOf(arr[Math.abs(randomNum % (arr.length - 1))]));
		}
		return strbuf.toString();
	}
}