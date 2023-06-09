package com.ivan.isaback.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Questionnaire {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate date;
    
    @Column(nullable = false)
    private int donationNumber;
    
    @Column(nullable = false)
    private boolean rejected;
    
    @Column(nullable = false)
    private boolean healthy;
    
    @Column(nullable = false)
    private boolean dangerousOccupation;
    
    @Column(nullable = false)
    private boolean infectious;
    
    @Column(nullable = false)
    private boolean bloodPressureIssues;
    
    @Column(nullable = false)
    private boolean onTherapy;
    
    @Column(nullable = false)
    private boolean aspirin;
    
    @Column(nullable = false)
    private boolean tatooed;
    
    @OneToOne
    @JoinColumn(name = "application_user_id", nullable = true)
    private ApplicationUser applicationUser;
}
