package com.ivan.isaback.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ivan.isaback.model.Complaint;
import com.ivan.isaback.model.dto.ComplaintDTO;
import com.ivan.isaback.model.dto.InsertComplaintDTO;
import com.ivan.isaback.model.dto.UpdateComplaintDTO;

public interface ComplaintService {
		
	List<Complaint> findAll();
	Complaint save(InsertComplaintDTO comp);
	Complaint update(UpdateComplaintDTO comp);
//	List<Complaint> findByUserId(int userId);
	// for admin, to reply to complaint
	List<Complaint> findByUnanswered();
	
	// get by user id pageable
	// rest of the metods is not currently needed
	Page<ComplaintDTO> findByUserIdPageable(String username, Pageable pageable);
	
}
