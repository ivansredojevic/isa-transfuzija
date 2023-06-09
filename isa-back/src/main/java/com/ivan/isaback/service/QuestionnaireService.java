package com.ivan.isaback.service;

import java.util.List;

import com.ivan.isaback.model.Questionnaire;
import com.ivan.isaback.model.dto.QuestionnaireDTO;

public interface QuestionnaireService {
	
	QuestionnaireDTO findByApplicationUsername(String username);
	String save(QuestionnaireDTO questionnaire);
	List<Questionnaire> findAll();
	void delete(int id);
	
}
