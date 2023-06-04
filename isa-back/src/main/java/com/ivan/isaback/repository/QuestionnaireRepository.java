package com.ivan.isaback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivan.isaback.model.Questionnaire;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Integer> {
	
	List<Questionnaire> findByApplicationUserId(int id);
	
}
