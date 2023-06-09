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
public class QuestionnaireServiceImpl implements QuestionnaireService {

	private QuestionnaireRepository questionnaireRepository;
	private ApplicationUserRepository applicationUserRepository;

	public QuestionnaireServiceImpl(QuestionnaireRepository questionnaireRepository,
			ApplicationUserRepository applicationUserRepository) {
		super();
		this.questionnaireRepository = questionnaireRepository;
		this.applicationUserRepository = applicationUserRepository;
	}

	@Override
	public QuestionnaireDTO findByApplicationUsername(String username) {

		Optional<ApplicationUser> appUserOpt = applicationUserRepository.findOneByUsername(username);
		if (appUserOpt.isPresent()) {
			Optional<Questionnaire> questionnaireOpt = questionnaireRepository.findOneByApplicationUserId(appUserOpt.get().getId());
			if (questionnaireOpt.isPresent()) {
				return new QuestionnaireDTO(questionnaireOpt.get());
			}
		}
		return null;
	}

	@Override
	public String save(QuestionnaireDTO q) {
		Optional<ApplicationUser> applicationUser = applicationUserRepository.findOneByUsername(q.getAppUsername());
		if (applicationUser.isPresent()) {
			Optional<Questionnaire> questionnaireOpt = questionnaireRepository.findOneByApplicationUserId(applicationUser.get().getId());
			if (!questionnaireOpt.isPresent()) {

				Questionnaire questionnaire = new Questionnaire();
				questionnaire.setDate(LocalDate.now());
				questionnaire.setDonationNumber(q.getDonationCount());
				questionnaire.setRejected(q.isRejected());
				questionnaire.setHealthy(q.isHealthy());
				questionnaire.setDangerousOccupation(q.isDangerousOccupation());
				questionnaire.setInfectious(q.isInfectious());
				questionnaire.setBloodPressureIssues(q.isBloodPressureIssues());
				questionnaire.setOnTherapy(q.isOnTherapy());
				questionnaire.setAspirin(q.isAspirin());
				questionnaire.setTatooed(q.isTatooed());
				questionnaire.setApplicationUser(applicationUser.get());
				
				questionnaireRepository.save(questionnaire);
				return "Questionnaire successfully added.";
			} else {
				return "User already have questionnaire";
			}
		}
		return "No users found for current questionnaire";
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
