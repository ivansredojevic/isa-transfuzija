package com.ivan.isaback.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.isaback.service.QuestionnaireService;
import com.ivan.isaback.model.Questionnaire;
import com.ivan.isaback.model.dto.QuestionnaireDTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/questionnaire/")
public class QuestionnaireController {

	private QuestionnaireService questionnaireService;
	
	public QuestionnaireController(QuestionnaireService questionnaireService) {
		super();
		this.questionnaireService = questionnaireService;
	}

	
	@PostMapping("fill-questionnaire")
	public ResponseEntity<Questionnaire> fillQuestionnaire(@RequestBody QuestionnaireDTO q) {
		
		try {
			Questionnaire questionnaire = questionnaireService.save(q);
			return ResponseEntity.ok(questionnaire);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.internalServerError().body(null);
		}	
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<Questionnaire> getQuestionnaire(@PathVariable int id) {
		
		Questionnaire questionnaire = questionnaireService.findByApplicationUserId(id);
		if(questionnaire != null) {
			return ResponseEntity.ok(questionnaire);
		} else {
			log.error("No questionnaire found");
			return ResponseEntity.internalServerError().body(null);
		}
		
	}
	
	@GetMapping("get-all")
	public ResponseEntity<List<Questionnaire>> getAll() {
		
		List<Questionnaire> questionnaire = questionnaireService.findAll();
		if(!questionnaire.isEmpty()) {
			return ResponseEntity.ok(questionnaire);
		} else {
			log.error("No questionnaires found");
			return ResponseEntity.internalServerError().body(null);
		}
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteQuestionnaire(@PathVariable int id) {
		
		try {
			questionnaireService.delete(id);
			return ResponseEntity.ok("Questionnaire deleted");
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error("Error deleting questionnaire.");
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	
}
