package com.example.cmc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.cmc.client.AuthServiceClient;
import com.example.cmc.entity.Account;
import com.example.cmc.entity.User;
import com.example.cmc.service.AccountService;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AuthServiceClient authServiceClient;
	
	@GetMapping(value = "/list")
	public List<User> getUserFromAccountService() {
		return authServiceClient.getUsers();
	}

	@GetMapping(value = "{name}")
	public Account getAccountByName(@PathVariable String name) {
		return accountService.findByName(name);
	}

	@GetMapping(value = "current")
	public Account getCurrentAccount(Principal principal) {
		return accountService.findByName(principal.getName());
	}

	@PutMapping(value = "current")
	public void saveCurrentAccount(Principal principal, @Valid @RequestBody Account account) {
		accountService.editUser(principal.getName(), account);
	}

	@PostMapping(value = "/create-user")
	public Account createNewAccount(@Valid @RequestBody User user) {
		System.out.println("user: " + user);
		return accountService.createUser(user);
	}
}
