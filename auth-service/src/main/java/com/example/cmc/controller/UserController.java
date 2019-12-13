package com.example.cmc.controller;

import java.net.URI;
import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.cmc.domain.Role;
import com.example.cmc.domain.RoleName;
import com.example.cmc.domain.User;
import com.example.cmc.exception.AppException;
import com.example.cmc.payload.ApiResponse;
import com.example.cmc.payload.JwtAuthenticationResponse;
import com.example.cmc.payload.LoginRequest;
import com.example.cmc.payload.SignUpRequest;
import com.example.cmc.repository.RoleRepository;
import com.example.cmc.security.JwtTokenProvider;
import com.example.cmc.service.UserService;

import java.util.Collections;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private String token = "";
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws AuthenticationException, Exception {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.generateToken(authentication);
		JwtAuthenticationResponse token = new JwtAuthenticationResponse(jwt);
		this.token = token.getTokenType() + " " + token.getAccessToken();
		return ResponseEntity.ok(token);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

		if (userService.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<Object>(new ApiResponse(false, "Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				signUpRequest.getPassword());

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new AppException("User Role not set."));

		user.setRoles(Collections.singleton(userRole));

		User result = userService.createUser(user);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
	}

	@GetMapping(value = "/userAuth")
	public User getUser(Principal principal) {
		System.out.println("principal: " + SecurityContextHolder.getContext());
		User user = userService.findByUserName(principal.getName());
		return user;
	}

	@GetMapping(value = "/list")
	public ResponseEntity<?> getUsers() {
		userService.getUsers();
		return ResponseEntity.ok(new ApiResponse(true, "Get list success!"));
	}
	
	@GetMapping(value = "/tokenShare")
	public String shareToken() {
		if (this.token.equals("")) {
			return null;
		}
		return this.token;
	}
}
