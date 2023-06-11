package com.ivan.isaback.service.impl;

import java.time.LocalDate;
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
import com.ivan.isaback.model.dto.AppointmentItemResponseDTO;
import com.ivan.isaback.model.dto.ConditionsEvaluationDTO;
import com.ivan.isaback.repository.ApplicationUserRepository;
import com.ivan.isaback.repository.AppointmentRepository;
import com.ivan.isaback.service.ApplicationUserService;
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
	private ApplicationUserService applicationUserService;

	public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
			ApplicationUserRepository applicationUserRepository, EmailService emailService,
			ApplicationUserService applicationUserService) {
		super();
		this.appointmentRepository = appointmentRepository;
		this.applicationUserRepository = applicationUserRepository;
		this.emailService = emailService;
		this.applicationUserService = applicationUserService;
	}

	@Override
	public List<AppointmentItemDTO> findAll() {
		List<Appointment> appointments = appointmentRepository.findAll();
		List<AppointmentItemDTO> appointmentResponseDTOs = new ArrayList<>();
		for (Appointment a : appointments) {
			AppointmentItemDTO apDto = new AppointmentItemDTO(a);
			appointmentResponseDTOs.add(apDto);
		}
		return appointmentResponseDTOs;

	}

	@Override
	public AppointmentItemDTO findOne(int id) {
		Optional<Appointment> appointmentOpt = appointmentRepository.findById(id);
		if (appointmentOpt.isPresent()) {
			return new AppointmentItemDTO(appointmentOpt.get(), new ConditionsEvaluationDTO(true, ""), false);
		} else {
			log.error("Appointment not found.");
			return null;
		}
	}

