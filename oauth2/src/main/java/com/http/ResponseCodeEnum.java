package com.http;

/**
 * 返回值编码枚举
 * @author zhutw
 *
 */
public enum ResponseCodeEnum {

	OK(0,"OK"),
	INTERNAL_SERVER_ERROR(500,"server.error"),
	UN_AUTHORIZED(401,"account.unauthorized"),
	PERMISSION_DENIED(403,"permission.denied"),
	
	TOKEN_INVALID(1000,"accessToken.invalid"),
	INVALID_PARAMETERS(1001,"invalid.parameters"),
	USERNAME_PASSWORD_ERROR(1002,"username or password error"),//企业登录接口用户名或者密码错误
	RESET_PASSWORD_FAILED(1003,"reset password failed"),//重置密码失败
	DIRECT_SIGNUP_FAILED(1004,"directSignup.failed"),//后台注册失败
	EXCEED_MAXMIUM_TIMES_ONE_DAY(1005, "exceed the maxmium times one day"),
	UNAVAILABLE_IDENTITY(1006, "identity is not available"),//用户名已经注册
	
	INVALID_CAPTCHA(1007,"invalid.captcha"),
	INVALID_ADMINUSER(1008,"invalid.adminuser"),
	ACCOUNT_EXIST(1009, "account is exist"),//用户已存在
	APPNAME_EVALUATIONORDER_EXIST(1010,"there are same priority appnames"),//已经存在相同优先级的appname

	TEMPLATE_EXIST(1011, "template is exist"), //模板已存在
	TEMPLATE_NOT_EXIST(1012, "template is exist"), //模板不存在
	APPKEY_NOT_EXIST(1013, "appKey is not exist"),//appKey不存在
	MOBLE_CAN_NOT_BE_EMPTY(1014, "the cell phone number can not be empty"),//手机号不可以空
	TIME_SEQUENCE_ERROR(1015,"time sequence error"),//时间顺序错误
	STATISTICAL_CYCLE_ERROR(1016,"The statistical cycle is up to 30 days"),//统计周期最长30天
	NO_GREATER_THAN_TODAY(1017,"The date of statistics can't be greater than today");

	private Integer code;
	private String message;

	ResponseCodeEnum(Integer code,String message){
		this.code = code;
		this.message = message;
	}
	
	public static ResponseCodeEnum fromCode(Integer code){
		ResponseCodeEnum[] values = ResponseCodeEnum.values();
		for (ResponseCodeEnum item : values) {
			if(item.getCode().equals(code)){
				return item;
			}
		}
		return null;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
