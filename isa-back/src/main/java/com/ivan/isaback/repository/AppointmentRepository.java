package com.ivan.isaback.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ivan.isaback.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
	
	List<Appointment> findAll();
	List<Appointment> findAllByCenterId(int centerId);
	//https://stackoverflow.com/questions/19733464/order-by-date-asc-with-spring-data
	List<Appointment> findAllByOrderByStartTimeAsc();
	List<Appointment> findAllByApplicationUserIdOrderByStartTimeDesc(int applicationUserId);
	List<Appointment> findAllByApplicationUserId(int applicationUserId);
	List<Appointment> findAllByApplicationUserIdAndTakenFalse(int applicationUserId);
	List<Appointment> findAllByApplicationUserIdAndTakenTrue(int applicationUserId);
	
	Page<Appointment> findAll(Pageable pageable);
	
}
