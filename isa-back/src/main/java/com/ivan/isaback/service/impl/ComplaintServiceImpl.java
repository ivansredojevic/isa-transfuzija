package com.ivan.isaback.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ivan.isaback.model.Appointment;
import com.ivan.isaback.model.Complaint;
import com.ivan.isaback.model.dto.AppointmentResponseDTO;
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
	
	public ComplaintServiceImpl(ComplaintRepository complaintRepository) {
		super();
		this.complaintRepository = complaintRepository;
	}

	@Override
	public List<Complaint> findAll() {
		return complaintRepository.findAll();
	}
	
	@Override
	public Complaint save(Complaint comp) {
		
		log.info(comp.toString());
		
		try {
			Complaint saved = complaintRepository.save(comp);
			return saved;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Complaint update(Complaint comp) {
		
		Optional<Complaint> complaintOpt = complaintRepository.findById(comp.getId());
		if(complaintOpt.isPresent()) {
			Complaint c = complaintOpt.get();
			// update all params
			c.setComplaintText(comp.getComplaintText());
			c.setReplyText(comp.getReplyText());
			c.setAdmin(comp.getAdmin());
			c.setApplicationUser(comp.getApplicationUser());
			c.setAppointment(comp.getAppointment());
			c.setCenter(comp.getCenter());
			c.setPersonnelUser(comp.getPersonnelUser());
			
			log.info(c.toString());
			try {
				Complaint saved = complaintRepository.save(c);
				return saved;
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			log.error("No complaint found with ID = " + comp.getId() + ".");
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
	
	public ComplaintDTO convertToDto(Complaint c){
	    return new ComplaintDTO(c);
	}
	
	@Override
	public Page<ComplaintDTO> findByUserIdPageable(String username, Pageable pageable) {
		Page<Complaint> pageables = complaintRepository.findAllByApplicationUserUsername(username, pageable);
		Page<ComplaintDTO> complaintsPage = pageables.map(complaint -> convertToDto(complaint));
		return complaintsPage; 
	}
	
}
