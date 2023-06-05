package com.ivan.isaback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.isaback.model.Personnel;
import com.ivan.isaback.model.dto.PersonnelDTO;
import com.ivan.isaback.service.PersonnelService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/personnel/")
public class PersonnelController {
	
	private PersonnelService personnelService;

	public PersonnelController(PersonnelService personnelService) {
		super();
		this.personnelService = personnelService;
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN', 'PERSONNEL')")
	@GetMapping(value = "get-id/{id}")
	public ResponseEntity<Personnel> findById(@PathVariable int id) {
		
		Personnel personnel = personnelService.findById(id);
		if(personnel != null) {
			return ResponseEntity.ok(personnel);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN', 'PERSONNEL')")
	@GetMapping(value = "get-username/{username}")
	public ResponseEntity<Personnel> findByUsername(@PathVariable String username) {
		
		Personnel personnel = personnelService.findByUsername(username);
		if(personnel != null) {
			return ResponseEntity.ok(personnel);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PreAuthorize("hasAnyAuthority('ADMIN', 'PERSONNEL')")
	@GetMapping(value = "get-all")
	public ResponseEntity<List<Personnel>> findAll() {
		
		List<Personnel> personnel = personnelService.findAll();
		if(!personnel.isEmpty()) {
			return ResponseEntity.ok(personnel);
		} else {
			log.error("No personnell found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@PostMapping(value = "/save")
	public ResponseEntity<Personnel> savePersonnel(@RequestBody PersonnelDTO p) {
		
		try {
			Personnel personnel = personnelService.save(p);
			return ResponseEntity.ok(personnel);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.internalServerError().body(null);
		}
	}
	
}
