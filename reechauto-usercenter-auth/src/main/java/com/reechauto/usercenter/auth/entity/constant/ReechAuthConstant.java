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
	
	/*
	 * 图片验证码
	 */
	public static final int VALIDATE_IMAGE_WIDTH=75;
	public static final int VALIDATE_IMAGE_HEIGHT=25;
	public static final String PARAMETER_NAME_ON_VALIDATE_DEVICEID_IMAGE="deviceid";
	public static final String PARAMETER_NAME_ON_VALIDATE_VCODE_IMAGE="vcode";
	public static final String VALIDATE_URL_IMAGE = "/oauth/image";

	public static final String DEDAULT_IMAGE_CODE = "/code/image";
	public static final String USER_ROLE_SAVE_PATH = "ReechUser:role:";
	public static final String USER_MENU_SAVE_PATH = "ReechUser:menu:";

}
