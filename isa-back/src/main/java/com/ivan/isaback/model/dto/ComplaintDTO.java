package com.ivan.isaback.model.dto;

import com.ivan.isaback.model.Complaint;

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
	private String admin;
	private String appointment;
	private String personnelUser;
	private String center;

	public ComplaintDTO(Complaint c) {

		this.id = c.getId();
		this.complaintText = c.getComplaintText();
		if (c.getReplyText() != null) {
			this.replyText = c.getReplyText();
		}
		if (c.getAdmin() != null) {
			this.admin = c.getAdmin().getName();
		}
		if (c.getAppointment() != null) {
			this.appointment = "ID: " + c.getAppointment().getId() + ", " + c.getAppointment().getStartTime();
		}
		if (c.getPersonnelUser() != null) {
			this.personnelUser = c.getPersonnelUser().getName();
		}
		if (c.getCenter() != null) {
			this.center = c.getCenter().getCenterName();
		}
	}
}
