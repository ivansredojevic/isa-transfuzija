package com.ivan.isaback.service;

import java.util.Optional;

import com.ivan.isaback.model.ApplicationUser;
import com.ivan.isaback.model.dto.ActivationDTO;
import com.ivan.isaback.model.dto.ApplicationUserDTO;
import com.ivan.isaback.model.dto.RegisterUserDTO;

public interface ApplicationUserService {
	
	String registerUser(RegisterUserDTO user);
	void deleteUser(int id);
	void updateUser(ApplicationUserDTO user);
	void addPenalty(ApplicationUserDTO user);
	void updatePassword(ApplicationUser user);
	ApplicationUser findByUsername(String username);
	ApplicationUserDTO getCurrentByUsername(String username);
	Optional<ApplicationUser> findByEmail(String email);
	ActivationDTO activateUser(String token);
	boolean evaluateConditions(String username);

}
