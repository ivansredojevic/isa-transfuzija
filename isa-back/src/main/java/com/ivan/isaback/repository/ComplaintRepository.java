package com.ivan.isaback.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ivan.isaback.model.Complaint;

public interface ComplaintRepository extends JpaRepository<com.ivan.isaback.model.Complaint, Integer> {
	
	List<Complaint> findAllByApplicationUserId(int id);
	List<Complaint> findAllByAdminIdIsNullAndReplyTextIsNull();
	
	Page<Complaint> findAllByApplicationUserId(int id, Pageable pageable);
	Page<Complaint> findAllByAdminIdIsNullAndReplyTextIsNull(Pageable pageable);
	
}
