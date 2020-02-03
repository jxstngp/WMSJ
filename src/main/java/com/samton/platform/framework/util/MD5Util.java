package com.samton.platform.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @Description:MD5工具类
 * @author:     Alex
 * @date:        2017年2月23日 上午10:33:53
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class MD5Util {
	
	// 默认的密码字符串组合，用来将字节转换成 16 进制表示的字符,默认组合换成了samton相关
	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	// { 's','a','m','t','o','n','e','c','f','u','0','1','2','3','5','9' };

	protected static MessageDigest messagedigest = null;
	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsaex) {
			System.err.println(MD5Util.class.getName() + "初始化失败，MessageDigest不支持MD5Util。");
			nsaex.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title:        getMD5String 
	 * @Description:  生成字符串的md5校验值
	 * @param:        @param s
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:34:08
	 */
	public static String getMD5String(String s) {
		return getMD5String(s.getBytes());
	}

	/**
	 * 
	 * @Title:        getFileMD5String 
	 * @Description:  生成文件的md5校验值
	 * @param:        @param file
	 * @param:        @return
	 * @param:        @throws IOException    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:34:37
	 */
	public static String getFileMD5String(File file) throws IOException {
		InputStream fis;
		fis = new FileInputStream(file);
		byte[] buffer = new byte[1024];
		int numRead = 0;
		while ((numRead = fis.read(buffer)) > 0) {
			messagedigest.update(buffer, 0, numRead);
		}
		fis.close();
		return bufferToHex(messagedigest.digest());
	}

	private static String getMD5String(byte[] bytes) {
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest());
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		// 取字节中高 4 位的数字转换, >>>
		// 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		// 取字节中低 4 位的数字转换
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}
}