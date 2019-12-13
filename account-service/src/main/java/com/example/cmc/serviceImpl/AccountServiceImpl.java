package com.example.cmc.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cmc.entity.Account;
import com.example.cmc.entity.User;
import com.example.cmc.repository.AccountRepository;
import com.example.cmc.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public Account createAccount(User user) {

		Account account = new Account();  
		account.setUserName(user.getUsername());
		account.setCreateDate(new Date());

		accountRepository.save(account);
		log.info("new account has been created: " + account.getUserName());
		return account;
	}

	@Override
	public List<Account> getAccounts() {
		return accountRepository.findAll();
	}
	
	

}
