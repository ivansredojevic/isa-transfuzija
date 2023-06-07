package com.ivan.isaback.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ivan.isaback.model.Appointment;
import com.ivan.isaback.model.dto.AppointmentDTO;
import com.ivan.isaback.model.dto.AppointmentResponseDTO;

public interface AppointmentService {
	
	List<AppointmentResponseDTO> findAll();
	
	List<Appointment> findByUserTaken(String username);
	List<Appointment> findByUserAndNotTaken(String username);
	AppointmentResponseDTO save(Appointment appointment) throws Exception;
	AppointmentResponseDTO make(AppointmentDTO appointmentDTO) throws Exception;;
	
	Page<AppointmentResponseDTO> findFreePageable(Pageable pageable);
	Page<AppointmentResponseDTO> findByUserTakenPageable(String username, Pageable pageable);
	Page<AppointmentResponseDTO> findByUserNotTakenPageable(String username, Pageable pageable);
	
	
	
}
