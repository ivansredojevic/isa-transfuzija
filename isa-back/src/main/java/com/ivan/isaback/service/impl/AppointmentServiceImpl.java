package com.ivan.isaback.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ivan.isaback.model.Appointment;
import com.ivan.isaback.model.QRCodeModel;
import com.ivan.isaback.model.dto.AppointmentDTO;
import com.ivan.isaback.repository.AppointmentRepository;
import com.ivan.isaback.service.AppointmentService;
import com.ivan.isaback.util.email.EmailDetails;
import com.ivan.isaback.util.email.EmailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {
	
	private AppointmentRepository appointmentRepository;
	private EmailService emailService;
	
	public AppointmentServiceImpl(AppointmentRepository appointmentRepository, EmailService emailService) {
		super();
		this.appointmentRepository = appointmentRepository;
		this.emailService = emailService;
	}

	@Override
	public List<Appointment> findAll() {
		return appointmentRepository.findAll();
	}

	@Override
	public List<Appointment> findByUserId(int id) {
		return appointmentRepository.findAllByApplicationUserIdOrderByStartTimeDesc(id);
	}

	@Override
	public List<Appointment> findByUserIdTaken(int id) {
		return appointmentRepository.findAllByApplicationUserIdAndTakenTrue(id);
	}

	@Override
	public List<Appointment> findByUserIdAndNotTaken(int id) {
		return appointmentRepository.findAllByApplicationUserIdAndTakenFalse(id);
	}

	@Override
	public Appointment save(AppointmentDTO appointmentDTO) {
		
		Appointment a = new Appointment();
		
		a.setCenter(appointmentDTO.getCenter());
		
		a.setModifiedTime(LocalDateTime.now());
		a.setStartTime(appointmentDTO.getStartTime());
		a.setDuration(appointmentDTO.getDuration());
		a.setPriceEuro(appointmentDTO.getPriceEuro());
		a.setTaken(false);
		a.setApproved(true);
		
		log.info(a.toString());
		
		try {
			Appointment saved = appointmentRepository.save(a);
			return saved;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Appointment make(AppointmentDTO appointmentDTO) {
		Optional<Appointment> appointmentOpt = appointmentRepository.findById(appointmentDTO.getId());
		if(appointmentOpt.isPresent()) {
			Appointment a = appointmentOpt.get();
			
			a.setCenter(appointmentDTO.getCenter());
			a.setApplicationUser(appointmentDTO.getApplicationUser());
			a.setModifiedTime(LocalDateTime.now());
			a.setStartTime(appointmentDTO.getStartTime());
			a.setDuration(appointmentDTO.getDuration());
			a.setPriceEuro(appointmentDTO.getPriceEuro());
			a.setTaken(appointmentDTO.isTaken());
			a.setApproved(appointmentDTO.isApproved());
		
			log.info(a.toString());
		
			try {
				Appointment saved = appointmentRepository.save(a);
				
				EmailDetails emailDetails = new EmailDetails();
				emailDetails.setRecipient(saved.getApplicationUser().getEmail());
				emailDetails.setSubject("ISA Appointment mail");
				
				QRCodeModel qrCodeModel = new QRCodeModel();
				qrCodeModel.setUserDetails(a.getApplicationUser().getName() + " " + a.getApplicationUser().getSurname());
				qrCodeModel.setAppointmentDate(a.getStartTime().toString());
				qrCodeModel.setCenterName(a.getCenter().getCenterName());
				qrCodeModel.setCenterAddress(a.getCenter().getAddress());
				
				emailService.sendQrCode(emailDetails, qrCodeModel);
				
				return saved;
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			log.error("No appointment found with ID = " + appointmentDTO.getId() + ".");
			return null;
		}
	}

}
