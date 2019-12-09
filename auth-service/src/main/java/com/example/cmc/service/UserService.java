package com.example.cmc.service;

import java.util.List;

import com.example.cmc.domain.User;

public interface UserService {

	public void createUser(User user);

	public List<User> getUsers();
}
