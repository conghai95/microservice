package com.example.cmc.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cmc.client.AccountServiceClient;
import com.example.cmc.domain.User;
import com.example.cmc.repository.UserRepository;
import com.example.cmc.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountServiceClient accountServiceClient;

	@Override
	public User createUser(User user) {
		System.out.println("user auth: " + user);
		accountServiceClient.createUserAccount(user);
		return userRepository.save(user);
	}
	
	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public User findByUserName(String name) {
		return userRepository.findByUsername(name);
	}

}
