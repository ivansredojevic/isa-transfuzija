package com.ivan.isaback.model.dto;

import com.ivan.isaback.model.Appointment;
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
public class AppointmentResponseDTO {

	private int id;
	private String username;
	private String center;
	private String modifiedTime;
	private String startTime;
	private String duration;
	private String priceEuro;
	private String taken;
	private String approved;
	private String doctors;

	public AppointmentResponseDTO(Appointment a) {
		this.id = a.getId();
		if (a.getApplicationUser() != null) {
			this.username = a.getApplicationUser().getUsername();
		} else {
			this.username = "";
		}
		this.center = a.getCenter().getCenterName();
		this.modifiedTime = a.getModifiedTime().toString();
		this.startTime = a.getStartTime().toString();
		this.duration = String.valueOf(a.getDuration());
		this.priceEuro = String.valueOf(a.getPriceEuro());
		this.taken = a.isTaken() ? "Done" : "Upcoming";
		this.approved = a.isApproved() ? "Not available" : "Available";
		String s = "";
		if (!a.getDoctors().isEmpty()) {
			for (Personnel p : a.getDoctors()) {
				s += ", " + p.getName();
			}
			this.doctors = s.substring(2);
		} else {
			this.doctors = "";
		}
	}

}
