package com.ivan.isaback.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ivan.isaback.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
	
	List<Appointment> findAll();
	List<Appointment> findAllByCenterId(int centerId);
	
	List<Appointment> findAllByApplicationUserUsername(String username);
	List<Appointment> findAllByApplicationUserUsernameAndTakenFalse(String username);
	List<Appointment> findAllByApplicationUserUsernameAndTakenTrue(String username);
	
	Page<Appointment> findAll(Pageable pageable);
	Page<Appointment> findAllByApprovedFalse(Pageable pageable);
	Page<Appointment> findAllByCenterId(int centerId, Pageable pageable);
	Page<Appointment> findAllByApplicationUserUsername(String username, Pageable pageable);
	Page<Appointment> findAllByApplicationUserUsernameAndTakenFalse(String username, Pageable pageable);
	Page<Appointment> findAllByApplicationUserUsernameAndTakenTrue(String username, Pageable pageable);
}
