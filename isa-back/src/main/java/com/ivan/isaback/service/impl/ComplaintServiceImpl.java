package com.ivan.isaback.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ivan.isaback.model.ApplicationUser;
import com.ivan.isaback.model.Appointment;
import com.ivan.isaback.model.Center;
import com.ivan.isaback.model.Complaint;
import com.ivan.isaback.model.Personnel;
import com.ivan.isaback.model.dto.ComplaintDTO;
import com.ivan.isaback.model.dto.InsertComplaintDTO;
import com.ivan.isaback.model.dto.UpdateComplaintDTO;
import com.ivan.isaback.repository.ApplicationUserRepository;
import com.ivan.isaback.repository.AppointmentRepository;
import com.ivan.isaback.repository.CenterRepository;
import com.ivan.isaback.repository.ComplaintRepository;
import com.ivan.isaback.repository.PersonnelRepository;
import com.ivan.isaback.service.ComplaintService;
import com.ivan.isaback.util.email.EmailDetails;
import com.ivan.isaback.util.email.EmailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ComplaintServiceImpl implements ComplaintService {
	
	private ComplaintRepository complaintRepository;
	private CenterRepository centerRepository;
	private PersonnelRepository personnelRepository;
	private ApplicationUserRepository applicationUserRepository;
	private AppointmentRepository appointmentRepository;
	private EmailService emailService;
	
	public ComplaintServiceImpl(ComplaintRepository complaintRepository, CenterRepository centerRepository,
			PersonnelRepository personnelRepository, ApplicationUserRepository applicationUserRepository,
			AppointmentRepository appointmentRepository, EmailService emailService) {
		super();
		this.complaintRepository = complaintRepository;
		this.centerRepository = centerRepository;
		this.personnelRepository = personnelRepository;
		this.applicationUserRepository = applicationUserRepository;
		this.appointmentRepository = appointmentRepository;
		this.emailService = emailService;
	}

	@Override
	public List<Complaint> findAll() {
		return complaintRepository.findAll();
	}
	
	@Override
	public Complaint save(InsertComplaintDTO comp) {
		
		log.info(comp.toString());

		Optional<ApplicationUser> appUser = applicationUserRepository.findOneByUsername(comp.getUsername());
		if (appUser.isPresent()) {
			Optional<Appointment> apt = appointmentRepository.findById(comp.getAppointmentId());
			if(!apt.isPresent()) {
				log.error("Appointment doesn't exist.");
				return null;
			}
			Complaint complaint = new Complaint();
			complaint.setAdmin(null);
			complaint.setApplicationUser(appUser.get());
			complaint.setAppointment(apt.get());
			complaint.setComplaintText(comp.getComplaintText());
			complaint.setReplyText(null);

			if (comp.getCenterId() != 0) {
				Optional<Center> cent = centerRepository.findById(comp.getCenterId());
				if(!cent.isPresent()) {
					log.error("Center not found.");
					return null;
				}
				complaint.setCenter(cent.get());
				apt.get().setComplainCenter(true);
			} else if (comp.getPersonnelId() != 0) {
				Optional<Personnel> pers = personnelRepository.findById(comp.getPersonnelId());
				if(!pers.isPresent()) {
					log.error("Doctor not found");
					return null;
				}
				complaint.setPersonnelUser(pers.get());
				apt.get().setComplainPers(true);
			}
			try {
				Complaint saved = complaintRepository.save(complaint);
				appointmentRepository.save(apt.get());
				return saved;
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			log.error("Error while creating complaint" );
			return null;
		}
	}

	@Override
	public Complaint update(UpdateComplaintDTO comp) {
		
		Optional<Complaint> complaintOpt = complaintRepository.findById(comp.getId());
		if(complaintOpt.isPresent()) {
			Complaint c = complaintOpt.get();
			
			if(c.getAdmin() != null) {
				log.error("Already sent response.");
				return null;
			}
			
			
			Optional<ApplicationUser> adminOpt = applicationUserRepository.findOneById(comp.getAdminId());
			if(!adminOpt.isPresent()) {
				log.error("No admin found");
				return null;
			}
			if(comp.getReplyText().isEmpty()) {
				log.error("invalid sender or reply");
				return null;
			}
			
			c.setReplyText(comp.getReplyText());
			c.setAdmin(adminOpt.get());
			
			String email = c.getApplicationUser().getEmail();
			log.info(email);
			
			try {
				Complaint saved = complaintRepository.save(c);
				
				EmailDetails emailDetails = new EmailDetails();
				emailDetails.setRecipient(email);
				emailDetails.setSubject("ISA Complaint report mail");
				emailDetails.setMsgBody("Reply for complaint ID=" + saved.getId() + ".  Reply text: \"" + saved.getReplyText() + "\", Admin ID=" + comp.getAdminId());
				log.info(emailDetails.getMsgBody());
				
				emailService.sendLink(emailDetails);

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
