package com.ivan.isaback.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivan.isaback.model.Questionnaire;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Integer> {
	
	List<Questionnaire> findByApplicationUserId(int id);
	Optional<Questionnaire> findOneByApplicationUserId(int id);
	
}
