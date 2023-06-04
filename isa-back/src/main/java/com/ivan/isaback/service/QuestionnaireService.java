package com.ivan.isaback.service;

import java.util.List;

import com.ivan.isaback.model.Questionnaire;
import com.ivan.isaback.model.dto.QuestionnaireDTO;

public interface QuestionnaireService {
	
	Questionnaire findByApplicationUserId(int id);
	Questionnaire save(QuestionnaireDTO questionnaire);
	List<Questionnaire> findAll();
	
}
