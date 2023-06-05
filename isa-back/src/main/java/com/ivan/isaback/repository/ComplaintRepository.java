package com.ivan.isaback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivan.isaback.model.Complaint;

public interface ComplaintRepository extends JpaRepository<com.ivan.isaback.model.Complaint, Integer> {
	
	List<Complaint> findAllByApplicationUserId(int id);
	List<Complaint> findAllByAdminIdIsNullAndReplyTextIsNull();
	
}
