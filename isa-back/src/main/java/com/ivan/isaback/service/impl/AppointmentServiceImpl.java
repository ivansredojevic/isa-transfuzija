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
import com.ivan.isaback.model.dto.AppointmentItemDTO;
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

	public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
			ApplicationUserRepository applicationUserRepository, EmailService emailService) {
		super();
		this.appointmentRepository = appointmentRepository;
		this.applicationUserRepository = applicationUserRepository;
		this.emailService = emailService;
	}

	@Override
	public List<AppointmentItemDTO> findAll() {
		List<Appointment> appointments = appointmentRepository.findAll();
		List<AppointmentItemDTO> appointmentResponseDTOs = new ArrayList<>();
		for (Appointment a : appointments) {
			AppointmentItemDTO arDto = new AppointmentItemDTO(a);
			appointmentResponseDTOs.add(arDto);
		}
		return appointmentResponseDTOs;
		
	}
	
	@Override
	public AppointmentItemDTO findOne(int id) {
		Optional<Appointment> appointmentOpt = appointmentRepository.findById(id);
		if(appointmentOpt.isPresent()) {
			return new AppointmentItemDTO(appointmentOpt.get());
		} else {
			log.error("Appointment not found.");
			return null;
		}
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
	public AppointmentItemDTO save(Appointment appointment) throws Exception {
		try {
			Appointment saved = appointmentRepository.save(appointment);
			return new AppointmentItemDTO(saved);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new Exception("Error: Appointment not added.");
		}
	}

	@Override
	public AppointmentItemDTO make(AppointmentDTO appointmentDTO) throws Exception {
		Optional<Appointment> appointmentOpt = appointmentRepository.findById(appointmentDTO.getId());
		
		if(appointmentOpt.isPresent()) {
			Appointment a = appointmentOpt.get();
			
			ApplicationUser applicationUser = applicationUserRepository.findByUsernameAndActivatedTrue(appointmentDTO.getUsername());
			if (applicationUser != null) {
				a.setModifiedTime(LocalDateTime.now());
				a.setApplicationUser(applicationUser);
				a.setTaken(false);
				a.setApproved(true);
			}

			try {
				Appointment saved = appointmentRepository.save(a);
				log.info("" + saved.getId());
				
				EmailDetails emailDetails = new EmailDetails();
				emailDetails.setRecipient(saved.getApplicationUser().getEmail());
				emailDetails.setSubject("ISA Appointment mail");
				
				emailService.sendQrCode(emailDetails, a.getCenter().getAddress());
				
				return new AppointmentItemDTO(saved);
			} catch (Exception e) {
				log.error(e.getMessage());
				throw new Exception("Error: Appointment not made.");
			}
		} else {
			log.error("No appointment found with ID = " + appointmentDTO.getId() + ".");
			return null;
		}
	}
	
	
	public AppointmentItemDTO convertToDto(Appointment app){
	    return new AppointmentItemDTO(app);
	}
	

	@Override
	public Page<AppointmentItemDTO> findByUserTakenPageable(String username, Pageable pageable) {
		
		Page<Appointment> pageables = appointmentRepository.findAllByApplicationUserUsernameAndTakenTrue(username, pageable);
		Page<AppointmentItemDTO> appointmentsPage = pageables.map(appoint -> convertToDto(appoint));
		return appointmentsPage;
	}

	@Override
	public Page<AppointmentItemDTO> findByUserNotTakenPageable(String username, Pageable pageable) {
		Page<Appointment> pageables = appointmentRepository.findAllByApplicationUserUsernameAndTakenFalse(username, pageable);
		Page<AppointmentItemDTO> appointmentsPage = pageables.map(appoint -> convertToDto(appoint));
		return appointmentsPage;
	}

	@Override
	public Page<AppointmentItemDTO> findFreePageable(Pageable pageable) {
		Page<Appointment> pageables = appointmentRepository.findAllByApprovedFalse(pageable);
		Page<AppointmentItemDTO> appointmentsPage = pageables.map(appoint -> convertToDto(appoint));
		return appointmentsPage;
	}

	@Override
	public AppointmentDTO cancel(AppointmentDTO appointmentDTO) {
		Optional<Appointment> appointmentOpt = appointmentRepository.findById(appointmentDTO.getId());

		if (appointmentOpt.isPresent()) {
			Appointment a = appointmentOpt.get();

			a.setModifiedTime(LocalDateTime.now());
			a.setApplicationUser(null);
			a.setTaken(false);
			a.setApproved(false);

			Appointment saved = appointmentRepository.save(a);
			log.info("" + saved.getId());
			return new AppointmentDTO(saved);
		} else {
			log.error("No appointment found with ID = " + appointmentDTO.getId() + ".");
			return null;
		}
	}
	
}
