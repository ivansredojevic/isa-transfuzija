package com.ivan.isaback.service;

import com.ivan.isaback.model.AuthenticationRequest;
import com.ivan.isaback.model.AuthenticationResponse;
import com.ivan.isaback.model.RefreshTokenRequest;
import com.ivan.isaback.model.RefreshTokenResponse;

public interface AuthenticationService {
	
	AuthenticationResponse generateToken(AuthenticationRequest request) throws Exception;
	RefreshTokenResponse refreshToken(RefreshTokenRequest request);
	
}
