package com.ivan.isaback.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ivan.isaback.model.ApplicationUser;
import com.ivan.isaback.model.Questionnaire;
import com.ivan.isaback.model.dto.QuestionnaireDTO;
import com.ivan.isaback.repository.ApplicationUserRepository;
import com.ivan.isaback.repository.QuestionnaireRepository;
import com.ivan.isaback.service.QuestionnaireService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuestionnaireServiceImpl implements QuestionnaireService{
	
	private QuestionnaireRepository questionnaireRepository;
	private ApplicationUserRepository applicationUserRepository;
	
	public QuestionnaireServiceImpl(QuestionnaireRepository questionnaireRepository,
			ApplicationUserRepository applicationUserRepository) {
		super();
		this.questionnaireRepository = questionnaireRepository;
		this.applicationUserRepository = applicationUserRepository;
	}

	@Override
	public Questionnaire findByApplicationUserId(int id) {
		List<Questionnaire> questionnaire = questionnaireRepository.findByApplicationUserId(id);
		if(!questionnaire.isEmpty()) {
			log.info("Found " + questionnaire.size() + " Questionnaires for ApplicationUserId = " + id + ".");
			return questionnaire.get(0);
		}
		return null;
	}

	@Override
	public Questionnaire save(QuestionnaireDTO q) {
		
		Optional<ApplicationUser> applicationUser = applicationUserRepository.findOneById(q.getAppUserId());
		
		if(applicationUser.isPresent()) {
			
			Questionnaire questionnaire = new Questionnaire();
			questionnaire.setDate(LocalDate.now());
			questionnaire.setDonationNumber(q.getDonationNumber());
			questionnaire.setRejected(q.isRejected());
			questionnaire.setHealthy(q.isHealthy());
			questionnaire.setDangerousOccupation(q.isDangerousOccupation());
			questionnaire.setInfectious(q.isInfectious());
			questionnaire.setBloodPressureIssues(q.isBloodPressureIssues());
			questionnaire.setOnTherapy(q.isOnTherapy());
			questionnaire.setAspirin(q.isAspirin());
			questionnaire.setTatooed(q.isTatooed());
			questionnaire.setApplicationUser(applicationUser.get());
			
			log.info(q.toString());
			return questionnaireRepository.save(questionnaire);
		}
		log.error("No users found for current questionnaire");
		return null;
	}

	@Override
	public List<Questionnaire> findAll() {
		return questionnaireRepository.findAll();
	}

	@Override
	public void delete(int id) {
		questionnaireRepository.deleteById(id);
	}
	
}
