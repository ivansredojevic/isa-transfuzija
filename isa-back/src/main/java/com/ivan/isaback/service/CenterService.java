package com.ivan.isaback.service;

import java.util.List;

import com.ivan.isaback.model.Center;
import com.ivan.isaback.model.dto.CenterDTO;

public interface CenterService {
	
	List<Center> findAll();
	Center findById(int id);
	Center save(CenterDTO center);
	Center update(CenterDTO centerDTO);
	
}
