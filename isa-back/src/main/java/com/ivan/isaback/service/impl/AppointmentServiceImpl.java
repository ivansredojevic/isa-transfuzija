package com.ivan.isaback.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ivan.isaback.model.ApplicationUser;
import com.ivan.isaback.model.Appointment;
import com.ivan.isaback.model.dto.AppointmentDTO;
import com.ivan.isaback.model.dto.AppointmentResponseDTO;
import com.ivan.isaback.repository.ApplicationUserRepository;
import com.ivan.isaback.repository.AppointmentRepository;
import com.ivan.isaback.service.AppointmentService;
import com.ivan.isaback.util.email.EmailDetails;
import com.ivan.isaback.util.email.EmailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {
	
	private AppointmentRepository appointmentRepository;
	private ApplicationUserRepository applicationUserRepository;
	private EmailService emailService;
	
	public AppointmentServiceImpl(AppointmentRepository appointmentRepository, EmailService emailService) {
		super();
		this.appointmentRepository = appointmentRepository;
		this.emailService = emailService;
	}

	@Override
	public List<AppointmentResponseDTO> findAll() {
		List<Appointment> appointments = appointmentRepository.findAll();
		List<AppointmentResponseDTO> appointmentResponseDTOs = new ArrayList<>();
		for (Appointment a : appointments) {
			AppointmentResponseDTO arDto = new AppointmentResponseDTO(a);
			appointmentResponseDTOs.add(arDto);
		}
		return appointmentResponseDTOs;
		
	}

	@Override
	public List<Appointment> findByUserTaken(String username) {
		return appointmentRepository.findAllByApplicationUserUsernameAndTakenTrue(username);
	}

	@Override
	public List<Appointment> findByUserAndNotTaken(String username) {
		return appointmentRepository.findAllByApplicationUserUsernameAndTakenFalse(username);
	}

	@Override
	public AppointmentResponseDTO save(Appointment appointment) throws Exception {
		try {
			Appointment saved = appointmentRepository.save(appointment);
			return new AppointmentResponseDTO(saved);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new Exception("Error: Appointment not added.");
		}
	}

	@Override
	public AppointmentResponseDTO make(AppointmentDTO appointmentDTO) throws Exception {
		Optional<Appointment> appointmentOpt = appointmentRepository.findById(appointmentDTO.getId());
		
		if(appointmentOpt.isPresent()) {
			Appointment a = appointmentOpt.get();
			
			ApplicationUser applicationUser = applicationUserRepository.findByUsernameAndActivatedTrue(appointmentDTO.getUsername());
			
			a.setModifiedTime(LocalDateTime.now());
			a.setApplicationUser(applicationUser);
			a.setTaken(false);
			a.setApproved(true);
			log.info(a.toString());
		
			try {
				Appointment saved = appointmentRepository.save(a);
				
				EmailDetails emailDetails = new EmailDetails();
				emailDetails.setRecipient(saved.getApplicationUser().getEmail());
				emailDetails.setSubject("ISA Appointment mail");
				
				emailService.sendQrCode(emailDetails, a.getCenter().getAddress());
				
				return new AppointmentResponseDTO(saved);
			} catch (Exception e) {
				log.error(e.getMessage());
				throw new Exception("Error: Appointment not made.");
			}
		} else {
			log.error("No appointment found with ID = " + appointmentDTO.getId() + ".");
			return null;
		}
	}
	
	
	public AppointmentResponseDTO convertToDto(Appointment app){
	    return new AppointmentResponseDTO(app);
	}
	

	@Override
	public Page<AppointmentResponseDTO> findByUserTakenPageable(String username, Pageable pageable) {
		
		Page<Appointment> pageables = appointmentRepository.findAllByApplicationUserUsernameAndTakenTrue(username, pageable);
		Page<AppointmentResponseDTO> appointmentsPage = pageables.map(appoint -> convertToDto(appoint));
		return appointmentsPage;
	}

	@Override
	public Page<AppointmentResponseDTO> findByUserNotTakenPageable(String username, Pageable pageable) {
		Page<Appointment> pageables = appointmentRepository.findAllByApplicationUserUsernameAndTakenFalse(username, pageable);
		Page<AppointmentResponseDTO> appointmentsPage = pageables.map(appoint -> convertToDto(appoint));
		return appointmentsPage;
	}

	@Override
	public Page<AppointmentResponseDTO> findFreePageable(Pageable pageable) {
		Page<Appointment> pageables = appointmentRepository.findAllByApprovedFalse(pageable);
		Page<AppointmentResponseDTO> appointmentsPage = pageables.map(appoint -> convertToDto(appoint));
		return appointmentsPage;
	}
	
}
