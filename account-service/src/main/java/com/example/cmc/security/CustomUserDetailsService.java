package com.example.cmc.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.cmc.client.AuthServiceClient;
import com.example.cmc.entity.User;
import com.example.cmc.security.UserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private AuthServiceClient authServiceClient;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		System.out.println("sfsdf: " + authServiceClient.getToken());
		User user = authServiceClient.loadUserDetailService(authServiceClient.getToken());
		return UserPrincipal.create(user);
	}
	
	@Transactional
	public UserDetails loadUserAuth() {
		System.out.println("sfsdf: " + authServiceClient.getToken());
		User user = authServiceClient.loadUserDetailService(authServiceClient.getToken());
		return UserPrincipal.create(user);
	}
}