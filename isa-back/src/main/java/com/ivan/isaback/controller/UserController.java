package com.ivan.isaback.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.isaback.model.User;
import com.ivan.isaback.model.dto.UserDTO;
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

	@PostMapping("register")
	public ResponseEntity<String> addUser(@RequestBody User user) {
//		log.info("insert " + user);
		if (userService.findByEmail(user.getEmail()).isPresent()) {
			return ResponseEntity.ok("User with email '" + user.getEmail() + "' already exists.");			
		}
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		log.info("insert: " + user);
		User u = userService.registerUser(user);
		log.info("" + u);
		return ResponseEntity.ok("OK");
	}
	
	@PutMapping("update/{id}")
	public void updateUser(@RequestBody UserDTO userDto) {
		log.info("update " + userDto);
		userService.updateUser(userDto);
	}
	
	
	@PutMapping("update-password/{id}")
	public void updatePassword(@RequestBody UserDTO userDto) {
		log.info("update password " + userDto);
		userService.updatePassword(userDto);
	}
	
	@DeleteMapping("delete/{id}")
	public void deleteUser(@PathVariable int id) {
		log.info("delete " + id);
		this.userService.deleteUser(id);
	}
	
	@GetMapping(path = "activate/{token}")
	public ResponseEntity<String> activate(@PathVariable String token) {
		
		if(!userService.activateUser(token)) {
			return new ResponseEntity<>("Account already activated or token doesn't exist", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("Account activated", HttpStatus.OK);
		
	}
	
}
