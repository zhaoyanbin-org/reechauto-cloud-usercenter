package com.reechauto.usercenter.common.utils.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class FileWriteUtil {
	private FileOutputStream fos = null;
	private OutputStreamWriter osw = null;
	private BufferedWriter bw = null;
	
	public void writeLine(String text,String filePath, String charsetName,boolean append) throws IOException{
		writeStart(filePath,charsetName,append);
		write(text);
		writeEnd();
	}
	
	public void writeContext(List<String> text,String filePath, String charsetName,boolean append) throws IOException{
		writeStart(filePath,charsetName,append);
		for (String str : text) {
			write(str);
		}
		writeEnd();
	}

	private void writeStart(String filePath, String charsetName,boolean append) throws IOException {
		fos = new FileOutputStream(new File(filePath),append);
		if (StringUtils.isBlank(charsetName)) {
			charsetName = "UTF-8";
		}
		osw = new OutputStreamWriter(fos, charsetName);
		bw = new BufferedWriter(osw);
	}

	private void write(String str) throws IOException {
		bw.write(str);
	}

	private void writeEnd() throws IOException {
		bw.close();
		osw.close();
		fos.close();
	}

}
