package com.ivan.isaback.service;

import java.util.Optional;

import com.ivan.isaback.model.ApplicationUser;
import com.ivan.isaback.model.dto.UserDTO;

public interface UserService {
	
	ApplicationUser registerUser(ApplicationUser user);
	void deleteUser(int id);
	void updateUser(UserDTO user);
	void updatePassword(UserDTO user);
	ApplicationUser findByUsername(String username);
	Optional<ApplicationUser> findByEmail(String email);
	boolean activateUser(String token);

}
