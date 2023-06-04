package com.ivan.isaback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivan.isaback.model.Questionnaire;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Integer> {

}
