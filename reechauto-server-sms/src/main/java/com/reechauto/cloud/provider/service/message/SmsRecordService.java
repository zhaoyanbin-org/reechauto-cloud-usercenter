package com.reechauto.cloud.provider.service.message;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.reechauto.cloud.provider.service.common.sms.AliSmsClientUtil;
import com.reechauto.cloud.provider.mapper.SmsRecordMapper;
import com.reechauto.cloud.provider.mapper.SmsTemplateMapper;
import com.reechauto.cloud.provider.model.SmsRecord;
import com.reechauto.cloud.provider.model.SmsTemplate;
import com.reechauto.cloud.provider.model.SmsTemplateExample;

@Service
public class SmsRecordService {

	@Autowired
	private SmsRecordMapper smsRecordMapper;

	@Autowired
	private SmsTemplateMapper smsTemplateMapper;
	
	
	//private ExecutorService executorService  = Executors.newFixedThreadPool(100);
	
	private ExecutorService executorService  = new ThreadPoolExecutor(10, 10000, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1000));
	
	@Value("${sms.serviceProvider}")
	private String serviceProvider;

	public void addRecord(String clientId, String receiveMobile, String templateId, String signName,
			String smsType, String isVoice, String sendParam,String status) {

		// 根据模板号从数据库中查找模板
		
		SmsTemplate smsTemplate = smsTemplateMapper.selectByPrimaryKey(templateId);

		try {
			executorService.execute(new Runnable() {

				@Override
				public void run() {

					try {
						// 发送记录保存到数据库
						SmsRecord smsRecord = new SmsRecord();
						smsRecord.setReceiveMobile(receiveMobile);
						smsRecord.setClientId(clientId);
						smsRecord.setSmsType(smsType);
						smsRecord.setTemplateId(templateId);
						smsRecord.setSignName(signName);
						smsRecord.setIsVoice(isVoice);
						smsRecord.setSendParam(sendParam);
						smsRecord.setSendContext(smsTemplate.getContent());
						smsRecord.setServiceProvider(serviceProvider);
						smsRecord.setStatus(status);
						smsRecordMapper.insertSelective(smsRecord);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			});
		} catch (Exception ex) {
		}

	}
	
	
	
	
	public void addRecordByException(String clientId, String receiveMobile, String templateId, String signName,
			String smsType, String isVoice, String sendParam,String status,String exceptionMessage) {

		// 根据模板号从数据库中查找模板
		
		SmsTemplate smsTemplate = smsTemplateMapper.selectByPrimaryKey(templateId);

		try {
			executorService.execute(new Runnable() {

				@Override
				public void run() {

					try {
						// 发送记录保存到数据库
						SmsRecord smsRecord = new SmsRecord();
						smsRecord.setReceiveMobile(receiveMobile);
						smsRecord.setClientId(clientId);
						smsRecord.setSmsType(smsType);
						smsRecord.setTemplateId(templateId);
						smsRecord.setSignName(signName);
						smsRecord.setIsVoice(isVoice);
						smsRecord.setSendParam(sendParam);
						smsRecord.setSendContext(smsTemplate.getContent());
						smsRecord.setServiceProvider(serviceProvider);
						smsRecord.setStatus(status);
						smsRecord.setGatewaySendRet(exceptionMessage);
						smsRecordMapper.insertSelective(smsRecord);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			});
		} catch (Exception ex) {
		}

	}

}
