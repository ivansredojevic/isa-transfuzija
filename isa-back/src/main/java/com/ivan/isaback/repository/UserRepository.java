package com.ivan.isaback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ivan.isaback.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByEmail(String email);
	User findByEmailAndActivated(String email, Boolean activated);
	
}
