package com.ivan.isaback.model.dto;

import com.ivan.isaback.model.ApplicationUser;
import com.ivan.isaback.model.Appointment;
import com.ivan.isaback.model.Center;
import com.ivan.isaback.model.Complaint;
import com.ivan.isaback.model.Personnel;

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
public class ComplaintDTO {
	
	private int id;
	private String complaintText;
	private String replyText;
    private ApplicationUser admin;
	private Appointment appointment;
    private ApplicationUser applicationUser;	
    private Personnel personnelUser;
    private Center center;
	
    public ComplaintDTO(Complaint c) {
    	
		this.id = c.getId();
		this.complaintText = c.getComplaintText();
		this.replyText = c.getReplyText();
//		this.admin = new ApplicationUserDTO(c.getAdmin());
//		this.appointment = new AppointmentDTO(c.getAppointment());
//		this.applicationUser = new ApplicationUserDTO(c.getApplicationUser());
//		this.personnelUser = new PersonnelDTO(c.getPersonnelUser());
//		this.center = new CenterDTO(c.getCenter());
		this.admin = c.getAdmin();
		this.appointment = c.getAppointment();
		this.applicationUser = c.getApplicationUser();
		this.personnelUser = c.getPersonnelUser();
		this.center = c.getCenter();
	}
}
