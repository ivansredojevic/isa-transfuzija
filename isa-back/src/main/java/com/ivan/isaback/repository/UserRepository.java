package com.ivan.isaback.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ivan.isaback.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByEmail(String email);
	User findByEmailAndActivatedTrue(String email);
	User findByUsernameAndActivatedTrue(String username);
	Optional<User> findByUsername(String username);
	Optional<User> findOneByEmail(String email);
	Optional<User> findOneByTokenAndActivatedFalse(String token);
	
}
