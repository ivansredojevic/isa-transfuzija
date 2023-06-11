package com.ivan.isaback.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.isaback.model.Appointment;
import com.ivan.isaback.model.dto.AppointmentDTO;
import com.ivan.isaback.model.dto.AppointmentItemDTO;
import com.ivan.isaback.model.dto.AppointmentItemResponseDTO;
import com.ivan.isaback.service.AppointmentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/appointment/")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class AppointmentController {
	
	private AppointmentService appointmentService;

	public AppointmentController(AppointmentService appointmentService) {
		super();
		this.appointmentService = appointmentService;
	}
	
//	@GetMapping(value = "all")
//	public ResponseEntity<List<AppointmentItemDTO>> getAll(){
//		List<AppointmentItemDTO> appointments = appointmentService.findAll();
//		if(!appointments.isEmpty()) {
//			return ResponseEntity.ok(appointments);
//		} else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//	}
	
	
//	@GetMapping(value = "history-by-user/{username}")
//	public ResponseEntity<List<Appointment>> getByUserTaken(@PathVariable String username){
//		List<Appointment> appointments = appointmentService.findByUserTaken(username);
//		if(!appointments.isEmpty()) {
//			return ResponseEntity.ok(appointments);
//		} else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//	}
	

	@GetMapping(value = "free-pageable/{username}")
	public Page<AppointmentItemDTO> getFreePageable(@PathVariable String username, Pageable pageable){
		return appointmentService.findFreePageable(username, pageable);
	}
	
	@GetMapping(value = "get/{id}")
	public ResponseEntity<AppointmentItemDTO> getOne(@PathVariable int id){
		AppointmentItemDTO appointmentItemDTO;
		try {
			appointmentItemDTO = appointmentService.findOne(id);
			return ResponseEntity.ok(appointmentItemDTO);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@GetMapping(value = "history-by-user-pageable/{username}")
	public Page<AppointmentItemDTO> getByUserTaken(@PathVariable String username, Pageable pageable){
		return appointmentService.findByUserTakenPageable(username, pageable);
	}
	
	@GetMapping(value = "upcoming-by-user-pageable/{username}")
	public Page<AppointmentItemDTO> getByUserNotTaken(@PathVariable String username, Pageable pageable){
		return appointmentService.findByUserNotTakenPageable(username, pageable);
	}
	
	@PostMapping(value = "add")
	public ResponseEntity<String> addAppointment(@RequestBody Appointment appointment){
		
		try {
			appointmentService.save(appointment);
			return ResponseEntity.ok("Appointment added");
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@PostMapping(value = "reserve")
	public ResponseEntity<AppointmentItemResponseDTO> reserveAppointment(@RequestBody AppointmentDTO dto){
		AppointmentItemResponseDTO appointment;
		try {
			appointment = appointmentService.make(dto);
			return ResponseEntity.ok(appointment);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return ResponseEntity.internalServerError().body(null);
		}
	}
	
	@PostMapping(value = "cancel")
	public ResponseEntity<AppointmentDTO> cancelAppointment(@RequestBody AppointmentDTO dto){
		try {
			AppointmentDTO response = appointmentService.cancel(dto);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.internalServerError().body(null);
		}
	}

}
