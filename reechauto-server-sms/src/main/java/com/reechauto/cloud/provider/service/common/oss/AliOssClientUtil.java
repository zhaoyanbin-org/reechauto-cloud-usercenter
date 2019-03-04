package com.reechauto.cloud.provider.service.common.oss;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.ObjectMetadata;
import com.reechauto.cloud.provider.exception.ImgException;

public class AliOssClientUtil {
	Log log = LogFactory.getLog(AliOssClientUtil.class);

	private String endpoint;
	private String accessKeyId;
	private String accessKeySecret;
	private String bucketName;

	private OSSClient ossClient;

	public AliOssClientUtil(String endpoint, String accessKeyId, String accessKeySecret, String bucketName) {
		this.endpoint = endpoint;
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
		this.bucketName = bucketName;
	}

	/**
	 * 初始化
	 */
	public void createOssClient() {
		CredentialsProvider credsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
		ossClient = new OSSClient(endpoint, credsProvider, null);
	}

	/**
	 * 销毁
	 */
	public void destory() {
		if (ossClient != null) {
			ossClient.shutdown();
		}
	}
	
	public String uploadImg2Oss(String path) {
		try {
			File file = new File(path);
			FileInputStream inputStream = new FileInputStream(file);
			Random random = new Random();
			String fileName = "" + random.nextInt(10000) + System.currentTimeMillis();
			String suffix ="text";
			int n=path.lastIndexOf(".") + 1;
			if(n>0) {
				suffix = path.substring(path.lastIndexOf(".") + 1).toLowerCase();
			}
			return this.uploadFile2OSS(inputStream, "data", fileName, suffix);
		} catch (FileNotFoundException e) {
			throw new ImgException("图片上传失败");
		}
	}

	public String uploadImg2Oss(String path, String fileDir) {
		try {
			File file = new File(path);
			FileInputStream inputStream = new FileInputStream(file);
			Random random = new Random();
			String fileName = "" + random.nextInt(10000) + System.currentTimeMillis();
			String suffix ="text";
			int n=path.lastIndexOf(".") + 1;
			if(n>0) {
				suffix = path.substring(path.lastIndexOf(".") + 1).toLowerCase();
			}
			return this.uploadFile2OSS(inputStream, fileDir, fileName, suffix);
		} catch (FileNotFoundException e) {
			throw new ImgException("图片上传失败");
		}
	}

	public String uploadImg2Oss(String path, String fileDir, String fileName) {
		try {
			File file = new File(path);
			FileInputStream inputStream = new FileInputStream(file);
			String suffix ="text";
			int n=path.lastIndexOf(".") + 1;
			if(n>0) {
				suffix = path.substring(path.lastIndexOf(".") + 1).toLowerCase();
			}
			return this.uploadFile2OSS(inputStream, fileDir, fileName, suffix);
		} catch (FileNotFoundException e) {
			throw new ImgException("图片上传失败");
		}
	}

	public String uploadImg2Oss(MultipartFile file, String fileDir) {
		if (file.getSize() > 1024 * 1024 * 20) {
			throw new ImgException("上传图片大小不能超过20M！");
		}
		String originalFilename = file.getOriginalFilename();
		String suffix ="text";
		int n=originalFilename.lastIndexOf(".") + 1;
		if(n>0) {
			suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
		}
		Random random = new Random();
		String fileName = "" + random.nextInt(10000) + System.currentTimeMillis();
		try {
			InputStream inputStream = file.getInputStream();
			return this.uploadFile2OSS(inputStream, fileDir, fileName, suffix);
		} catch (Exception e) {
			throw new ImgException("图片上传失败");
		}
	}
	
	public String uploadImg2Oss(MultipartFile file) {
		if (file.getSize() > 1024 * 1024 * 20) {
			throw new ImgException("上传图片大小不能超过20M！");
		}
		String originalFilename = file.getOriginalFilename();
		String suffix ="text";
		int n=originalFilename.lastIndexOf(".") + 1;
		if(n>0) {
			suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
		}
		Random random = new Random();
		String fileName = "" + random.nextInt(10000) + System.currentTimeMillis();
		try {
			InputStream inputStream = file.getInputStream();
			return this.uploadFile2OSS(inputStream, "data", fileName, suffix);
		} catch (Exception e) {
			throw new ImgException("图片上传失败");
		}
	}

	public String uploadImg2Oss(MultipartFile file, String fileDir, String fileName) {
		if (file.getSize() > 1024 * 1024 * 20) {
			throw new ImgException("上传图片大小不能超过20M！");
		}
		String originalFilename = file.getOriginalFilename();
		String suffix ="text";
		int n=originalFilename.lastIndexOf(".") + 1;
		if(n>0) {
			suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
		}
		try {
			System.out.println("fileDir:"+fileDir);
			System.out.println("fileName:"+fileName);
			System.out.println("suffix:"+suffix);
			InputStream inputStream = file.getInputStream();
			return this.uploadFile2OSS(inputStream, fileDir, fileName, suffix);
		} catch (Exception e) {
			throw new ImgException("图片上传失败");
		}
	}

	public String uploadFile2OSS(InputStream instream, String fileDir, String fileName, String suffix) {
		try {
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(instream.available());
			objectMetadata.setCacheControl("no-cache");// 设置Cache-Control请求头，表示用户指定的HTTP请求/回复链的缓存行为:不经过本地缓存
			objectMetadata.setHeader("Pragma", "no-cache");// 设置页面不缓存
			objectMetadata.setContentType(getcontentType(suffix));
			objectMetadata.setContentDisposition("inline;filename=" + fileName + suffix);
			String fullName = fileName + suffix;
			// 上传文件
			Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 20);
			ossClient.putObject(bucketName, fileDir + "/" + fullName, instream, objectMetadata);
			URL url = ossClient.generatePresignedUrl(bucketName, fileDir + "/" + fullName, expiration);
			return url.toString();
		} catch (IOException e) {
			throw new ImgException("图片上传失败");
		} 
	}

	public String getcontentType(String suffix) {
		if (suffix.equalsIgnoreCase(".bmp")) {
			return "image/bmp";
		}
		if (suffix.equalsIgnoreCase(".gif")) {
			return "image/gif";
		}
		if (suffix.equalsIgnoreCase(".jpeg") || suffix.equalsIgnoreCase(".jpg") || suffix.equalsIgnoreCase(".png")) {
			return "image/jpeg";
		}
		if (suffix.equalsIgnoreCase(".html")) {
			return "text/html";
		}
		if (suffix.equalsIgnoreCase(".txt")) {
			return "text/plain";
		}
		if (suffix.equalsIgnoreCase(".vsd")) {
			return "application/vnd.visio";
		}
		if (suffix.equalsIgnoreCase(".pptx") || suffix.equalsIgnoreCase(".ppt")) {
			return "application/vnd.ms-powerpoint";
		}
		if (suffix.equalsIgnoreCase(".docx") || suffix.equalsIgnoreCase(".doc")) {
			return "application/msword";
		}
		if (suffix.equalsIgnoreCase(".xml")) {
			return "text/xml";
		}
		if (suffix.equalsIgnoreCase("mp3")) {
			return "audio/mp3";
		}
		if (suffix.equalsIgnoreCase("amr")) {
			return "audio/amr";
		}
		return "text/plain";

	}

}
