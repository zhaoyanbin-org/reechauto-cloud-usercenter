package com.reechauto.usercenter.common.utils.str;

public class StrUtil {
	/**
	 * 首字母小写
	 * @param str
	 * @return
	 */
	public String lowerFirst(String str) {
		char[] chars = str.toCharArray();
		chars[0] += 32;
		return String.valueOf(chars);
	}
}
