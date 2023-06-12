package com.ivan.isaback.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.isaback.model.ApplicationUser;
import com.ivan.isaback.model.dto.ActivationDTO;
import com.ivan.isaback.model.dto.ApplicationUserDTO;
import com.ivan.isaback.model.dto.ConditionsEvaluationDTO;
import com.ivan.isaback.model.dto.RegisterUserDTO;
import com.ivan.isaback.service.ApplicationUserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/users/")
public class ApplicationUserController {

	private ApplicationUserService userService;
	
	public ApplicationUserController(ApplicationUserService userService) {
		super();
		this.userService = userService;
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@PutMapping("update/{id}")
	public void updateUser(@RequestBody ApplicationUserDTO userDto) {
		log.info("update " + userDto);
		userService.updateUser(userDto);
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@PutMapping("update-password/{id}")
	public void updatePassword(@RequestBody ApplicationUser user) {
		log.info("update password " + user);
		userService.updatePassword(user);
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@DeleteMapping("delete/{id}")
	public void deleteUser(@PathVariable int id) {
		log.info("delete " + id);
		this.userService.deleteUser(id);
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping(path = "get/{username}")
	public ResponseEntity<ApplicationUser> getByUsername(@PathVariable String username) {

		ApplicationUser user = userService.findByUsername(username);
		if (user != null) {
			return ResponseEntity.status(HttpStatus.OK).body(user);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping(path = "load-user/{username}")
	public ResponseEntity<ApplicationUserDTO> getCurrentByUsername(@PathVariable String username) {

		try {
			ApplicationUserDTO userDto = userService.getCurrentByUsername(username);
			return ResponseEntity.ok(userDto);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	// helper function to provide information whether user is allowed to make
	// appointment or not
	@PreAuthorize("hasAnyRole('USER')")
	@GetMapping(path = "evaluate-conditions/{username}")
	public ResponseEntity<ConditionsEvaluationDTO> evaluateConditions(@PathVariable String username) {
		ConditionsEvaluationDTO conditionsEvaluationDTO = userService.evaluateConditions(username);
		return ResponseEntity.ok(conditionsEvaluationDTO);
	}

	@GetMapping(path = "activate/{token}")
	public ResponseEntity<ActivationDTO> activate(@PathVariable String token) {
		ActivationDTO response = userService.activateUser(token);
		// success
		// fail-nonexistent
		// fail-repeating
		return ResponseEntity.ok(response);

	}

	@PostMapping("register")
	public ResponseEntity<String> registerUser(@RequestBody RegisterUserDTO user) {
		String response = userService.registerUser(user);
		return ResponseEntity.ok("{ \"response\" : \"" + response + "\" }");
	}

//	@PutMapping("penalty/{id}")
//	public void addPenalty(@RequestBody ApplicationUserDTO userDto) {
//		log.info("add penalty " + userDto);
//		userService.updateUser(userDto);
//	}

}
