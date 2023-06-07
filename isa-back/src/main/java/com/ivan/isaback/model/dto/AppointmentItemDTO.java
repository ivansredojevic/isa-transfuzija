package com.ivan.isaback.model.dto;

import java.util.ArrayList;

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
public class AppointmentItemDTO {

	private int id;
	private String username;
	private String center;
	private int centerId;
	private String modifiedTime;
	private String startTime;
	private String duration;
	private String priceEuro;
	private String taken;
	private String approved;
	private String doctors;
	private ArrayList<Integer> doctorIds;

	public AppointmentItemDTO(Appointment a) {
		this.id = a.getId();
		if (a.getApplicationUser() != null) {
			this.username = a.getApplicationUser().getUsername();
		} else {
			this.username = "";
		}
		this.center = a.getCenter().getCenterName();
		this.centerId = a.getCenter().getId();
		this.modifiedTime = a.getModifiedTime().toString();
		this.startTime = a.getStartTime().toString();
		this.duration = String.valueOf(a.getDuration());
		this.priceEuro = String.valueOf(a.getPriceEuro());
		this.taken = a.isTaken() ? "Done" : "Upcoming";
		this.approved = a.isApproved() ? "Not available" : "Available";
		String s = "";
		if (!a.getDoctors().isEmpty()) {
			doctorIds = new ArrayList<>();
			for (Personnel p : a.getDoctors()) {
				s += ", " + p.getName();
				doctorIds.add(p.getId());
			}
			this.doctors = s.substring(2);
		} else {
			this.doctors = "";
			
		}
	}

}
