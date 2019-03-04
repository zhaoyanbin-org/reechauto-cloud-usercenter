package com.reechauto.usercenter.auth.entity.constant;

public interface ReechAuthConstant {
	/*
	 * 手机登录请求
	 */
	public static final String REECHLOGIN_MOBILE_URL = "/mobile/token";
	/*
	 * 登录页页面
	 */
	public static final String REECHLOGIN_LOGINPAGE_URL = "/authentication/require";
	/*
	 * 登录请求页
	 */
	public static final String REECHLOGIN_LOGINPROCESSING_URL = "/authentication/form";

	/*
	 * 验证码保存在redis中的key
	 */
	public static final String VALIDATE_CODE_SAVE_PATH = "validate:code:deviceid:";

	/*
	 * 手机验证码:设备ID和验证码ID
	 */
	public static final String PARAMETER_NAME_ON_VALIDATE_DEVICEID_MOBILE = "mobile";
	public static final String PARAMETER_NAME_ON_VALIDATE_VCODE_MOBILE = "vcode";
	public static final String VALIDATE_URL_MOBILE = "/mobile/token";
	


}
