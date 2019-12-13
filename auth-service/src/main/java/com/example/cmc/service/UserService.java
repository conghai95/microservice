package com.example.cmc.service;

import java.util.List;

import com.example.cmc.domain.User;

public interface UserService {
	
	public User findByUserName(String name);

	public User createUser(User user);

	public List<User> getUsers();

	public Boolean existsByUsername(String username);
}
