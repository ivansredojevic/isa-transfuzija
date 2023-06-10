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
	private String endTime;
	private String startTime;
	private String duration;
	private String priceEuro;
	private boolean taken;
	private String approved;
	private boolean complainPers;
	private boolean complainCenter;
	private String doctors;
	private ArrayList<Integer> doctorIds = new ArrayList<>();
    private boolean canReserve;
    private boolean canCancel;
    private String reason;

	public AppointmentItemDTO(Appointment a, ConditionsEvaluationDTO conditionsEvaluationDTO, boolean canCancel) {
		this.id = a.getId();
		if (a.getApplicationUser() != null) {
			this.username = a.getApplicationUser().getUsername();
		} else {
			this.username = "";
		}
		this.center = a.getCenter().getCenterName();
		this.centerId = a.getCenter().getId();
		this.endTime = a.getEndTime().toString();
		this.startTime = a.getStartTime().toString();
		this.duration = String.valueOf(a.getDuration());
		this.priceEuro = String.valueOf(a.getPriceEuro());
		this.taken = a.isTaken();
		this.approved = a.isApproved() ? "Not available" : "Available";
		this.complainPers = a.isComplainPers();
		this.complainCenter = a.isComplainCenter();
		String s = "";
		if (!a.getDoctors().isEmpty()) {
			for (Personnel p : a.getDoctors()) {
				s += ", " + p.getName();
				doctorIds.add(p.getId());
			}
			this.doctors = s.substring(2);
		} else {
			this.doctors = "";
		}
		this.canReserve = conditionsEvaluationDTO.isCanMakeAppointment();
		this.canCancel = canCancel;
		this.reason = conditionsEvaluationDTO.getReason();
	}
	
	public AppointmentItemDTO(Appointment a) {
		this.id = a.getId();
		if (a.getApplicationUser() != null) {
			this.username = a.getApplicationUser().getUsername();
		} else {
			this.username = "";
		}
		this.center = a.getCenter().getCenterName();
		this.centerId = a.getCenter().getId();
		this.endTime = a.getEndTime().toString();
		this.startTime = a.getStartTime().toString();
		this.duration = String.valueOf(a.getDuration());
		this.priceEuro = String.valueOf(a.getPriceEuro());
		this.taken = a.isTaken();
		this.approved = a.isApproved() ? "Not available" : "Available";
		this.complainPers = a.isComplainPers();
		this.complainCenter = a.isComplainCenter();
		String s = "";
		if (!a.getDoctors().isEmpty()) {
			for (Personnel p : a.getDoctors()) {
				s += ", " + p.getName();
				doctorIds.add(p.getId());
			}
			this.doctors = s.substring(2);
		} else {
			doctorIds = new ArrayList<>();
			this.doctors = "";
		}
		this.canReserve = true;
		this.canCancel = true;
		this.reason = "";
	}

}
