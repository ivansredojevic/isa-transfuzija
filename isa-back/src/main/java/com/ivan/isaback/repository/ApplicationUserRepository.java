package com.ivan.isaback.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ivan.isaback.model.ApplicationUser;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Integer>{
	
	ApplicationUser findByEmail(String email);
	ApplicationUser findByEmailAndActivatedTrue(String email);
	ApplicationUser findByUsernameAndActivatedTrue(String username);

	Optional<ApplicationUser> findOneByUsername(String username);
	Optional<ApplicationUser> findOneById(int id);
	Optional<ApplicationUser> findOneByEmail(String email);
	Optional<ApplicationUser> findOneByTokenAndActivatedFalse(String token);
	Optional<ApplicationUser> findOneByToken(String token);
	Optional<ApplicationUser> findOneByUsernameAndActivatedTrue(String username);
	
	@Modifying
    @Query("UPDATE ApplicationUser SET penalty = 0 WHERE dtype = 'application_user'")
    void clearPenalties();
	
}
