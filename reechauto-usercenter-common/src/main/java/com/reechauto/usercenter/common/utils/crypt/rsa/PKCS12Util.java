package com.reechauto.usercenter.common.utils.crypt.rsa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Enumeration;

public class PKCS12Util {

	/**
	 * 通常为*.p12或*.pfx
	 * 
	 * @param keyStorePath
	 * @param password
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws KeyStoreException
	 */
	private KeyStore getKeyStore(String keyStorePath, String password)
			throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException {
		// 实例化密钥库，默认JKS类型
		KeyStore ks = KeyStore.getInstance("PKCS12");
		// 获得密钥库文件流
		FileInputStream is = new FileInputStream(keyStorePath);
		// 加载密钥库
		ks.load(is, password.toCharArray());
		// 关闭密钥库文件流
		is.close();
		return ks;
	}

	/**
	 * 获取私钥
	 * 
	 * @param keyStorePath
	 *            证书地址
	 * @param password
	 *            证书密码
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws KeyStoreException
	 * @throws UnrecoverableKeyException
	 */
	public PrivateKey getPrivateKey(String keyStorePath, String password) throws IOException, NoSuchAlgorithmException,
			CertificateException, KeyStoreException, UnrecoverableKeyException {
		KeyStore ks = getKeyStore(keyStorePath, password);
		Enumeration<?> aliases = ks.aliases();
		String keyAlias = null;
		if (aliases.hasMoreElements()) {
			keyAlias = (String) aliases.nextElement();
			System.out.println("p12's alias----->" + keyAlias);
		}
		PrivateKey privateKey = (PrivateKey) ks.getKey(keyAlias, password.toCharArray());
		return privateKey;
	}

	/**
	 * 获取私钥字符串
	 * 
	 * @param keyStorePath
	 *            证书地址
	 * @param password
	 *            证书密码
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws KeyStoreException
	 * @throws UnrecoverableKeyException
	 */
	public String getPrivateKeyStr(String keyStorePath, String password) throws IOException, NoSuchAlgorithmException,
			CertificateException, KeyStoreException, UnrecoverableKeyException {
		PrivateKey privateKey = getPrivateKey(keyStorePath, password);
		String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
		return privateKeyStr;
	}

	/**
	 * 获取公钥
	 * 
	 * @param keyStorePath
	 *            证书地址
	 * @param password
	 *            证书密码
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws KeyStoreException
	 * @throws UnrecoverableKeyException
	 */
	public PublicKey getPublicKey(String keyStorePath, String password) throws IOException, NoSuchAlgorithmException,
			CertificateException, KeyStoreException, UnrecoverableKeyException {
		KeyStore ks = getKeyStore(keyStorePath, password);
		Enumeration<?> aliases = ks.aliases();
		String keyAlias = null;
		if (aliases.hasMoreElements()) {
			keyAlias = (String) aliases.nextElement();
			System.out.println("p12's alias----->" + keyAlias);
		}
		Certificate certificate = ks.getCertificate(keyAlias);
		PublicKey publicKey = certificate.getPublicKey();
		return publicKey;
	}

	/**
	 * 获取公钥字符串
	 * 
	 * @param keyStorePath
	 *            证书地址
	 * @param password
	 *            证书密码
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws KeyStoreException
	 * @throws UnrecoverableKeyException
	 */
	public String getPublicKeyStr(String keyStorePath, String password) throws IOException, NoSuchAlgorithmException,
			CertificateException, KeyStoreException, UnrecoverableKeyException {
		PublicKey publicKey = getPublicKey(keyStorePath, password);
		String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
		return publicKeyStr;
	}

	/**
	 * 获取公钥
	 * @param crtPath 数字证书的路径
	 * @return
	 * @throws CertificateException
	 * @throws FileNotFoundException
	 */
	public PublicKey getPublicKey(String crtPath) throws CertificateException, FileNotFoundException {
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		X509Certificate cert = (X509Certificate) cf.generateCertificate(new FileInputStream(crtPath));
		PublicKey publicKey = cert.getPublicKey();
		return publicKey;
	}

	/**
	 * 获取公钥字符串
	 * @param crtPath 数字证书的路径
	 * @return
	 * @throws CertificateException
	 * @throws FileNotFoundException
	 */
	public String getPublicKeyStr(String crtPath) throws CertificateException, FileNotFoundException {
		PublicKey publicKey = getPublicKey(crtPath);
		String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
		return publicKeyStr;
	}

	/**
	 * 保存到指定文件
	 * 
	 * @param path
	 * @param key
	 * @throws Exception
	 */
	public static void saveFile(String path, String key) throws Exception {
		FileOutputStream fos = new FileOutputStream(path);
		fos.write(key.getBytes());
		fos.flush();
		fos.close();
	}

}
