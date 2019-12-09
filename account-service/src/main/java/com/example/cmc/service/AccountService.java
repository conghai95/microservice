package com.example.cmc.service;

import com.example.cmc.entity.Account;
import com.example.cmc.entity.User;

public interface AccountService {
	/**
	 * Finds account by given name
	 *
	 * @param accountName
	 * @return found account
	 */
	Account findByName(String accountName);

	/**
	 * Checks if account with the same name already exists Invokes Auth Service user
	 * creation Creates new account with default parameters
	 *
	 * @param user
	 * @return created account
	 */
	Account createUser(User user);

	/**
	 * Validates and applies incoming account updates Invokes Statistics Service
	 * update
	 *
	 * @param name
	 * @param update
	 */
	void editUser(String name, Account update);
}
