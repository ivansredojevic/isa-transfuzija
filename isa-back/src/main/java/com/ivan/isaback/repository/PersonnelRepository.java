package com.ivan.isaback.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivan.isaback.model.Personnel;

public interface PersonnelRepository extends JpaRepository<Personnel, Integer> {

	Optional<Personnel> findByUsername(String username);

}
