package com.reechauto.usercenter.common.utils.crypt.rsa;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import com.reechauto.usercenter.common.utils.crypt.base64.Base64Util;

public class RsaUtil {
	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "SHA256WithRSA";// "MD5withRSA";

	/**
	 * 通过jdk初始化公钥 私钥 (一般都是由openssl等工具生成)
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> initJdkKey() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(2048);

		KeyPair keyPair = keyPairGen.generateKeyPair();
		// 公钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		// 私钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);

		keyMap.put("PubKey", publicKey);
		keyMap.put("PriKey", privateKey);
		return keyMap;
	}

	/**
	 * 用私钥对信息生成数字签名
	 * 
	 * @param data
	 *            加密数据
	 * @param privateKey
	 *            私钥
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {
		byte[] keyBytes = Base64Util.decryptBase64(privateKey);
		// 构造PKCS8EncodedKeySpec对象
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		// KEY_ALGORITHM 指定的加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		// 取私钥对象
		PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 用私钥对信息生成数字签名
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(priKey);
		signature.update(data);
		return Base64Util.encryptBase64(signature.sign());
	}

	/**
	 * 校验数字签名
	 * 
	 * @param data
	 *            加密的数据
	 * @param publicKey
	 *            公钥
	 * @param sign数字签名
	 * @return
	 * @throws Exception
	 */
	public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
		byte[] keyBytes = Base64Util.decryptBase64(publicKey);
		// 构建x509EncodedKeySpec
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		// KEY_ALGORITHM指定的加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		// 取出公钥
		PublicKey pubKey = keyFactory.generatePublic(keySpec);

		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(pubKey);
		signature.update(data);
		return signature.verify(Base64Util.decryptBase64(sign));
	}

	/**
	 * 解密（用私钥解密）
	 * 
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] decrypByPrivateKey(byte[] data, String key) throws Exception {
		// 对密钥解密
		byte[] keyBytes = Base64Util.decryptBase64(key);
		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		return cipher.doFinal(data);
	}

	/**
	 * 解密(用公钥解密)
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data, String key) throws Exception {
		// 对密钥解密
		byte[] keyBytes = Base64Util.decryptBase64(key);
		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);

		return cipher.doFinal(data);
	}

	/**
	 * 加密<br>
	 * 用公钥加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {
		// 对公钥解密
		byte[] keyBytes = Base64Util.decryptBase64(key);

		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		return cipher.doFinal(data);
	}

	/**
	 * 加密<br>
	 * 用私钥加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String key) throws Exception {
		// 对密钥解密
		byte[] keyBytes = Base64Util.decryptBase64(key);

		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);

		return cipher.doFinal(data);
	}

	/**
	 * 私钥分段解密
	 * @param data
	 * @param priKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKeySubsection(byte[] data, String priKey) throws Exception {
		// 对密钥解密
		byte[] keyBytes = Base64Util.decryptBase64(priKey);
		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;

		for (int i = 0; inputLen - offSet > 0; offSet = i * 256) {
			byte[] cache;
			if (inputLen - offSet > 256) {
				cache = cipher.doFinal(data, offSet, 256);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}

			out.write(cache, 0, cache.length);
			++i;
		}

		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * 公钥分段解密
	 * @param data
	 * @param pubKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKeySubsection(byte[] data, String pubKey) throws Exception {
		// 对密钥解密
		byte[] keyBytes = Base64Util.decryptBase64(pubKey);
		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);

		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;

		for (int i = 0; inputLen - offSet > 0; offSet = i * 256) {
			byte[] cache;
			if (inputLen - offSet > 256) {
				cache = cipher.doFinal(data, offSet, 256);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}

			out.write(cache, 0, cache.length);
			++i;
		}

		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * 公钥分段加密
	 * @param data
	 * @param pubKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKeySubsection(byte[] data, String pubKey) throws Exception {
		// 对公钥解密
		byte[] keyBytes = Base64Util.decryptBase64(pubKey);
		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);

		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;

		for (int i = 0; inputLen - offSet > 0; offSet = i * 244) {
			byte[] cache;
			if (inputLen - offSet > 244) {
				cache = cipher.doFinal(data, offSet, 244);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}

			out.write(cache, 0, cache.length);
			++i;
		}

		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * 私钥分段加密
	 * @param data
	 * @param priKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKeySubsection(byte[] data, String priKey) throws Exception {
		byte[] keyBytes = Base64Util.decryptBase64(priKey);

		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);

		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;

		for (int i = 0; inputLen - offSet > 0; offSet = i * 244) {
			byte[] cache;
			if (inputLen - offSet > 244) {
				cache = cipher.doFinal(data, offSet, 244);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}

			out.write(cache, 0, cache.length);
			++i;
		}

		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	public static void main(String[] args) throws Exception {
		String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAodzakV/hMRXnKsjf3i/SeoGYR02haiKOJfdcJ0xMU3BzVViG2ZSrwVqgovOgkCOw9tTTqf+3UyqZe+1+PZJxEq6hnOLCgdKdLTkdkw43P4W6lwZ2bV08q0KjE+hlFDTQCynNHFOnWm2VNrsAvrTMXegxLnbSmIixLFMm1z1FzQJmycFo9Q/BvL6v5msnC0Cd29I/nHhm2QK/Imnk9+t9QNeQFjqLp/p8aNDqvk0zQMp0He0mvZ4od0W2jKx/BQ+HgG2Rha4F3NLxaXronBJe3200pgdht1z7wJJE8NBimVzvvo/s/ai2sa1y93VmvAWM4Z6ORlkI1ucq3Cwcb6Bd9QIDAQAB";
		String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCh3NqRX+ExFecqyN/eL9J6gZhHTaFqIo4l91wnTExTcHNVWIbZlKvBWqCi86CQI7D21NOp/7dTKpl77X49knESrqGc4sKB0p0tOR2TDjc/hbqXBnZtXTyrQqMT6GUUNNALKc0cU6dabZU2uwC+tMxd6DEudtKYiLEsUybXPUXNAmbJwWj1D8G8vq/maycLQJ3b0j+ceGbZAr8iaeT3631A15AWOoun+nxo0Oq+TTNAynQd7Sa9nih3RbaMrH8FD4eAbZGFrgXc0vFpeuicEl7fbTSmB2G3XPvAkkTw0GKZXO++j+z9qLaxrXL3dWa8BYzhno5GWQjW5yrcLBxvoF31AgMBAAECggEAJDoWASuAb0gDsSO8VzJE3X5b4rmRQvvw7LZsPa9p9dLZPWTO6avItUHfahk0+o3Ccbw4k3fURBzotT/2XTmFbTUAnuhxuDS3UQPThrJL0z/O/zZHEzsm9P25Gh+NX/RiC92TNp/SeP7FwrYf+I7mnq31M/0MLW+Kl+0UaGAiWzuvBRLO1ceriMclXwcQAKuwSkS5gr69kmLSn6Y6NNtWklABkMduOuoU9XYO73Oe9WjTJEf3DnzRxrjS6kyUn9ylQjQYj/6mPd/Jok/G2HKOpWCoFSjRGLgXu1LyrNbJv6KKGGiZqkSUS0cGMJOOqhPDqJuWvU4fCRQ/cDEsdLLikQKBgQDaa53WLvd7xScd5TmVkpP8IsjM0rtS1uBulyf2Qwm4AyhcGYIsL+S1jHqsG7JLdMKoVhsmqwolNKUUXr+esFPEjaL4wGkCE90juv22hTcui9lJCoH1vqHC3OvZ0Asinkftik2EsEq1J3yoLLFpdz8pwvpTQpi5fhwqBGI7wVqBQwKBgQC9tiPIzEOsX5CfF2LfUPTwpVUO92Qh4ZhHbN/iXaj0IC3GKNLrMQLcKUlkcmPr5d1XPsuW0sW20wXVBIkiS6K/07OUlI3/ug4JZ2VeoBTwYUXqP9ewx35ExHecEeADM8OlyqgoKjZyozH0Ds03vhDGvBSRtJHPL6+ypV+uu4x0ZwKBgQCpfsOgFxhUAt1lFKHIEVQnYDLkx084bKkDCOeo8cLT+hdSyTS753qa6+WyM1f7uCLqkM9HBYm874p/MTuQAJMwvxzN3jpes11zQcNq5Ru6UioOxUmukv1igx+/CfPqLFvy2NVo94kx5O+WbgBKsI/ib3zkKZTstRFUBczZRCVgkwKBgH6ZC2w2E3r2EvZ3jr1jsvDcA9UG9kU93HbditHumo6+4oz4NThJ7pSo4JctVszXCkvsVcQCR3pne97ZNgIy4xqL1azzLYdXy4IvA5CP1ylKYHOfwvnvRq3xRbhanVjFtGvwvyJ+ePMrQGyTbBMkvOFCJZ/OFfdi5SacASwDcAlFAoGBALGLkPIRSEQjQOa5UfqTIndkogjnbT9PsF4a0fBq9zYOn7kRoyD6uSGSi9W308dx7sMrVRDyXY9EnLTD9sENk7L5zs3SZhgrKJ/xs/bLD222RhQPbokF/0vyAUmTe+WAJnhVlhOg+sIDb3Z6NiXXo4XzLEezH7UsRLWOG6eiBTcZ";
		System.err.println("公钥: \n\r" + publicKey);
		System.err.println("私钥： \n\r" + privateKey);

		/*
		 * System.err.println("公钥加密——私钥解密"); String inputStr = "abc"; byte[] data =
		 * inputStr.getBytes(); byte[] encodedData = encryptByPublicKey(data,
		 * publicKey); byte[] decodedData = decrypByPrivateKey(encodedData, privateKey);
		 * 
		 * String outputStr = new String(decodedData); System.err.println("加密前: " +
		 * inputStr + "\n\r" + "解密后: " + outputStr);
		 * 
		 * System.err.println("私钥加密——公钥解密"); String inputStr1 = "sign"; byte[] data1 =
		 * inputStr1.getBytes(); byte[] encodedData1 = encryptByPrivateKey(data1,
		 * privateKey); byte[] decodedData1 = decryptByPublicKey(encodedData1,
		 * publicKey);
		 * 
		 * String outputStr1 = new String(decodedData1); System.err.println("加密前: " +
		 * inputStr1 + "\n\r" + "解密后: " + outputStr1);
		 * 
		 * System.err.println("私钥签名——公钥验证签名"); // 产生签名 String sign1 = sign(encodedData1,
		 * privateKey); System.err.println("签名:\r" + sign1);
		 * 
		 * // 验证签名 boolean status1 = verify(encodedData1, publicKey, sign1);
		 * System.err.println("状态:\r" + status1);
		 */

		/*
		 * String a=
		 * "T0ZfFv9P3oJRUiZVCQkmyiB9kY+MS1PMQ7qTigF7gPqZiKz83YyeG1DhHeurVV+GyHCdSW1GlAuGAXkV6dvuWvLMMJLsfzI5uNxXIXAMNTTkNQ/eujL5xUXhZpMXRDcMnQSiZSOw4RfldPyVxjwgnMpNgQdb62l3LGTGtXPYk16b2q0p08TMU800ZwpLm/zmI+O82vFvMo/DWdd59SXawmsiG+0DUVKlav8n3c+z5glbdUPGWUJlI+JeXm9hYAdj8yigfCwSqctsnkslV5TxQpwQckxk0fXr/RUQV1NJ92yiVgeNvQQY2EAnxrKbt4jmv4DFWvHgTvZx/SrLwOEI+g==";
		 * byte[] decodedData = decrypByPrivateKey(Base64Util.decryptBase64(a),
		 * privateKey); System.out.println(new String(decodedData));
		 * 
		 * String b =
		 * "Lj2y/F9BjwvpxrINH4kt2TCxdWY0lXCcA6r5FkcYBrcN0XN+EfaMW/rZOnd2cbs+MmYcJgPiLpd+G8BpIn6GUGSEJKcideRQDROFoM7HqBrv0ZV+H2VhoIrww8TsEzsS9xkk87O5/NcvHor3BAcotYJckLzTpWkW7jtdoRJGKDc3oHga84jWUyi58aQ7AjYxPehBjR+1P4VuG9pN8n3QTKwCZ7Lrsa3o7rvDJMoOUUXqTLACYpGfkCo/CYXTSd+71tJcOPjnjxhm8uLff0vU9v2NaeC/l0RuchjkO32dK9TIqVZRLgG7+CiM2j9U6p80WGZr8+SL+eA+/M/laelkwQ==";
		 * byte[] decodedData1 = decryptByPublicKey(Base64Util.decryptBase64(b),
		 * publicKey);
		 * 
		 * String outputStr1 = new String(decodedData1); System.out.println(new
		 * String(outputStr1));
		 */

		String b = "gglrKeeg1y/WZMr7RPPqP2QZNWboc5it9nXwgKj+gpw0qBHz4+IBTRIlmzwvaP7ChWIKmhbzsFqJf58CNfyhyGfuEL4eTqOpwQj6Lhg9rHc5eenHCVZE8Omd87diGPSxG7TCpEW4zevoE20pCK4/+nYI1/lpM0GdtPzR0XoVN9cHKK/rbUfSuX+zB/CrxfwuAIv+ZNFcIuXmdG5Uoq/dqQXna1zyqybW7K4nb6RuRDMFU2MvX9foZQeE/NJDeZd2rgFefSaFBBCG0UmZeEYFC/UcWYsIVI9lNxKRUfctwuwjhX5b/OqDYyro8eA/wr9X3G/B9+n+tg/cXcG/AVAPxw==";

		String sign1 = "KPnVEQHqfrSJy7krOylCadDEtinWAEF6RD9bdZks0G/IJRoTLTcS/eM3/kh/1O0N3g2cFEH34VWrdUwFVZKmntKJ7/nZJfQuiixDfPd2RfNk/9PNseulFddqtD+dIOhAT6oeq0blIkOH6zWafxfUYWCPJRHmv3P4wXf1U3+IWLGfw4ouFgWGROdbJhdLXCoDCRrwNBCT++T77qXo2F+W42NqT622F5l+CPD85H6a7HEVW2mQRhCdM+3rBPed5F9ePXPE6yDDEwpVSGisNVoM6XePNh1WQwsAPZse89mEqxujJUIwiO3/hPU8jO/E/jCrBiDKdIyxt0N2MVTTbfc6Tw==";
		System.err.println("签名:\r" + sign1);

		boolean status1 = verify(b.getBytes(), publicKey, sign1);
		System.err.println("状态:\r" + status1);

	}

}
