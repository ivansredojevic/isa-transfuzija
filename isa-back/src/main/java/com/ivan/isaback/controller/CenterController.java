package com.ivan.isaback.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.isaback.model.Center;
import com.ivan.isaback.service.CenterService;

@RestController
@CrossOrigin
@RequestMapping("/api/center/")
public class CenterController {
	
	private CenterService centerService;
	
	public CenterController(CenterService centerService) {
		super();
		this.centerService = centerService;
	}

	@GetMapping(value = "all")
	public ResponseEntity<List<Center>> getAll(){
		List<Center> centers = centerService.findAll();
		if(!centers.isEmpty()) {
			return ResponseEntity.ok(centers);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	
	@GetMapping(value = "all-pageable")
	public Page<Center> getAllPageable(Pageable pageable){
//		log.info(pageable.toString());
		return centerService.findAllPageable(pageable);
	}
	
//	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//	@GetMapping(value = "{centerId}")
//	public ResponseEntity<Center> getById(@PathVariable int centerId){
//		Center center = centerService.findById(centerId);
//		if(center != null) {
//			return ResponseEntity.ok(center);
//		} else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//	}
//	
//	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//	@PostMapping(value = "add")
//	public ResponseEntity<Center> addCenter(@RequestBody CenterDTO centerDTO){
//		Center center = centerService.save(centerDTO);
//		if(center != null) {
//			return ResponseEntity.ok(center);
//		} else {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//		}
//	}
//	
//	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//	@PostMapping(value = "update")
//	public ResponseEntity<Center> updateCenter(@RequestBody CenterDTO centerDTO){
//		Center center = centerService.update(centerDTO);
//		if(center != null) {
//			return ResponseEntity.ok(center);
//		} else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//	}
	
	
	
}
