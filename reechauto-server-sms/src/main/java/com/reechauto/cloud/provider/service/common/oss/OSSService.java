package com.reechauto.cloud.provider.service.common.oss;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OSSService {

	@Value("${aliyun.oss.endpoint}")
	private String endpoint;
	@Value("${aliyun.oss.accessKeyId}")
	private String accessKeyId;
	@Value("${aliyun.oss.accessKeySecret}")
	private String accessKeySecret;
	@Value("${aliyun.oss.bucketName}")
	private String bucketName;

	public String uploadImg2Oss(String path) {
		String imageUrl = "";
		AliOssClientUtil util = new AliOssClientUtil(endpoint, accessKeyId, accessKeySecret, bucketName);
		util.createOssClient();
		imageUrl = util.uploadImg2Oss(path);
		util.destory();
		return imageUrl;
	}

	public String uploadImg2Oss(String path, String fileDir) {
		String imageUrl = "";
		AliOssClientUtil util = new AliOssClientUtil(endpoint, accessKeyId, accessKeySecret, bucketName);
		util.createOssClient();
		imageUrl = util.uploadImg2Oss(path,fileDir);
		util.destory();
		return imageUrl;
	}

	public String uploadImg2Oss(String path, String fileDir, String fileName) {
		String imageUrl = "";
		AliOssClientUtil util = new AliOssClientUtil(endpoint, accessKeyId, accessKeySecret, bucketName);
		util.createOssClient();
		imageUrl = util.uploadImg2Oss(path,fileDir,fileName);
		util.destory();
		return imageUrl;
	}

	public String uploadImg2Oss(MultipartFile file, String fileDir) {
		String imageUrl = "";
		AliOssClientUtil util = new AliOssClientUtil(endpoint, accessKeyId, accessKeySecret, bucketName);
		util.createOssClient();
		imageUrl = util.uploadImg2Oss(file,fileDir);
		util.destory();
		return imageUrl;
	}

	public String uploadImg2Oss(MultipartFile file) {
		String imageUrl = "";
		AliOssClientUtil util = new AliOssClientUtil(endpoint, accessKeyId, accessKeySecret, bucketName);
		util.createOssClient();
		imageUrl = util.uploadImg2Oss(file);
		util.destory();
		return imageUrl;
	}

	public String uploadImg2Oss(MultipartFile file, String fileDir, String fileName) {
		String imageUrl = "";
		AliOssClientUtil util = new AliOssClientUtil(endpoint, accessKeyId, accessKeySecret, bucketName);
		util.createOssClient();
		imageUrl = util.uploadImg2Oss(file,fileDir,fileName);
		util.destory();
		return imageUrl;
	}

	public String uploadFile2OSS(InputStream instream, String fileDir, String fileName, String suffix) {
		String imageUrl = "";
		AliOssClientUtil util = new AliOssClientUtil(endpoint, accessKeyId, accessKeySecret, bucketName);
		util.createOssClient();
		imageUrl = util.uploadFile2OSS(instream,fileDir,fileName,suffix);
		util.destory();
		return imageUrl;
	}

	public static void main(String[] args) throws IOException {
		AliOssClientUtil util = new AliOssClientUtil("https://oss-cn-beijing.aliyuncs.com", "LTAI4neWcL5za1ri",
				"JWgKRm5uoGSeUnFYtXTGxaXPz1lxFL", "reechauto-file");
		util.createOssClient();
		String aa = util.uploadImg2Oss("F:\\1211");
		System.out.println(aa);
		util.destory();

		// InputStream instream = new FileInputStream(new File("F:\\111.png"));
		// String aa = PictureUploadUtils.UploadToAliyun("data/", instream, "png");
		// System.out.println(aa);
	}

}
