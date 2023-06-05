package com.ivan.isaback.service;

import java.util.List;

import com.ivan.isaback.model.Personnel;
import com.ivan.isaback.model.dto.PersonnelDTO;

public interface PersonnelService {
	
	Personnel findById(int id);
	Personnel findByUsername(String username);
	List<Personnel> findAll();
	Personnel save(PersonnelDTO personnel);
	
}
