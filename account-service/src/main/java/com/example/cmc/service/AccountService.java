package com.example.cmc.service;

import java.util.List;

import com.example.cmc.entity.Account;
import com.example.cmc.entity.User;

public interface AccountService {

	/**
	 * Checks if account with the same name already exists Invokes Auth Service user
	 * creation Creates new account with default parameters
	 *
	 * @param user
	 * @return created account
	 */
	Account createAccount(User user);
	
	List<Account> getAccounts();
}
