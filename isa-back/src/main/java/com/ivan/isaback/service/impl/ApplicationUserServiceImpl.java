package com.ivan.isaback.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ivan.isaback.model.ApplicationUser;
import com.ivan.isaback.model.Questionnaire;
import com.ivan.isaback.model.dto.ActivationDTO;
import com.ivan.isaback.model.dto.ApplicationUserDTO;
import com.ivan.isaback.model.dto.RegisterUserDTO;
import com.ivan.isaback.repository.ApplicationUserRepository;
import com.ivan.isaback.repository.QuestionnaireRepository;
import com.ivan.isaback.service.ApplicationUserService;
import com.ivan.isaback.util.email.EmailDetails;
import com.ivan.isaback.util.email.EmailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApplicationUserServiceImpl implements ApplicationUserService, UserDetailsService {

	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	private ApplicationUserRepository userRepository;
	private QuestionnaireRepository questionnaireRepository;
	private EmailService emailService;

	public ApplicationUserServiceImpl(ApplicationUserRepository userRepository,
			QuestionnaireRepository questionnaireRepository, EmailService emailService) {
		super();
		this.userRepository = userRepository;
		this.questionnaireRepository = questionnaireRepository;
		this.emailService = emailService;
	}

	@Override
	public String registerUser(RegisterUserDTO user) {

		Optional<ApplicationUser> existingEmail = userRepository.findOneByEmail(user.getEmail());
		Optional<ApplicationUser> existingUsername = userRepository.findOneByUsername(user.getUsername());

		if (existingEmail.isPresent()) {
			return "User with email " + user.getEmail() + " already exists.";
		} else if (existingUsername.isPresent()) {
			return "User with username " + user.getUsername() + " already exists.";
		}
		log.info(user.toString());

		ApplicationUser newUser = new ApplicationUser();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		newUser.setEmail(user.getEmail());
		newUser.setName(user.getName());
		newUser.setSurname(user.getSurname());
		newUser.setAddress(user.getAddress());
		newUser.setPhone(user.getPhone());
		newUser.setJmbg(user.getJmbg());
		newUser.setSex(user.getSex());
		newUser.setOccupation(user.getOccupation());
		newUser.setJobinformation(user.getJobinformation());
		newUser.setToken(UUID.randomUUID().toString());
		newUser.setRole(user.getRole());
		newUser.setActivated(false);
		newUser.setPenalty(0);
		newUser.setLastDonationDate(LocalDate.parse(user.getLastDonationDate()));

		ApplicationUser savedUser = userRepository.save(newUser);

		EmailDetails emailDetails = new EmailDetails();
		emailDetails.setRecipient(user.getEmail());
		emailDetails.setSubject("ISA activation mail");
		emailDetails.setMsgBody("http://localhost:4200/activate?token=" + savedUser.getToken());
		log.info(emailDetails.getMsgBody());
		log.info(savedUser.getToken());
		emailService.sendLink(emailDetails);

		return "Your account is created. Visit the link from email to activate your account.";
	}

	@Override
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public boolean evaluateConditions(String username) {

		Optional<ApplicationUser> applicationUserOpt = userRepository.findOneByUsernameAndActivatedTrue(username);
		if (applicationUserOpt.isPresent()) {
			Optional<Questionnaire> questionnaireOpt = questionnaireRepository
					.findOneByApplicationUserId(applicationUserOpt.get().getId());
			if (questionnaireOpt.isPresent()) {
				if (!questionnaireOpt.get().getApplicationUser().getUsername()
						.equals(applicationUserOpt.get().getUsername())) {
					// questionnaire invalid
					return false;
				}
				if (applicationUserOpt.get().getPenalty() >= 3) {
					// has more than 3 penalties
					return false;
				}
				if (applicationUserOpt.get().getLastDonationDate().plusMonths(6).isAfter(LocalDate.now())) {
					// had appointment in last 6 months
					return false;
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public void updateUser(ApplicationUserDTO userDto) {
		Optional<ApplicationUser> user = userRepository.findById(userDto.getId());
		if (user.isPresent()) {
			user.get().setEmail(userDto.getEmail());
			user.get().setUsername(userDto.getUsername());
			user.get().setName(userDto.getName());
			user.get().setSurname(userDto.getSurname());
			user.get().setAddress(userDto.getAddress());
			user.get().setPhone(userDto.getPhone());
			user.get().setJmbg(userDto.getJmbg());
			;
			user.get().setSex(userDto.getSex());
			user.get().setOccupation(userDto.getOccupation());
			user.get().setJobinformation(userDto.getJobinformation());
			user.get().setPenalty(userDto.getPenalty());
			userRepository.save(user.get());
		}
	}

	@Override
	public void addPenalty(ApplicationUserDTO userDto) {

		Optional<ApplicationUser> user = userRepository.findById(userDto.getId());
		if (user.isPresent()) {
			user.get().setPenalty(userDto.getPenalty() + 1);
			userRepository.save(user.get());
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Optional<ApplicationUser> user = userRepository.findOneByUsername(username);
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_" + user.get().getRole()));
			return new org.springframework.security.core.userdetails.User(username, user.get().getPassword(), true,
					true, true, true, authorities);
		} catch (Exception ex) {
			throw new UsernameNotFoundException("No user with username " + username);
		}
	}

	@Override
	public ApplicationUserDTO getCurrentByUsername(String username) {
		ApplicationUser user = userRepository.findByUsernameAndActivatedTrue(username);
		Optional<Questionnaire> questionnaireOpt = questionnaireRepository.findOneByApplicationUserId(user.getId());
		int questId = 0;
		if (questionnaireOpt.isPresent()) {
			questId = questionnaireOpt.get().getId();
		}

		boolean canDonate = false;
		if (user.getPenalty() < 3 && user.getLastDonationDate().plusMonths(6).isBefore(LocalDate.now())) {
			canDonate = true;
		}
		return new ApplicationUserDTO(user, canDonate, questId);
	}

	@Override
	public ApplicationUser findByUsername(String username) {
		return userRepository.findByUsernameAndActivatedTrue(username);
	}

	@Override
	public Optional<ApplicationUser> findByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}

	@Override
	public ActivationDTO activateUser(String token) {

		Optional<ApplicationUser> existingUser = userRepository.findOneByToken(token);
		log.info("" + existingUser);

		if (!existingUser.isPresent()) {
			return new ActivationDTO("fail-nonexistent", "");
		} else {

			if (existingUser.get().getActivated()) {
				return new ActivationDTO("fail-repeating", existingUser.get().getUsername());
			} else {
				ApplicationUser user = existingUser.get();
				user.setActivated(true);
				userRepository.save(user);

				return new ActivationDTO("success", existingUser.get().getUsername());
			}
		}
	}

	@Override
	public void updatePassword(ApplicationUser user) {

		Optional<ApplicationUser> userOpt = userRepository.findById(user.getId());
		if (userOpt.isPresent()) {
			userOpt.get().setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			userRepository.save(userOpt.get());
		}
	}

}
