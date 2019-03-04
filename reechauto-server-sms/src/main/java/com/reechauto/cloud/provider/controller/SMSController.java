package com.reechauto.cloud.provider.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reechauto.cloud.provider.bean.request.ReechAutoConstants;
import com.reechauto.cloud.provider.enums.ExceptionCodeEnum;
import com.reechauto.cloud.provider.service.message.SmsService;
import com.reechauto.cloud.provider.service.message.SmsTemplateService;
import com.reechauto.usercenter.common.exception.DataEmptyException;
import com.reechauto.usercenter.common.exception.EnumsException;
import com.zyb.common.tools.common.ResponseData;
import com.reechauto.cloud.provider.exception.UnauthorizedException;
import com.reechauto.cloud.provider.model.SmsTemplate;
import com.reechauto.cloud.provider.service.message.SmsRecordService;


import net.bytebuddy.asm.Advice;

@RestController
@RequestMapping("sms")
public class SMSController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SmsService smsService;
	@Autowired
	private SmsRecordService recordService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private SmsTemplateService templateService;

	
	@PostMapping("sendSmsMeg")
	public ResponseData sendSms(@RequestParam("clientId") String clientId, @RequestParam("smsType") String smsType,
			@RequestParam("receiveMobile") String receiveMobile, @RequestParam("templateId") String templateId,
			@RequestParam("signName") String signName, @RequestParam("isVoice") String isVoice,
			@RequestBody String sendParam) {
		try {

			smsService.sendMessage(clientId, receiveMobile, templateId, signName, smsType, isVoice, sendParam);

			return new ResponseData(ExceptionCodeEnum.OK);
		}catch (UnauthorizedException ex) {
			return new ResponseData(ExceptionCodeEnum.SYS_UN_AUTHORIZED, ex.getLocalizedMessage());
		} catch (EnumsException ex) {
			return new ResponseData(ExceptionCodeEnum.COMMON_PARAMETER_ERROR, ex.getLocalizedMessage());
		}catch (Exception ex) {
			return new ResponseData(ExceptionCodeEnum.SEND_MESSAGE_ERR, ex.getLocalizedMessage());
		}

	}

	@PostMapping("cancelFuse")
	public ResponseData cancelFuse(@RequestParam("clientId") String clientId, @RequestParam("smsType") String smsType,
			@RequestParam("receiveMobile") String receiveMobile) {
		try {

			smsService.cancelFuse(clientId, receiveMobile, smsType);

			return new ResponseData(ExceptionCodeEnum.OK);
		} catch (EnumsException ex) {
			return new ResponseData(ExceptionCodeEnum.COMMON_PARAMETER_ERROR, ex.getLocalizedMessage());
		}catch (Exception ex) {
			return new ResponseData(ExceptionCodeEnum.COMMON_PARAMETER_ERROR, ex.getLocalizedMessage());
		}

	}

	
	@PostMapping("addTemplate")
	public ResponseData addTemplate(@RequestParam("templateId") String templateId, @RequestParam("templateName") String templateName,
			@RequestParam("content") String content, @RequestParam("clientId") Integer clientId) {
		try {
 
			templateService.addTemplate(templateId, templateName, content, clientId);
			return new ResponseData(ExceptionCodeEnum.OK);
		} catch (Exception ex) {
			return new ResponseData(ExceptionCodeEnum.COMMON_PARAMETER_ERROR, ex.getLocalizedMessage());
		}
		
	}
	
	@PostMapping("removeTemplate")
	public ResponseData removeTemplate(@RequestParam("templateId") String templateId) {
		try {
 
			templateService.removeTemplate(templateId);
			return new ResponseData(ExceptionCodeEnum.OK);
		} catch (Exception ex) {
			return new ResponseData(ExceptionCodeEnum.COMMON_PARAMETER_ERROR, ex.getLocalizedMessage());
		}
	}
	
	@PostMapping("modifyTemplate")
	public ResponseData modifyTemplate(@RequestBody SmsTemplate smsTemplateInfo) {
		try {
 
			return templateService.modifyTemplate(smsTemplateInfo);
		} catch (Exception ex) {
			return new ResponseData(ExceptionCodeEnum.COMMON_PARAMETER_ERROR, ex.getLocalizedMessage());
		}
	}
	
	@PostMapping("selectTemplate")
	public ResponseData selectTemplate(@RequestBody SmsTemplate smsTemplateInfo) {
		try {
 
			List<SmsTemplate> list = templateService.selectTemplate(smsTemplateInfo);
			return new ResponseData(list);
		}catch (DataEmptyException ex) {
			// TODO: handle exception
			return new ResponseData(ExceptionCodeEnum.COMMON_PARAMETER_ERROR, ex.getLocalizedMessage());
		} catch (Exception ex) {
			return new ResponseData(ExceptionCodeEnum.COMMON_PARAMETER_ERROR, ex.getLocalizedMessage());
		}
	}

}
