package com.ivan.isaback.controller;

import java.util.List;

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

import com.ivan.isaback.model.Complaint;
import com.ivan.isaback.model.dto.ComplaintDTO;
import com.ivan.isaback.model.dto.InsertComplaintDTO;
import com.ivan.isaback.model.dto.UpdateComplaintDTO;
import com.ivan.isaback.service.ComplaintService;

@RestController
@CrossOrigin
@RequestMapping("/api/complaint/")
public class ComplaintController {
	
	private ComplaintService complaintService;
	
	public ComplaintController(ComplaintService complaintService) {
		super();
		this.complaintService = complaintService;
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping(value = "all")
	public ResponseEntity<List<Complaint>> getAll(){
		List<Complaint> complaints = complaintService.findAll();
		if(!complaints.isEmpty()) {
			return ResponseEntity.ok(complaints);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping(value = "user-pageable/{username}")
	public Page<ComplaintDTO> getByUser(@PathVariable String username, Pageable pageable){
		return complaintService.findByUserIdPageable(username, pageable);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@PostMapping(value = "add")
	public ResponseEntity<Complaint> addComplaint(@RequestBody InsertComplaintDTO complaintDTO){
		Complaint complaint = complaintService.save(complaintDTO);
		if(complaint != null) {
			return ResponseEntity.ok(complaint);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping(value = "update")
	public ResponseEntity<Complaint> updateComplaint(@RequestBody UpdateComplaintDTO complaintDTO){
		Complaint complaint = complaintService.update(complaintDTO);
		if(complaint != null) {
			return ResponseEntity.ok(complaint);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	
}
