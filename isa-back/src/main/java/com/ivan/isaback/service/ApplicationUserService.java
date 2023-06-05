package com.ivan.isaback.service;

import java.util.Optional;

import com.ivan.isaback.model.ApplicationUser;
import com.ivan.isaback.model.dto.ApplicationUserDTO;

public interface ApplicationUserService {
	
	ApplicationUser registerUser(ApplicationUser user);
	void deleteUser(int id);
	void updateUser(ApplicationUserDTO user);
	void addPenalty(ApplicationUserDTO user);
	void updatePassword(ApplicationUserDTO user);
	ApplicationUser findByUsername(String username);
	Optional<ApplicationUser> findByEmail(String email);
	boolean activateUser(String token);

}
