package com.ivan.isaback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.isaback.model.dto.QuestionnaireDTO;
import com.ivan.isaback.service.QuestionnaireService;

@RestController
@CrossOrigin
@RequestMapping("/api/questionnaire/")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class QuestionnaireController {

	private QuestionnaireService questionnaireService;

	public QuestionnaireController(QuestionnaireService questionnaireService) {
		super();
		this.questionnaireService = questionnaireService;
	}

	@PostMapping("fill-questionnaire")
	public ResponseEntity<String> fillQuestionnaire(@RequestBody QuestionnaireDTO q) {
		String response = questionnaireService.save(q);
		return ResponseEntity.ok("{ \"response\" : \"" + response + "\" }");
	}

	@GetMapping("get/{username}")
	public ResponseEntity<QuestionnaireDTO> getQuestionnaire(@PathVariable String username) {
		try {
			QuestionnaireDTO questionnaireDTO = questionnaireService.findByApplicationUsername(username);
			return ResponseEntity.ok(questionnaireDTO);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(null);
		}
	}

//	@GetMapping("get-all")
//	public ResponseEntity<List<Questionnaire>> getAll() {
//
//		List<Questionnaire> questionnaire = questionnaireService.findAll();
//		if (!questionnaire.isEmpty()) {
//			return ResponseEntity.ok(questionnaire);
//		} else {
//			log.error("No questionnaires found");
//			return ResponseEntity.internalServerError().body(null);
//		}
//	}

//	@DeleteMapping("delete/{id}")
//	public ResponseEntity<String> deleteQuestionnaire(@PathVariable int id) {
//
//		try {
//			questionnaireService.delete(id);
//			return ResponseEntity.ok("Questionnaire deleted");
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			log.error("Error deleting questionnaire.");
//			return ResponseEntity.internalServerError().body(e.getMessage());
//		}
//	}

}
