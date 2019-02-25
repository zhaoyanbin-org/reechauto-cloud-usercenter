package com.reechauto.usercenter.common.exception;

public class ReechSignException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int errorCode=10002;

	public ReechSignException(String message) {
		super(message);
	}

	public ReechSignException(int errorCode, String message) {
		this(errorCode, message, true);
	}

	public ReechSignException(int errorCode, String message, Throwable cause) {
		this(errorCode, message, cause, true);
	}

	public ReechSignException(int errorCode, String message, boolean propertiesKey) {
		super(message);
		this.setErrorCode(errorCode);
	}

	public ReechSignException(int errorCode, String message, Throwable cause, boolean propertiesKey) {
		super(message, cause);
		this.setErrorCode(errorCode);
	}

	public ReechSignException(String message, Throwable cause) {
		super(message, cause);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}