package com.reechauto.cloud.provider.service.message;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.hamcrest.CoreMatchers.nullValue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.bouncycastle.crypto.agreement.ECDHBasicAgreement;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.visitor.functions.If;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.reechauto.cloud.provider.bean.request.ReechAutoConstants;
import com.reechauto.cloud.provider.enums.SmsTypeEnum;
import com.reechauto.cloud.provider.exception.UnauthorizedException;
import com.reechauto.cloud.provider.service.common.sms.AliSmsClientUtil;
import com.reechauto.cloud.provider.service.common.sms.SmsClientUtil;
import com.reechauto.cloud.provider.service.message.SmsRecordService;
import com.reechauto.usercenter.common.exception.EnumsException;
import com.zyb.common.tools.common.ResponseData;

import org.springframework.data.redis.connection.RedisConnection;

@Service
public class SmsService {
	// 暂时新起线程发送，后边重构时入到队列中

	private static Object o = new Object();

	@Value("${aliyun.oss.accessKeyId}")
	private String accessKeyId;
	@Value("${aliyun.oss.accessKeySecret}")
	private String accessKeySecret;
	@Value("${sms.serviceProvider}")
	private String serviceProvider;
	@Qualifier("stringRedisTemplate")
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private ExecutorService executorService;
	@Autowired
	private SmsRecordService recordService;

	@Autowired
	private LettuceConnectionFactory lettuceConnectionFactory;

	public synchronized void send(String receiveMobile, String templateId, String signName, String templateParamJson,
			String outId, String clientId, String smsType) throws Exception {

		String prefix = ReechAutoConstants.REG_SEND_SMS_KEY + clientId + ":" + receiveMobile + ":" + smsType;
		String key = prefix + ":" + System.currentTimeMillis() + (int) Math.random() * 100000000;
		Set<String> keys = stringRedisTemplate.keys(prefix + "*");
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				try {
					Class<?> c1 = null;
					SmsClientUtil util = null;
					c1 = Class.forName(
							"com.reechauto.cloud.provider.service.common.sms." + serviceProvider + "SmsClientUtil");
					Constructor<?> constructor = c1.getConstructor(String.class, String.class);
					util = (SmsClientUtil) constructor.newInstance(accessKeyId, accessKeySecret);
					util.sendSms(receiveMobile, templateId, signName, templateParamJson, null);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		);
		stringRedisTemplate.opsForValue().set(key, "1", 60 * 5, TimeUnit.SECONDS);

	}

	public synchronized void sendMessage(String clientId, String receiveMobile, String templateId, String signName,
			String smsType, String isVoice, String sendParam) throws Exception {
		try {
			// 判断发送短信的类型有没有错，如果有错，直接抛异常
			String aString = SmsTypeEnum.get(smsType).getValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new EnumsException("短信发送类型错误");
		}
		Set<String> keys = null;

		try {
			lettuceConnectionFactory.getConnection().openPipeline();
			String prefix = ReechAutoConstants.REG_SEND_SMS_KEY + clientId + ":" + receiveMobile + ":" + smsType;
			keys = stringRedisTemplate.keys(prefix + "*");

		} catch (Exception e) {
			recordService.addRecordByException(clientId, receiveMobile, templateId, signName, smsType, isVoice,
					sendParam, "未发送redis出现异常", e.getMessage());
			// send(receiveMobile, templateId, signName, sendParam, null, clientId,
			// smsType);
			// TODO Auto-generated catch block
			return;
		}
		if (keys == null || keys.size() < 2) {
			recordService.addRecord(clientId, receiveMobile, templateId, signName, smsType, isVoice, sendParam,
					"已发送" + keys.size());
			send(receiveMobile, templateId, signName, sendParam, null, clientId, smsType);
		} else {
			recordService.addRecord(clientId, receiveMobile, templateId, signName, smsType, isVoice, sendParam,
					"未发送" + keys.size());
			System.out.println("短信发送量过大，禁止发送");
			throw new UnauthorizedException("短信发送量过大，禁止发送");
		}

	}

	public void cancelFuse(String clientId, String receiveMobile, String smsType) throws Exception {

		try {
			// 判断发送短信的类型有没有错，如果有错，直接抛异常
			String aString = SmsTypeEnum.get(smsType).getValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new EnumsException("短信发送类型错误");
		}

		Set<String> keys = stringRedisTemplate
				.keys(ReechAutoConstants.REG_SEND_SMS_KEY + clientId + ":" + receiveMobile + ":" + smsType + "*");
		for (String key : keys) {
			stringRedisTemplate.delete(key);
		}

	}

	/*
	 * public static void main(String[] args) throws Exception { AliSmsClientUtil
	 * util = new
	 * AliSmsClientUtil("LTAI4neWcL5za1ri","JWgKRm5uoGSeUnFYtXTGxaXPz1lxFL");
	 * util.sendSms("15966087152", "SMS_149375713", "绿驰汽车",
	 * "{\"code\":\"1111111\"}", null); }
	 */

}
