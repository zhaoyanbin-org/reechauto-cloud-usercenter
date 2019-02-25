package com.reechauto.usercenter.common.exception;

/**
 * 
 * @author zhaoyb
 *
 */
public class EnumsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int errorCode=10001;

	public EnumsException(String message) {
		super(message);
	}

	public EnumsException(int errorCode, String message) {
		this(errorCode, message, true);
	}

	public EnumsException(int errorCode, String message, Throwable cause) {
		this(errorCode, message, cause, true);
	}

	public EnumsException(int errorCode, String message, boolean propertiesKey) {
		super(message);
		this.setErrorCode(errorCode);
	}

	public EnumsException(int errorCode, String message, Throwable cause, boolean propertiesKey) {
		super(message, cause);
		this.setErrorCode(errorCode);
	}

	public EnumsException(String message, Throwable cause) {
		super(message, cause);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}