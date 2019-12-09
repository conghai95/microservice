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

@Service
public class AccountServiceImpl implements AccountService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	AccountRepository repository;

	@Override
	public Account findByName(String accountName) {
		return repository.findByUserName(accountName);
	}

	@Override
	public Account createUser(User user) {

		Account account = new Account();
		account.setId(user.getId());
		account.setUserName(user.getUsername());
		account.setCreateDate(new Date());

		repository.save(account);
		log.info("new account has been created: " + account.getUserName());
		return account;
	}

	@Override
	public void editUser(String name, Account update) {

		Account account = repository.findByUserName(name);

		account.setCreateDate(new Date());
		repository.save(account);

		log.debug("account {} changes has been saved", name);
	}

}