//	@Override
//	public List<Appointment> findByUserTaken(String username) {
//		return appointmentRepository.findAllByApplicationUserUsernameAndTakenTrue(username);
//	}
//
//	@Override
//	public List<Appointment> findByUserAndNotTaken(String username) {
//		return appointmentRepository.findAllByApplicationUserUsernameAndTakenFalse(username);
//	}

	@Override
	public AppointmentItemDTO save(Appointment appointment) throws Exception {
		try {
			// calculate end time
			// made for creation of appointment
			// so admin can enter start time and duration only
			appointment.setEndTime(appointment.getStartTime().plusMinutes(appointment.getDuration()));
			Appointment saved = appointmentRepository.save(appointment);
			return new AppointmentItemDTO(saved);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new Exception("Error: Appointment not added.");
		}
	}

	@Override
	public AppointmentItemResponseDTO make(AppointmentDTO appointmentDTO) throws Exception {
		Optional<Appointment> appointmentOpt = appointmentRepository.findById(appointmentDTO.getId());

		if (appointmentOpt.isPresent()) {
			Appointment a = appointmentOpt.get();

			ApplicationUser applicationUser = applicationUserRepository
					.findByUsernameAndActivatedTrue(appointmentDTO.getUsername());

			if (applicationUser != null) {
				if (applicationUserService.evaluateConditions(appointmentDTO.getUsername()).isCanMakeAppointment()) {

					a.setApplicationUser(applicationUser);
					a.setTaken(false);
					a.setApproved(true);

					// provera da li korisnik ima nesto zakazano u ovo vreme

					LocalDateTime newStart = a.getStartTime();
					LocalDateTime newEnd = a.getStartTime().plusMinutes(a.getDuration());
					int newCenterId = a.getCenter().getId();

					List<Appointment> appointments = appointmentRepository
							.findAllByApplicationUserUsernameAndTakenFalseAndStartTimeAfter(appointmentDTO.getUsername(), LocalDateTime.now());

					for (Appointment appointment : appointments) {

//						log.info("new start: " + newStart);
//						log.info("new end:   " + newEnd);
						LocalDateTime oldStart = appointment.getStartTime();
						LocalDateTime oldEnd = appointment.getStartTime().plusMinutes(appointment.getDuration());
						int oldCenterId = appointment.getCenter().getId();

//						log.info("old start: " + oldStart);
//						log.info("old end:   " + oldEnd);
//						log.info("new start before old end" + newStart.isBefore(oldEnd));
//
//						log.info("new end after old start" + newEnd.isAfter(oldStart));

						if (applicationUser.getPenalty() > 3) {
							return new AppointmentItemResponseDTO(0, "User have 3 or more penalties.");
						}
						if (applicationUser.getLastDonationDate().plusMonths(6).isAfter(LocalDate.now())) {
							return new AppointmentItemResponseDTO(0, "User has donated in last 6 months.");
						}
						// ako je u pitanju isti centar proveriti da se ne poklapaju satnice, ako nije 
						if (newCenterId == oldCenterId) {
							if (newStart.isBefore(oldEnd) && newEnd.isAfter(oldStart)) {
								log.error("Appointment overlaps with your upcoming appointment " + appointment.getId()
										+ ".");
								// vrati na bekend 0 da signalizira da ne treba da se prebaci na drugu stranicu
								return new AppointmentItemResponseDTO(0,
										"Appointment overlaps with your upcoming appointment " + appointment.getId()
												+ ".");
							}
						}
					}
				}
			}

			try {
				Appointment saved = appointmentRepository.save(a);
				log.info("" + saved.getId());

				EmailDetails emailDetails = new EmailDetails();
				emailDetails.setRecipient(saved.getApplicationUser().getEmail());
				emailDetails.setSubject("ISA Appointment mail");

				emailService.sendQrCode(emailDetails, a.getCenter().getAddress());

				return new AppointmentItemResponseDTO(appointmentDTO.getId(),
						"Appointment " + appointmentDTO.getId() + " reserved.");
			} catch (Exception e) {
				log.error(e.getMessage());
				throw new Exception("Error: Appointment not made.");
			}
		} else {
			log.error("No appointment found with ID = " + appointmentDTO.getId() + ".");
			return new AppointmentItemResponseDTO(0, "No appointment found with ID = " + appointmentDTO.getId() + ".");
		}
	}

	public AppointmentItemDTO convertToDtoParamsDefault(Appointment app) {
		return new AppointmentItemDTO(app);
	}

	public AppointmentItemDTO convertToDtoCancellable(Appointment app) {

		boolean canCancel = LocalDateTime.now().plusDays(1).isBefore(app.getStartTime());
//		log.info(" " + LocalDateTime.now().plusDays(1));
//		log.info(" " + app.getStartTime());
//		log.info("Can be cancelled: " + canCancel);
		// calculate if date is further than
		return new AppointmentItemDTO(app, new ConditionsEvaluationDTO(true, ""), canCancel);
	}

	public AppointmentItemDTO convertToDtoCanReserveCancellable(Appointment app, ConditionsEvaluationDTO conditions) {

		boolean canCancel = LocalDateTime.now().plusDays(1).isBefore(app.getStartTime());
//		log.info(" " + LocalDateTime.now().plusDays(1));
//		log.info(" " + app.getStartTime());
//		log.info("Can be cancelled: " + canCancel);
		// calculate if date is further than
		return new AppointmentItemDTO(app, conditions, canCancel);
	}

	@Override
	public Page<AppointmentItemDTO> findByUserTakenPageable(String username, Pageable pageable) {
		//history
		Page<Appointment> pageables = appointmentRepository.findAllByApplicationUserUsernameAndEndTimeBefore(username, LocalDateTime.now(), pageable);
		Page<AppointmentItemDTO> appointmentsPage = pageables.map(appoint -> convertToDtoParamsDefault(appoint));
		return appointmentsPage;
	}

	@Override
	public Page<AppointmentItemDTO> findByUserNotTakenPageable(String username, Pageable pageable) {
		Page<Appointment> pageables = appointmentRepository.findAllByApplicationUserUsernameAndTakenFalseAndStartTimeAfter(username, LocalDateTime.now(), pageable);
		Page<AppointmentItemDTO> appointmentsPage = pageables.map(appoint -> convertToDtoCancellable(appoint));
		return appointmentsPage;
	}

	@Override
	public Page<AppointmentItemDTO> findFreePageable(String username, Pageable pageable) {
		// vrati sve koji su slobodni a da nisu prosli
		Page<Appointment> pageables = appointmentRepository.findAllByStartTimeAfterAndApprovedFalse(LocalDateTime.now(), pageable);
		ConditionsEvaluationDTO conditions = applicationUserService.evaluateConditions(username);
		Page<AppointmentItemDTO> appointmentsPage = pageables
				.map(appoint -> convertToDtoCanReserveCancellable(appoint, conditions));
		return appointmentsPage;
	}

	@Override
	public AppointmentDTO cancel(AppointmentDTO appointmentDTO) {
		Optional<Appointment> appointmentOpt = appointmentRepository.findById(appointmentDTO.getId());

		if (appointmentOpt.isPresent()) {
			Appointment a = appointmentOpt.get();

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
