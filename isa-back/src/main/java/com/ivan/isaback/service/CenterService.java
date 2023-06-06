package com.ivan.isaback.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ivan.isaback.model.Center;
import com.ivan.isaback.model.dto.CenterDTO;

public interface CenterService {
	
	List<Center> findAll();
	Page<Center> findAllPageable(Pageable pageable);
	Center findById(int id);
	Center save(CenterDTO center);
	Center update(CenterDTO centerDTO);
	
}
