package com.reechauto.usercenter.common.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.reechauto.usercenter.common.resp.ResponseData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public final class ExceptionAdviceHandler {

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseData unKnowExceptionHandler(HttpServletRequest req, HttpServletResponse resp, Exception ex) {
		log.error(ex.getMessage());
		return ResponseData.error(500, ex.getMessage());
	}

	@ExceptionHandler(value = RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseData runtimeExceptionHandler(HttpServletRequest req, HttpServletResponse resp, RuntimeException ex) {
		log.error(ex.getMessage());
		return ResponseData.error(500, ex.getMessage());
	}

	/**
	 * 空指针异常
	 */
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseData nullPointerExceptionHandler(HttpServletRequest req, HttpServletResponse resp,
			NullPointerException ex) {
		log.error(ex.getMessage());
		return ResponseData.error(500, "空指针异常：" + ex.getMessage());
	}

	/**
	 * 类型转换异常
	 */
	@ExceptionHandler(ClassCastException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseData classCastExceptionHandler(HttpServletRequest req, HttpServletResponse resp,
			ClassCastException ex) {
		log.error(ex.getMessage());
		return ResponseData.error(500, "类型转换异常" + ex.getMessage());
	}

	/**
	 * IO异常
	 */
	@ExceptionHandler(IOException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseData iOExceptionHandler(HttpServletRequest req, HttpServletResponse resp, IOException ex) {
		log.error(ex.getMessage());
		return ResponseData.error(500, "IO异常" + ex.getMessage());
	}

	/**
	 * 未知方法异常
	 */
	@ExceptionHandler(NoSuchMethodException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseData noSuchMethodExceptionHandler(HttpServletRequest req, HttpServletResponse resp,
			NoSuchMethodException ex) {
		log.error(ex.getMessage());
		return ResponseData.error(500, ex.getMessage());
	}

	/**
	 * 数组越界异常
	 */
	@ExceptionHandler(IndexOutOfBoundsException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseData indexOutOfBoundsExceptionHandler(HttpServletRequest req, HttpServletResponse resp,
			IndexOutOfBoundsException ex) {
		log.error(ex.getMessage());
		return ResponseData.error(500, "越界异常" + ex.getMessage());
	}

	/**
	 * 500错误
	 */
	@ExceptionHandler({ ConversionNotSupportedException.class, HttpMessageNotWritableException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseData server500(HttpServletRequest req, HttpServletResponse resp, Exception ex) {
		log.error(ex.getMessage());
		return ResponseData.error(500, ex.getMessage());
	}

	@ExceptionHandler(value = ServerErrorException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseData serverErrorExceptionHandler(HttpServletRequest req, HttpServletResponse resp,
			ServerErrorException ex) {
		log.error(ex.getMessage());
		return ResponseData.error(500, ex.getMessage());
	}

	/**
	 * 405错误
	 */
	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public ResponseData request405(HttpServletRequest req, HttpServletResponse resp,
			HttpRequestMethodNotSupportedException ex) {
		log.error(ex.getMessage());
		return ResponseData.error(405, "请求方法不正确");
	}

	/**
	 * 406错误
	 */
	@ExceptionHandler({ HttpMediaTypeNotAcceptableException.class })
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public ResponseData request406(HttpServletRequest req, HttpServletResponse resp,
			HttpMediaTypeNotAcceptableException ex) {
		log.error(ex.getMessage());
		return ResponseData.error(406, "不接受该请求");

	}

	@ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	public ResponseData httpMediaTypeNotSupportedExceptionHandler(HttpServletRequest req, HttpServletResponse resp,
			HttpMediaTypeNotSupportedException ex) {
		log.error(ex.getMessage());
		return ResponseData.error(415, "服务器无法处理请求附带的媒体格式");
	}

	/**
	 * 404
	 */
	@ExceptionHandler(value = NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseData notFoundException(HttpServletRequest req, HttpServletResponse resp,
			NoHandlerFoundException ex) {
		log.error(ex.getMessage());
		return ResponseData.error(404, "找不到服务");
	}



	/**
	 * 400错误
	 */
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseData requestNotReadable(HttpServletRequest req, HttpServletResponse resp,
			HttpMessageNotReadableException ex) {
		log.error(ex.getMessage());
		return ResponseData.error(400, "错误的请求:" + ex.getMessage());
	}

	/**
	 * 400错误 类型不匹配
	 */
	@ExceptionHandler({ TypeMismatchException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseData requestTypeMismatch(HttpServletRequest req, HttpServletResponse resp,
			TypeMismatchException ex) {
		log.error(ex.getMessage());
		return ResponseData.error(400, ex.getMessage());
	}

	/**
	 * 400错误 缺少参数
	 */
	@ExceptionHandler({ MissingServletRequestParameterException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseData requestMissingServletRequest(HttpServletRequest req, HttpServletResponse resp,
			MissingServletRequestParameterException ex) {
		log.error(ex.getMessage());
		return ResponseData.error(400, "参数错误:" + ex.getMessage());
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseData methodArgumentNotValidExceptionHandler(HttpServletRequest req, HttpServletResponse resp,
			MethodArgumentNotValidException ex) {
		log.error(ex.getMessage());
		return ResponseData.error(400, "参数错误").data(ex.getBindingResult().getAllErrors());
	}

	@ExceptionHandler(value = BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseData Bind(HttpServletRequest req, HttpServletResponse resp, BindException ex) {
		log.error(ex.getMessage());
		return ResponseData.argumentsError().data(ex.getAllErrors());
	}


}
