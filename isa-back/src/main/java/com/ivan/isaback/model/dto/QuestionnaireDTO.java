package com.ivan.isaback.model.dto;

import com.ivan.isaback.model.ApplicationUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionnaireDTO {
	

    private int donationNumber;
    private boolean rejected;
    private boolean healthy;
    private boolean dangerousOccupation;
    private boolean infectious;
    private boolean bloodPressureIssues;
    private boolean onTherapy;
    private boolean aspirin;
    private boolean tatooed;
    private ApplicationUser applicationUser;

}
