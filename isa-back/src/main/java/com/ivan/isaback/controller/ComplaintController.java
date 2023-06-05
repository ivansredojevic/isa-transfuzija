package com.ivan.isaback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.isaback.model.Complaint;
import com.ivan.isaback.model.dto.ComplaintDTO;
import com.ivan.isaback.service.ComplaintService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/complaint/")
public class ComplaintController {
	
	private ComplaintService complaintService;

	public ComplaintController(ComplaintService complaintService) {
		super();
		this.complaintService = complaintService;
	}
	
//	List<Complaint> findAll();
//	Complaint save(ComplaintDTO complaintDTO);
//	Complaint update(ComplaintDTO complaintDTO);
//	List<Complaint> findByUsername(int userId);
//	// for admin, to reply to complaint
//	List<Complaint> findByUnanswered();
	
	@GetMapping(value = "all")
	public ResponseEntity<List<Complaint>> getAll(){
		List<Complaint> complaints = complaintService.findAll();
		if(!complaints.isEmpty()) {
			return ResponseEntity.ok(complaints);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@GetMapping(value = "{userId}")
	public ResponseEntity<List<Complaint>> getByUser(@PathVariable int userId){
		List<Complaint> complaints = complaintService.findByUserId(userId);
		if(!complaints.isEmpty()) {
			return ResponseEntity.ok(complaints);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PostMapping(value = "add")
	public ResponseEntity<Complaint> addComplaint(@RequestBody ComplaintDTO complaintDTO){
		Complaint complaint = complaintService.save(complaintDTO);
		if(complaint != null) {
			return ResponseEntity.ok(complaint);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PostMapping(value = "update")
	public ResponseEntity<Complaint> updateComplaint(@RequestBody ComplaintDTO complaintDTO){
		Complaint complaint = complaintService.update(complaintDTO);
		if(complaint != null) {
			return ResponseEntity.ok(complaint);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	
}
