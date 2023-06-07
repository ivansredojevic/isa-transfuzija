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
import com.ivan.isaback.model.dto.AppointmentResponseDTO;
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
	public ResponseEntity<List<AppointmentResponseDTO>> getAll(){
		List<AppointmentResponseDTO> appointments = appointmentService.findAll();
		if(!appointments.isEmpty()) {
			return ResponseEntity.ok(appointments);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	
//	@GetMapping(value = "history-by-user/{username}")
//	public ResponseEntity<List<Appointment>> getByUserTaken(@PathVariable String username){
//		List<Appointment> appointments = appointmentService.findByUserTaken(username);
//		if(!appointments.isEmpty()) {
//			return ResponseEntity.ok(appointments);
//		} else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//	}
//	
//	@GetMapping(value = "upcoming-by-user/{username}")
//	public ResponseEntity<List<Appointment>> getByUserNotTaken(@PathVariable String username){
//		List<Appointment> appointments = appointmentService.findByUserAndNotTaken(username);
//		if(!appointments.isEmpty()) {
//			return ResponseEntity.ok(appointments);
//		} else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//	}
	
	
	@GetMapping(value = "free-pageable")
	public Page<AppointmentResponseDTO> getFreePageable(Pageable pageable){
		return appointmentService.findFreePageable(pageable);
	}
	
	
	@GetMapping(value = "history-by-user-pageable/{username}")
	public Page<AppointmentResponseDTO> getByUserTaken(@PathVariable String username, Pageable pageable){
		return appointmentService.findByUserTakenPageable(username, pageable);
	}
	
	@GetMapping(value = "upcoming-by-user-pageable/{username}")
	public Page<AppointmentResponseDTO> getByUserNotTaken(@PathVariable String username, Pageable pageable){
		return appointmentService.findByUserNotTakenPageable(username, pageable);
	}
	
	
	
	@PostMapping(value = "add")
	public ResponseEntity<String> addAppointment(@RequestBody Appointment appointment){
		AppointmentResponseDTO appoint;
		try {
			appoint = appointmentService.save(appointment);
			return ResponseEntity.ok("Appointment added");
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@PostMapping(value = "make")
	public ResponseEntity<String> makeAppointment(@RequestBody AppointmentDTO dto){
		AppointmentResponseDTO appointment;
		try {
			appointment = appointmentService.make(dto);
			return ResponseEntity.ok("Appointment made for user: " + appointment.getUsername() + ".");
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.internalServerError().body(null);
		}
	}
	
	
	

}
