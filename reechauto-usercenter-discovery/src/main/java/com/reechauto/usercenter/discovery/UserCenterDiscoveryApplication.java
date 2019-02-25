package com.reechauto.usercenter.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class UserCenterDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserCenterDiscoveryApplication.class, args);
	}

}

