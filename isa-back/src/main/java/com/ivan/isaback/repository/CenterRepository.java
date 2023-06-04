package com.ivan.isaback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivan.isaback.model.Center;

public interface CenterRepository extends JpaRepository<Center, Integer>{
	
	List<Center> findAll();
	
}


//@Modifying
//@Query("update OutboundMessage om set om.smscMessageId = ?2, om.status = ?3 where om.messageId = ?1")
//int updateAcknowledge(String messageId, String smscMessageId, String status);
//