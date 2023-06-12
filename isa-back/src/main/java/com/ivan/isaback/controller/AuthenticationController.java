package com.ivan.isaback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.isaback.model.AuthenticationRequest;
import com.ivan.isaback.model.AuthenticationResponse;
import com.ivan.isaback.model.RefreshTokenRequest;
import com.ivan.isaback.model.RefreshTokenResponse;
import com.ivan.isaback.service.AuthenticationService;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthenticationController {

	private AuthenticationService authenticationService;

	public AuthenticationController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	@PostMapping("/generate-token")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) throws Exception {
		AuthenticationResponse authenticationResponse = authenticationService.generateToken(request);
		return ResponseEntity.ok(authenticationResponse);
	}

	@PostMapping("/refresh-token")
	public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
		RefreshTokenResponse response = authenticationService.refreshToken(refreshTokenRequest);
		return ResponseEntity.ok(response);
	}

}
