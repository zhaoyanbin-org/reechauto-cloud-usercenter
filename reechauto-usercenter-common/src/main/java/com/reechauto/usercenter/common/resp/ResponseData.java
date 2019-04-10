package com.reechauto.usercenter.common.resp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResponseData implements Serializable {

	private static final long serialVersionUID = 1L;
	private final String message;
	private final int code;
	private final Map<String, Object> data = new HashMap<String, Object>();

	private ResponseData() {
		this.code = 1000;
		this.message = "OK";
	}

	private ResponseData(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public ResponseData data(Object value) {
		data.put("context", value);
		return this;
	}

	public ResponseData data(String key, Object value) {
		data.put(key, value);
		return this;
	}

	public static ResponseData ok() {
		return new ResponseData();
	}

	public static ResponseData ok(String msg) {
		return new ResponseData(1000, msg);
	}

	public static ResponseData error() {
		return new ResponseData(1001, "customer Error");
	}

	public static ResponseData error(String message) {
		return new ResponseData(1001, message);
	}

	public static ResponseData error(int code, String message) {
		return new ResponseData(code, message);
	}

	public static ResponseData notFound() {
		return new ResponseData(404, "Not Found");
	}

	public static ResponseData argumentsError() {
		return new ResponseData(400, "Parameter error");
	}

	public static ResponseData forbidden() {
		return new ResponseData(403, "Forbidden");
	}

	public static ResponseData unauthorized() {
		return new ResponseData(401, "unauthorized");
	}

	public static ResponseData serverInternalError() {
		return new ResponseData(500, "Server Internal Error");
	}

}
