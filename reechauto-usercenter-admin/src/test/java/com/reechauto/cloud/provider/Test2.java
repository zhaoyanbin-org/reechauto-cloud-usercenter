package com.reechauto.cloud.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class Test2 {
	
	
	@Test
	public void Create() throws Exception {
		String string = "a,b,c,d";
		
		List<String> list = Arrays.asList(string.split(","));
		
		for(String string1:list) {
			System.out.println(string1);
		}
		
		System.out.println("************************************************************************************************");
		
		System.out.println(String.join(",", list));
	}
}
