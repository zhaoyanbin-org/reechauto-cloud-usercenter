package com.reechauto.usercenter.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@ComponentScan(value= {"com.reechauto.usercenter"})
public class UserCenterUserApplication {

	public static void main(String[] args) {
		log.info("资源服务器--用户中心启动...");
		SpringApplication.run(UserCenterUserApplication.class, args);
	}

}

