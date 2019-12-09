package com.example.cmc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CmcApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmcApplication.class, args);
	}

}
