package com.http;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ResponseData implements Serializable{

	private static final long serialVersionUID = 6158064016496394731L;
	private Integer code;
	private String message;
	private String cause; //异常调试信息
	
	private Object data;
	
	public ResponseData(){
		this.code = ResponseCodeEnum.OK.getCode();
		this.message = ResponseCodeEnum.OK.getMessage();
	}
	
	public ResponseData(int code,String message){
		this.code = code;
		this.message = message;
	}
	
	/**
	 * data 成功业务数据
	 * @param data
	 */
	public ResponseData(Object data){
		this.code = ResponseCodeEnum.OK.getCode();
		this.message = ResponseCodeEnum.OK.getMessage();
		this.data = data;
	}
	
	public ResponseData(int code,String message,Object data){
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public ResponseData(int code,String message,String cause){
		this.code = code;
		this.message = message;
		this.cause = cause;
	}
	
	public ResponseData(ResponseCodeEnum responseCodeEnum){
		this.code = responseCodeEnum.getCode();
		this.message = responseCodeEnum.getMessage();
	}
	
	
	public ResponseData(ResponseCodeEnum responseCodeEnum,Object data){
		this.code = responseCodeEnum.getCode();
		this.message = responseCodeEnum.getMessage();
		this.data = data;
	}
	public ResponseData(ResponseCodeEnum responseCodeEnum,String cause){
		this.code = responseCodeEnum.getCode();
		this.message = responseCodeEnum.getMessage();
		this.cause = cause;
	}
	
	public Integer getCode() {
		return code;
	}
	public ResponseData setCode(Integer code) {
		this.code = code;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public ResponseData setMessage(String message) {
		this.message = message;
		return this;
	}
	public Object getData() {
		return data;
	}
	public ResponseData setData(Object data) {
		this.data = data;
		return this;
	}

	public ResponseData setCause(String cause) {
		this.cause = cause;
		return this;
	}

	public String getCause() {
		return cause;
	}
}
