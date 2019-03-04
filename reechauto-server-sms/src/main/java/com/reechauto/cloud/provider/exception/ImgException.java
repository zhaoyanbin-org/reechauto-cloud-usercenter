package com.reechauto.cloud.provider.exception;

public class ImgException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int errorCode=10001;

	public ImgException(String message) {
		super(message);
	}

	public ImgException(int errorCode, String message) {
		this(errorCode, message, true);
	}

	public ImgException(int errorCode, String message, Throwable cause) {
		this(errorCode, message, cause, true);
	}

	public ImgException(int errorCode, String message, boolean propertiesKey) {
		super(message);
		this.setErrorCode(errorCode);
	}

	public ImgException(int errorCode, String message, Throwable cause, boolean propertiesKey) {
		super(message, cause);
		this.setErrorCode(errorCode);
	}

	public ImgException(String message, Throwable cause) {
		super(message, cause);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}