package com.ivan.isaback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.isaback.model.User;
import com.ivan.isaback.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/users/")
public class UserController {
	
	private UserService userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@PostMapping
	public ResponseEntity<String> addUser(@RequestBody User user) {
		log.info("insert " + user);
		if (userService.findByUsername(user.getUsername()).isPresent()) {
			return ResponseEntity.ok("User with the username '" + user.getUsername() + "' already exists.");			
		}
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userService.addUser(user);
		return ResponseEntity.ok("OK");
	}
	
	@PutMapping("{id}")
	public void updateSubscriber(@RequestBody User user) {
		log.info("update " + user);
		userService.updateUser(user);
	}
	
	@DeleteMapping("{id}")
	public void deleteSubscriber(@PathVariable int id) {
		log.info("delete " + id);
		this.userService.deleteUser(id);
	}
	
}
