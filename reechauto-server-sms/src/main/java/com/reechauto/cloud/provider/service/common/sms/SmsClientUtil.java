package com.reechauto.cloud.provider.service.common.sms;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

public interface SmsClientUtil {
	
	
	public SendSmsResponse sendSms(String receiveMobile, String templateCode, String signName, String templateParamJson,
			String outId) throws ClientException;

}
