package com.reechauto.usercenter.common.exception;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	private int errorCode=10001;

	public ValidateCodeException(String message) {
		super(message);
	}

	public ValidateCodeException(int errorCode, String message) {
		this(errorCode, message, true);
	}

	public ValidateCodeException(int errorCode, String message, Throwable cause) {
		this(errorCode, message, cause, true);
	}

	public ValidateCodeException(int errorCode, String message, boolean propertiesKey) {
		super(message);
		this.setErrorCode(errorCode);
	}

	public ValidateCodeException(int errorCode, String message, Throwable cause, boolean propertiesKey) {
		super(message, cause);
		this.setErrorCode(errorCode);
	}

	public ValidateCodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}