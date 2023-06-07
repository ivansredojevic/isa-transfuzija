package com.ivan.isaback.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ivan.isaback.model.Center;
import com.ivan.isaback.model.dto.CenterDTO;
import com.ivan.isaback.repository.CenterRepository;
import com.ivan.isaback.repository.specification.FilterSpecification;
import com.ivan.isaback.service.CenterService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CenterServiceImpl implements CenterService {
	
	private CenterRepository centerRepository;
	
	public CenterServiceImpl(CenterRepository centerRepository) {
		super();
		this.centerRepository = centerRepository;
	}

	@Override
	public List<Center> findAll() {
		return centerRepository.findAll();
	}

	@Override
	public Center findById(int id) {
		Optional<Center> centerOpt = centerRepository.findById(id);
		if(centerOpt.isPresent()) {
			return centerOpt.get();
		} else {
			log.error("No centers found for ID = " + id + ".");
			return null;
		}
	}

	@Override
	public Center save(CenterDTO center) {
		Center c = new Center();
		
		c.setCenterName(center.getCenterName());
		c.setAddress(center.getAddress());
		c.setRating(center.getRating());
		c.setOpenTime(center.getOpenTime());
		c.setClosedTime(center.getClosedTime());
		
		log.info(c.toString());
		try {
			Center saved = centerRepository.save(c);
			return saved;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		
	}

	@Override
	public Center update(CenterDTO center) {
		
		Optional<Center> centerOpt = centerRepository.findById(center.getId());
		
		if(centerOpt.isPresent()) {
			
			Center c = centerOpt.get();
			
			c.setCenterName(center.getCenterName());
			c.setAddress(center.getAddress());
			c.setRating(center.getRating());
			c.setOpenTime(center.getOpenTime());
			c.setClosedTime(center.getClosedTime());
			
			log.info(c.toString());
			try {
				Center saved = centerRepository.save(c);
				return saved;
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			log.error("No center found with ID = " + center.getId() + ".");
			return null;
		}
		
	}

	@Override
	public Page<Center> findAllPageable(Pageable pageable) {
		return centerRepository.findAll(pageable);
	}

}
