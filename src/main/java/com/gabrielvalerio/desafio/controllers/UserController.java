package com.gabrielvalerio.desafio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielvalerio.desafio.model.User;
import com.gabrielvalerio.desafio.repository.UserRepository;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			user.password = passwordEncoder.encode(user.password);
			final User savedUser = userRepository.save(user);
			return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
