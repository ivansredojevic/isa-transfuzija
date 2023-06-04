package com.ivan.isaback.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @Column
    private LocalDate date;
    
    @Column
    private int donationNumber;
    
    private boolean rejected;
    
    private boolean healthy;
    
    private boolean dangerousOccupation;
    
    private boolean infectious;
    
    private boolean bloodPressureIssues;
    
    private boolean onTherapy;
    
    private boolean aspirin;
    
    private boolean tatooed;
}
