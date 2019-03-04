package com.reechauto.usercenter.user.bean;

public interface ReechAuthConstant {
	
	/*
	 * 验证码保存在redis中的key
	 */
	public static final String VALIDATE_CODE_SAVE_PATH = "validate:code:deviceid:";
	
	/*
	 * 手机验证码:设备ID和验证码ID
	 */
	public static final String PARAMETER_NAME_ON_VALIDATE_DEVICEID_MOBILE = "mobile";
	public static final String PARAMETER_NAME_ON_VALIDATE_VCODE_MOBILE = "vcode";

	
	/*
	 * 图片验证码
	 */
	public static final int VALIDATE_IMAGE_WIDTH=75;
	public static final int VALIDATE_IMAGE_HEIGHT=25;
	public static final String PARAMETER_NAME_ON_VALIDATE_DEVICEID_IMAGE="deviceid";
	public static final String PARAMETER_NAME_ON_VALIDATE_VCODE_IMAGE="vcode";
	
	/*
	 * 手机注册
	 */
	public static final String MOBILE_REGISTER_URL = "/user/register/mobile";

}
