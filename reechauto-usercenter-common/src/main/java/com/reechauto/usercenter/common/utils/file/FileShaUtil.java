package com.reechauto.usercenter.common.utils.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.security.MessageDigest;

import org.apache.commons.lang.StringUtils;

public class FileShaUtil {
	public static byte[] createChecksum(String filename) throws Exception {
		InputStream fis = new FileInputStream(filename);
		byte[] buffer = new byte[1024];
		MessageDigest complete = MessageDigest.getInstance("SHA-256");
		int numRead;
		do {
			numRead = fis.read(buffer);
			if (numRead > 0) {
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1);

		fis.close();
		return complete.digest();
	}

	public static String toHexString(String fullFilename) throws Exception {
		byte[] b = createChecksum(fullFilename);
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

	public static void createHashFile(String fullHashFileName, String fullFilename) throws Exception {
		String code = toHexString(fullFilename);
		if (StringUtils.isNotBlank(code)) {
			File file = new File(fullHashFileName);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();

			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(code);
			bw.flush();
			bw.close();
			fw.close();
		} else {
			throw new RuntimeException("没有生成成功hashCode");
		}
	}

	public static void main(String args[]) {
		try {
			//System.out.println(toHexString("d:\\zip\\文件导出.csv.zip"));
			createHashFile("d:\\zip\\aa.hash","d:\\zip\\文件导出.csv.zip");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
