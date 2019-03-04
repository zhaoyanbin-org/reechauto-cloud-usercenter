package com.reechauto.cloud.provider.enums;

import com.zyb.common.tools.common.IExceptionEnum;

/**
 * 返回值编码枚举
 * @author zhaoyb
 *
 */
public enum ExceptionCodeEnum implements IExceptionEnum{

	OK(1000,"OK","正常"),
	SYS_INTERNAL_SERVER_ERROR(500,"server.error","服务器内部错误"),
	SYS_UN_AUTHORIZED(401,"account.unauthorized","未权限"),
	SYS_PERMISSION_DENIED(403,"permission.denied","拒绝访问"),
	
	COMMON_PARAMETER_ERROR(21000, "parameter error","参数错误"),
	


	MOBILE_FORMAT_ERR(20001, "wrong phone number format","手机号码格式错误"),
	SEND_MESSAGE_ERR(20002, "sending message error","发送短信出错"),
	SEND_EMAIL_ERR(20003, "sending email error","发送邮件出错");


	private int code;
	private String message;
	private String desc;
	

	ExceptionCodeEnum(Integer code,String message,String desc){
		this.code = code;
		this.message = message;
		this.desc = desc;
	}
	
	public static ExceptionCodeEnum fromCode(int code){
		ExceptionCodeEnum[] values = ExceptionCodeEnum.values();
		for (ExceptionCodeEnum item : values) {
			if(item.getCode()==code){
				return item;
			}
		}
		return null;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
