package com.ivan.isaback.service;

import java.util.Optional;

import com.ivan.isaback.model.User;

public interface UserService {
	
	void addUser(User user);
	void deleteUser(Integer id);
	void updateUser(User user);
	Optional<User> findByUsername(String username);

}
