package com.ivan.isaback.service;

import java.util.Optional;

import com.ivan.isaback.model.User;

public interface UserService {
	
	User registerUser(User user);
	void deleteUser(int id);
	void updateUser(User user);
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	boolean activateUser(String token);

}
