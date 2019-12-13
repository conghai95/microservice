package com.example.cmc.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.cmc.entity.User;

@FeignClient(name = "auth-service", url = "http://localhost:8080")
@RequestMapping(value = "/api/users")
public interface AuthServiceClient {
	
	@GetMapping(value = "/list")
	public List<User> getUsers();
	
	@GetMapping(value = "/tokenShare")
	public String getToken();
	
	@GetMapping(value ="/userAuth")
	public User loadUserDetailService(@RequestHeader(value = "Authorization", required = true) String authorizationHeader);
}
