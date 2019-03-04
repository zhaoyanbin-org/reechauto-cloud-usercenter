package com.reechauto.usercenter.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(value= {"com.reechauto.usercenter"})
@EnableFeignClients
public class UserCenterAuthApplication {

	public static void main(String[] args) {
		log.info("授权服务启动...");
		//String pwd = new BCryptPasswordEncoder().encode("usercenter123456");
		//log.info(pwd);
		SpringApplication.run(UserCenterAuthApplication.class, args);
	}

}

