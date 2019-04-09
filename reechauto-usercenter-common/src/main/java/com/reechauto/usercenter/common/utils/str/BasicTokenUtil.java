package com.reechauto.usercenter.common.utils.str;

import java.io.UnsupportedEncodingException;

import org.springframework.util.Base64Utils;

public class BasicTokenUtil {

	public static String authorizationBasic(String clientId,String clientSecret) throws UnsupportedEncodingException  {
		StringBuffer sb = new StringBuffer("Basic  ");
		String key = clientId+":"+clientSecret;		
		return sb.append(Base64Utils.encodeToString(key.getBytes("UTF-8"))).toString();
	}
	
	public static String authorizationBearer(String access_token) throws UnsupportedEncodingException  {
		StringBuffer sb = new StringBuffer("Bearer  ");
		return sb.append(access_token).toString();
	}
}
