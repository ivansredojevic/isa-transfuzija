package com.ivan.isaback.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    @Column
    private LocalDate date;
    
    @Column
    private int donationNumber;
    
    @Column
    private boolean rejected;
    
    @Column
    private boolean healthy;
    
    @Column
    private boolean dangerousOccupation;
    
    @Column
    private boolean infectious;
    
    @Column
    private boolean bloodPressureIssues;
    
    @Column
    private boolean onTherapy;
    
    @Column
    private boolean aspirin;
    
    @Column
    private boolean tatooed;
}
