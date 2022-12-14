package com.ivan.isaback.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ivan.isaback.model.User;
import com.ivan.isaback.repository.UserRepository;
import com.ivan.isaback.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{
	
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public void addUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void deleteUser(int id) {
		userRepository.deleteById(id);	
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {			
			User user = userRepository.findByUsername(username).get();
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(user.getRole()));
			return new org.springframework.security.core.userdetails.User(username, user.getPassword(), true, true, true, true, authorities);			
		} catch(Exception ex) {		
			throw new UsernameNotFoundException("No user with username " + username);		
		}		
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
