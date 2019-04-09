package com.reechauto.usercenter.common.utils.crypt.desc;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.reechauto.usercenter.common.utils.crypt.base64.Base64Util;

public class DesUtil {

	/**
	 * 生成Key
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Key createKey() throws Exception {
		SecureRandom secureRandom = new SecureRandom();
		KeyGenerator kg = KeyGenerator.getInstance("DES");
		kg.init(secureRandom);
		SecretKey secretKey = kg.generateKey();
		return secretKey;
	}

	/**
	 * 加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data, String key) throws Exception {
		byte[] bytes = encrypt(data.getBytes("UTF-8"), key);
		return Base64Util.encryptBase64(bytes);
	}

	/**
	 * 加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data, Key key) throws Exception {
		byte[] bytes = encrypt(data.getBytes("UTF-8"), key);
		return Base64Util.encryptBase64(bytes);
	}

	/**
	 * 解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String data, String key) throws Exception {
		byte[] bytes = decrypt(Base64Util.decryptBase64(data), key);
		return new String(bytes, "UTF-8");
	}

	/**
	 * 解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String data, Key key) throws Exception {
		byte[] bytes = decrypt(Base64Util.decryptBase64(data), key);
		return new String(bytes, "UTF-8");
	}

	/**
	 * 密钥转换
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) throws Exception {
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(dks);
		// 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
		// SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
		return secretKey;
	}

	/**
	 * 加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] data, String key) throws Exception {
		SecureRandom sr = new SecureRandom();
		Key k = toKey(key.getBytes("UTF-8"));
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, k, sr);
		return cipher.doFinal(data);
	}

	/**
	 * 加密
	 * 
	 * @param data
	 * @param k
	 * @return
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] data, Key k) throws Exception {
		SecureRandom sr = new SecureRandom();
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, k, sr);
		return cipher.doFinal(data);
	}

	/**
	 * 解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] data, String key) throws Exception {
		SecureRandom sr = new SecureRandom();
		Key k = toKey(key.getBytes("UTF-8"));
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, k, sr);
		return cipher.doFinal(data);
	}

	/**
	 * 解密
	 * 
	 * @param data
	 * @param k
	 * @return
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] data, Key k) throws Exception {
		SecureRandom sr = new SecureRandom();
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, k, sr);
		return cipher.doFinal(data);
	}

}
