package com.ivan.isaback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.isaback.model.AuthenticationRequest;
import com.ivan.isaback.model.AuthenticationResponse;
import com.ivan.isaback.model.User;
import com.ivan.isaback.service.impl.UserServiceImpl;
import com.ivan.isaback.util.JwtUtil;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	private AuthenticationManager authenticationManager;
	private UserServiceImpl userService;
	private JwtUtil jwtUtil;
	
	public AuthenticationController(AuthenticationManager authenticationManager, UserServiceImpl userService,
			JwtUtil jwtUtil) {
		super();
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		
		final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		
		final User user = userService.findByUsername(request.getUsername()).get();
		user.setToken(jwt);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt, user));
	}
	
}