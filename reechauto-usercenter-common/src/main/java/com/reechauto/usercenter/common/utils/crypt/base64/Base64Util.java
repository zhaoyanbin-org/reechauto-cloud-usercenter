package com.reechauto.usercenter.common.utils.crypt.base64;

import java.util.Base64;

public class Base64Util {

	/**
	 * 加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBase64(String data) throws Exception {
		return Base64.getEncoder().encodeToString(data.getBytes("UTF-8"));
	}

	/**
	 * 加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBase64(byte[] data) throws Exception {
		return Base64.getEncoder().encodeToString(data);
	}

	/**
	 * 解密
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBase64(String data) throws Exception {
		return Base64.getDecoder().decode(data);
	}
	
	/**
	 * 解密
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decryptBase64ToString(String data) throws Exception {
		return new String(Base64.getDecoder().decode(data),"utf-8");
	}
	
	public static void main(String[] args) throws Exception {
		//String aa = "彦彬大哥";
		//String b = encryptBase64(aa);
		//System.out.println(b);
		String c = "YWJjMTIzNA==";
		String d = decryptBase64ToString(c);
		System.out.println(d);
		
	}

}
