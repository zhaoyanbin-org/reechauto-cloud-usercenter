package com.reechauto.cloud.provider.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ThreadPoolConfig {

	@Bean
	public ExecutorService getCachedThreadPool() {
		
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		return executor;
	}
	
	
}
