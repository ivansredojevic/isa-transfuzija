package com.ivan.isaback.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ivan.isaback.model.User;
import com.ivan.isaback.model.dto.UserDTO;
import com.ivan.isaback.repository.UserRepository;
import com.ivan.isaback.service.UserService;
import com.ivan.isaback.util.email.EmailDetails;
import com.ivan.isaback.util.email.EmailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService{
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	private UserRepository userRepository;
	private EmailService emailService;
	
	public UserServiceImpl(UserRepository userRepository, EmailService emailService) {
		super();
		this.userRepository = userRepository;
		this.emailService = emailService;
	}

	@Override
	public User registerUser(User user) {
		
		Optional<User> existingUser = userRepository.findOneByEmail(user.getEmail());
		
		if(existingUser.isPresent()) {
			return null;
		}
		
		user.setToken(UUID.randomUUID().toString());
		user.setRole("ROLE_USER");
		user.setActivated(false);
		User savedUser = userRepository.save(user);
		
		EmailDetails emailDetails = new EmailDetails();
		emailDetails.setRecipient(user.getEmail());
		emailDetails.setSubject("ISA activation mail");
		emailDetails.setMsgBody("http://localhost:8089/api/users/activate/" + user.getToken());
		log.info(emailDetails.getMsgBody());
		log.info(user.getToken());
		emailService.sendLink(emailDetails);
		
		return savedUser;
	}

	@Override
	public void deleteUser(int id) {
		userRepository.deleteById(id);	
	}

	@Override
	public void updateUser(UserDTO userDto) {
		Optional<User> user = userRepository.findById(userDto.getId());
		if(user.isPresent()) {
			user.get().setEmail(userDto.getEmail());
			user.get().setUsername(userDto.getUsername());
			user.get().setName(userDto.getName());
			user.get().setSurname(userDto.getSurname());
			user.get().setAddress(userDto.getAddress());
			user.get().setCity(userDto.getCity());
			user.get().setState(userDto.getState());
			user.get().setPhone(userDto.getPhone());
			user.get().setJmbg(userDto.getJmbg());;
			user.get().setSex(userDto.getSex());
			user.get().setOccupation(userDto.getOccupation());
			user.get().setJobinformation(userDto.getJobinformation());
			userRepository.save(user.get());	
		}
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {			
			User user = userRepository.findByUsername(username).get();
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
			return new org.springframework.security.core.userdetails.User(username, user.getPassword(), true, true, true, true, authorities);			
		} catch(Exception ex) {		
			throw new UsernameNotFoundException("No user with username " + username);		
		}		
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}

	@Override
	public boolean activateUser(String token) {
		
		Optional<User> existingUser = userRepository.findOneByTokenAndActivatedFalse(token);
		log.info("" + existingUser);
		
		if(!existingUser.isPresent()) {
			return false;
		}
		
		User user = existingUser.get();
		user.setActivated(true);
		userRepository.save(user);
		
		return true;
	}

	@Override
	public void updatePassword(UserDTO userDto) {
		
		Optional<User> user = userRepository.findById(userDto.getId());
		if(user.isPresent()) {
			user.get().setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
			userRepository.save(user.get());	
		}
	}
}