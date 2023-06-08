package com.ivan.isaback.model.dto;

import com.ivan.isaback.model.Questionnaire;

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
	
	private int id;
    private int donationNumber;
    private boolean rejected;
    private boolean healthy;
    private boolean dangerousOccupation;
    private boolean infectious;
    private boolean bloodPressureIssues;
    private boolean onTherapy;
    private boolean aspirin;
    private boolean tatooed;
    private ApplicationUserDTO applicationUser;
	
    public QuestionnaireDTO(Questionnaire quest) {
		
		this.id = quest.getId();
		this.donationNumber = quest.getDonationNumber();
		this.rejected = quest.isRejected();
		this.healthy = quest.isHealthy();
		this.dangerousOccupation = quest.isDangerousOccupation();
		this.infectious = quest.isInfectious();
		this.bloodPressureIssues = quest.isBloodPressureIssues();
		this.onTherapy = quest.isOnTherapy();
		this.aspirin = quest.isAspirin();
		this.tatooed = quest.isTatooed();
		this.applicationUser = new ApplicationUserDTO(quest.getApplicationUser());
	}
	
   
    
    

}
