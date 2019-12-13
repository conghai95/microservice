package com.example.cmc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RestController;

import com.example.cmc.entity.Account;
import com.example.cmc.entity.User;
import com.example.cmc.service.AccountService;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@GetMapping(value = "/list")
	public ResponseEntity<?> findAllAccount() throws AuthenticationException, Exception {
		return ResponseEntity.ok(accountService.getAccounts());
	}

	@PostMapping(value = "/create-user")
	public Account createNewAccount(@Valid @RequestBody User user) {
		return accountService.createAccount(user);
	}
}
