package com.ivan.isaback.controller;

import java.util.Optional;

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

import com.ivan.isaback.model.ApplicationUser;
import com.ivan.isaback.model.dto.ApplicationUserDTO;
import com.ivan.isaback.service.ApplicationUserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/users/")
public class ApplicationUserController {
	
	private ApplicationUserService userService;
	public ApplicationUserController(ApplicationUserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("register")
	public ResponseEntity<String> addUser(@RequestBody ApplicationUser user) {
		if (userService.findByEmail(user.getEmail()).isPresent()) {
			return ResponseEntity.ok("User with email '" + user.getEmail() + "' already exists.");			
		}
		log.info("insert: " + user);
		ApplicationUser u = userService.registerUser(user);
		log.info("" + u);
		return ResponseEntity.ok("OK");
	}
	
	@PutMapping("update/{id}")
	public void updateUser(@RequestBody ApplicationUserDTO userDto) {
		log.info("update " + userDto);
		userService.updateUser(userDto);
	}
	
	
	@PutMapping("update-password/{id}")
	public void updatePassword(@RequestBody ApplicationUserDTO userDto) {
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
	
	@GetMapping(path = "get/{username}")
	public ResponseEntity<ApplicationUser> getByUsername(@PathVariable String username) {
		
		ApplicationUser user = userService.findByUsername(username);
		if(user != null) {
			return ResponseEntity.status(HttpStatus.OK).body(user);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	@PutMapping("penalty/{id}")
	public void addPenalty(@RequestBody ApplicationUserDTO userDto) {
		log.info("add epnalty " + userDto);
		userService.updateUser(userDto);
	}
	
}
