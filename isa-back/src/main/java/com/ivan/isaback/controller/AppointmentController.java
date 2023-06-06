package com.ivan.isaback.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.isaback.model.Appointment;
import com.ivan.isaback.model.Complaint;
import com.ivan.isaback.model.dto.AppointmentDTO;
import com.ivan.isaback.service.AppointmentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/appointment/")
public class AppointmentController {
	
	private AppointmentService appointmentService;

	public AppointmentController(AppointmentService appointmentService) {
		super();
		this.appointmentService = appointmentService;
	}
	
	@GetMapping(value = "all")
	public ResponseEntity<List<Appointment>> getAll(){
		List<Appointment> appointments = appointmentService.findAll();
		if(!appointments.isEmpty()) {
			return ResponseEntity.ok(appointments);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@GetMapping(value = "all-by-user/{id}")
	public ResponseEntity<List<Appointment>> getByUserId(@PathVariable int id){
		List<Appointment> appointments = appointmentService.findByUserId(id);
		if(!appointments.isEmpty()) {
			return ResponseEntity.ok(appointments);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@GetMapping(value = "history-by-user/{id}")
	public ResponseEntity<List<Appointment>> getByUserIdTaken(@PathVariable int id){
		List<Appointment> appointments = appointmentService.findByUserIdTaken(id);
		if(!appointments.isEmpty()) {
			return ResponseEntity.ok(appointments);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@GetMapping(value = "upcoming-by-user/{id}")
	public ResponseEntity<List<Appointment>> getByUserIdNotTaken(@PathVariable int id){
		List<Appointment> appointments = appointmentService.findByUserIdAndNotTaken(id);
		if(!appointments.isEmpty()) {
			return ResponseEntity.ok(appointments);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@GetMapping(value = "allPageable")
	public Page<Appointment> getAllPageable(Pageable pageable){
		return appointmentService.findAllPageable(pageable);
	}
	
	@GetMapping(value = "allByUserPageable/{id}")
	public Page<Appointment> getByUserId(@PathVariable int id, Pageable pageable){
		return appointmentService.findByUserIdPageable(id, pageable);
	}
	
	@GetMapping(value = "historyByUserPageable/{id}")
	public Page<Appointment> getByUserIdTaken(@PathVariable int id, Pageable pageable){
		return appointmentService.findByUserIdTakenPageable(id, pageable);
	}
	
	@GetMapping(value = "upcomingByUserPageable/{id}")
	public Page<Appointment> getByUserIdNotTaken(@PathVariable int id, Pageable pageable){
		return appointmentService.findByUserIdNotTakenPageable(id, pageable);
	}
	
	
	
	@PostMapping(value = "add")
	public ResponseEntity<Appointment> addAppointment(@RequestBody AppointmentDTO dto){
		Appointment appointment = appointmentService.save(dto);
		if(appointment != null) {
			return ResponseEntity.ok(appointment);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@PostMapping(value = "make")
	public ResponseEntity<Appointment> makeAppointment(@RequestBody AppointmentDTO dto){
		Appointment appointment = appointmentService.make(dto);
		if(appointment != null) {
			return ResponseEntity.ok(appointment);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	
	

}
