package com.ivan.isaback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivan.isaback.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

}
