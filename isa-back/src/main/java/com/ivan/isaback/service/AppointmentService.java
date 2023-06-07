package com.ivan.isaback.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ivan.isaback.model.Appointment;
import com.ivan.isaback.model.dto.AppointmentDTO;
import com.ivan.isaback.model.dto.AppointmentItemDTO;

public interface AppointmentService {
	
	List<AppointmentItemDTO> findAll();
	
	List<Appointment> findByUserTaken(String username);
	List<Appointment> findByUserAndNotTaken(String username);
	AppointmentItemDTO save(Appointment appointment) throws Exception;
	AppointmentItemDTO make(AppointmentDTO appointmentDTO) throws Exception;

	String cancel(AppointmentDTO appointmentDTO) throws Exception;
	
	Page<AppointmentItemDTO> findFreePageable(Pageable pageable);
	Page<AppointmentItemDTO> findByUserTakenPageable(String username, Pageable pageable);
	Page<AppointmentItemDTO> findByUserNotTakenPageable(String username, Pageable pageable);
	
	
	
}
