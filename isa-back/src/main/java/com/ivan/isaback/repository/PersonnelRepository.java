package com.ivan.isaback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivan.isaback.model.Personnel;

public interface PersonnelRepository extends JpaRepository<Personnel, Integer> {

}
