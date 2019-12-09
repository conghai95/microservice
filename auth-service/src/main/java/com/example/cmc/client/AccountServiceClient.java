package com.example.cmc.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.cmc.domain.User;

@FeignClient(name = "account-service", url = "http://localhost:8081")
@RequestMapping(value = "/account")
public interface AccountServiceClient {

	@PostMapping(value = "/create-user")
	public void createUserAccount(@RequestBody User user);
}
