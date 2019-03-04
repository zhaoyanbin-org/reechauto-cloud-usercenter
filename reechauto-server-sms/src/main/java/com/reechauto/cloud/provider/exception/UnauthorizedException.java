package com.reechauto.cloud.provider.exception;

/**
 * 
 * @author zhaoyb
 *
 */
public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int errorCode=401;

	public UnauthorizedException(String message) {
		super(message);
	}

	public UnauthorizedException(int errorCode, String message) {
		this(errorCode, message, true);
	}

	public UnauthorizedException(int errorCode, String message, Throwable cause) {
		this(errorCode, message, cause, true);
	}

	public UnauthorizedException(int errorCode, String message, boolean propertiesKey) {
		super(message);
		this.setErrorCode(errorCode);
	}

	public UnauthorizedException(int errorCode, String message, Throwable cause, boolean propertiesKey) {
		super(message, cause);
		this.setErrorCode(errorCode);
	}

	public UnauthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}