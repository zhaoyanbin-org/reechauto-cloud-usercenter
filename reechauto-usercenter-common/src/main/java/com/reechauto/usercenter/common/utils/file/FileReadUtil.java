package com.reechauto.usercenter.common.utils.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class FileReadUtil {

	public List<String> read(String path, String charset) throws Exception {
		List<String> list = new ArrayList<String>();
		String encoding = StringUtils.isNotBlank(charset)?charset:"GBK";
		File file = new File(path);
		if (file.isFile() && file.exists()) { // 判断文件是否存在
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				list.add(lineTxt);
			}
			read.close();
		} else {
			throw new RuntimeException("找不到指定的文件");
		}
		return list;
	}

}
