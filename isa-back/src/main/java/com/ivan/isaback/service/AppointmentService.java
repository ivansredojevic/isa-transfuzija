package com.ivan.isaback.service;

import java.util.List;

import com.ivan.isaback.model.Appointment;
import com.ivan.isaback.model.dto.AppointmentDTO;

public interface AppointmentService {
	
	List<Appointment> findAll();
	List<Appointment> findByUserId(int id);
	List<Appointment> findByUserIdTaken(int id);
	List<Appointment> findByUserIdAndNotTaken(int id);
	Appointment save(AppointmentDTO appointmentDTO);
	Appointment make(AppointmentDTO appointmentDTO);
	
}
