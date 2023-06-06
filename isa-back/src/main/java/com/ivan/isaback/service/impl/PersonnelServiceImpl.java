package com.ivan.isaback.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ivan.isaback.model.Personnel;
import com.ivan.isaback.model.dto.PersonnelDTO;
import com.ivan.isaback.repository.PersonnelRepository;
import com.ivan.isaback.service.PersonnelService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersonnelServiceImpl implements PersonnelService {
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	private PersonnelRepository personnelRepository;
	
	public PersonnelServiceImpl(PersonnelRepository personnelRepository) {
		super();
		this.personnelRepository = personnelRepository;
	}
	
	@Override
	public Personnel findById(int id) {
		Optional<Personnel> personnel = personnelRepository.findById(id);
		if(personnel.isPresent()) {
			return personnel.get();
		} else {
			log.error("No personnel found for id = " + id + ".");
			return null;
		}
	}

	@Override
	public Personnel findByUsername(String username) {
		Optional<Personnel> personnel = personnelRepository.findByUsername(username);
		if(personnel.isPresent()) {
			return personnel.get();
		} else {
			log.error("No personnel found for username = " + username + ".");
			return null;
		}
	}

	@Override
	public List<Personnel> findAll() {
		
		List<Personnel> personnel = personnelRepository.findAll();
		if(!personnel.isEmpty()) {
			log.info("Found " + personnel.size() + " Personnel.");
			return personnel;
		} else {
			log.error("No personnel found.");
			return null;
		}
	}

	@Override
	public Personnel save(PersonnelDTO p) {
		
		Optional<Personnel> personnelOpt = personnelRepository.findByUsername(p.getUsername());
		if(!personnelOpt.isPresent()) {
			Personnel personnel = new Personnel();
			personnel.setEmail(p.getEmail());
			personnel.setUsername(p.getUsername());
			personnel.setPassword(bCryptPasswordEncoder.encode(p.getPassword()));
			personnel.setName(p.getName());
			personnel.setSurname(p.getSurname());
			personnel.setAddress(p.getAddress());
			personnel.setPhone(p.getPhone());
			personnel.setJmbg(p.getJmbg());
			personnel.setSex(p.getSex());
			personnel.setOccupation(p.getOccupation());
			personnel.setJobinformation(p.getJobinformation());
			personnel.setActivated(true);
			personnel.setRole("ROLE_PERSONNEL");
			personnel.setCenter(p.getCenter());
			
			log.info(personnel.toString());
			return personnelRepository.save(personnel);
		}
		log.error("Error while inserting personnel.");
		return null;
	}

}
