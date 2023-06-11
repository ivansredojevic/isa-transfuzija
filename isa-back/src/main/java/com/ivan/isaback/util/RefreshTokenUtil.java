package com.ivan.isaback.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class RefreshTokenUtil {

	public static final String SECRET_KEY = "secret_refresh";
	public static final long EXPIRATION = 1000 * 60 * 60 * 11;

	public String generateRefreshToken(UserDetails userDetails) {
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("sub", userDetails.getUsername());
		claims.put("iat", new Date());
		
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.compact();
	}

	public boolean validateRefreshToken(String refreshToken) {
		
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(refreshToken);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}
	
	public String extractUsername(String refreshToken) {
		return extractClaim(refreshToken, Claims::getSubject);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
}