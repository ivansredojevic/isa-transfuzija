package com.ivan.isaback.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ivan.isaback.model.Complaint;
import com.ivan.isaback.model.dto.ComplaintDTO;

public interface ComplaintService {
		
	List<Complaint> findAll();
	Complaint save(ComplaintDTO complaintDTO);
	Complaint update(ComplaintDTO complaintDTO);
	List<Complaint> findByUserId(int userId);
	// for admin, to reply to complaint
	List<Complaint> findByUnanswered();
	
	// get by user id pageable
	// rest of the metods is not currently needed
	Page<Complaint> findByUserIdPageable(int userId, Pageable pageable);
	
}
