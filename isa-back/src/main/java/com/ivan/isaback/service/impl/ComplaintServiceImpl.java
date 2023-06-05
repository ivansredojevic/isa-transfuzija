package com.ivan.isaback.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ivan.isaback.model.Complaint;
import com.ivan.isaback.model.dto.ComplaintDTO;
import com.ivan.isaback.repository.ApplicationUserRepository;
import com.ivan.isaback.repository.AppointmentRepository;
import com.ivan.isaback.repository.CenterRepository;
import com.ivan.isaback.repository.ComplaintRepository;
import com.ivan.isaback.repository.PersonnelRepository;
import com.ivan.isaback.service.ComplaintService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ComplaintServiceImpl implements ComplaintService {
	
	private ComplaintRepository complaintRepository;
	private CenterRepository centerRepository;
	private ApplicationUserRepository applicationUserRepository;
	private PersonnelRepository personnelRepository;
	private AppointmentRepository appointmentRepository;
	
	public ComplaintServiceImpl(ComplaintRepository complaintRepository, CenterRepository centerRepository,
			ApplicationUserRepository applicationUserRepository, PersonnelRepository personnelRepository,
			AppointmentRepository appointmentRepository) {
		super();
		this.complaintRepository = complaintRepository;
		this.centerRepository = centerRepository;
		this.applicationUserRepository = applicationUserRepository;
		this.personnelRepository = personnelRepository;
		this.appointmentRepository = appointmentRepository;
	}

	@Override
	public List<Complaint> findAll() {
		return complaintRepository.findAll();
	}
	
	@Override
	public Complaint save(ComplaintDTO complaintDTO) {
		
		Complaint c = new Complaint();
		
		c.setComplaintText(complaintDTO.getComplaintText());
		c.setReplyText(complaintDTO.getReplyText());
		c.setAdmin(complaintDTO.getAdmin());
		c.setApplicationUser(complaintDTO.getApplicationUser());
		c.setAppointment(complaintDTO.getAppointment());
		c.setCenter(complaintDTO.getCenter());
		c.setPersonnelUser(complaintDTO.getPersonnelUser());
		
		log.info(c.toString());
		try {
			Complaint saved = complaintRepository.save(c);
			return saved;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Complaint update(ComplaintDTO complaintDTO) {
		
		Optional<Complaint> complaintOpt = complaintRepository.findById(complaintDTO.getId());
		if(complaintOpt.isPresent()) {
			Complaint c = complaintOpt.get();
			// update all params
			c.setComplaintText(complaintDTO.getComplaintText());
			c.setReplyText(complaintDTO.getReplyText());
			c.setAdmin(complaintDTO.getAdmin());
			c.setApplicationUser(complaintDTO.getApplicationUser());
			c.setAppointment(complaintDTO.getAppointment());
			c.setCenter(complaintDTO.getCenter());
			c.setPersonnelUser(complaintDTO.getPersonnelUser());
			
			log.info(c.toString());
			try {
				Complaint saved = complaintRepository.save(c);
				return saved;
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			log.error("No complaint found with ID = " + complaintDTO.getId() + ".");
			return null;
		}
		
	}

	@Override
	public List<Complaint> findByUserId(int userId) {
		return complaintRepository.findAllByApplicationUserId(userId);
	}

	@Override
	public List<Complaint> findByUnanswered() {
		return complaintRepository.findAllByAdminIdIsNullAndReplyTextIsNull();
	}

}
