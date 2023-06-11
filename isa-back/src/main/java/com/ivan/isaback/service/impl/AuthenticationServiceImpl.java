package com.ivan.isaback.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ivan.isaback.model.ApplicationUser;
import com.ivan.isaback.model.AuthenticationRequest;
import com.ivan.isaback.model.AuthenticationResponse;
import com.ivan.isaback.model.RefreshTokenRequest;
import com.ivan.isaback.model.RefreshTokenResponse;
import com.ivan.isaback.service.AuthenticationService;
import com.ivan.isaback.util.JwtUtil;
import com.ivan.isaback.util.RefreshTokenUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private ApplicationUserServiceImpl userService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private RefreshTokenUtil refreshUtil;
	
	@Override
	public AuthenticationResponse generateToken(AuthenticationRequest request) throws Exception {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		final String refreshToken = refreshUtil.generateRefreshToken(userDetails);

		final ApplicationUser user = userService.findByUsername(request.getUsername());
		user.setToken(jwt);
		
		return new AuthenticationResponse(jwt, refreshToken, user);
	}

	@Override
	public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
		
		String refreshToken = request.getRefreshToken();
		
		String username = refreshUtil.extractUsername(refreshToken);
		
		final UserDetails userDetails = userService.loadUserByUsername(username);
		if(refreshUtil.validateRefreshToken(refreshToken)) {
			final String newToken = jwtUtil.generateToken(userDetails);
			final String newRefreshToken = refreshUtil.generateRefreshToken(userDetails);
			return new RefreshTokenResponse(newToken, newRefreshToken);
		}
		return null;
	}

}
