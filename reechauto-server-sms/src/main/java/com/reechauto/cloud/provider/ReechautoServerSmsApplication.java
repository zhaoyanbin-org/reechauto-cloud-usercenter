package com.reechauto.cloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value= {"com.reechauto"})
public class ReechautoServerSmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReechautoServerSmsApplication.class, args);
	}

}
