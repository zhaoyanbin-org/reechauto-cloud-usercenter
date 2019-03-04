package com.reechauto.usercenter.common.exception;

/**
 * 空数据异常
 * @author zhaoyb
 *
 */
public class DataEmptyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int errorCode=10011;

	public DataEmptyException(String message) {
		super(message);
	}

	public DataEmptyException(int errorCode, String message) {
		this(errorCode, message, true);
	}

	public DataEmptyException(int errorCode, String message, Throwable cause) {
		this(errorCode, message, cause, true);
	}

	public DataEmptyException(int errorCode, String message, boolean propertiesKey) {
		super(message);
		this.setErrorCode(errorCode);
	}

	public DataEmptyException(int errorCode, String message, Throwable cause, boolean propertiesKey) {
		super(message, cause);
		this.setErrorCode(errorCode);
	}

	public DataEmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}