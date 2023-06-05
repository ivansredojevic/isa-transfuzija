package com.ivan.isaback.model.dto;

import java.time.LocalDateTime;

import com.ivan.isaback.model.ApplicationUser;
import com.ivan.isaback.model.Appointment;
import com.ivan.isaback.model.Center;

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
public class AppointmentDTO {
	
	private int id;
	private Center center;
	private ApplicationUser applicationUser;
	private LocalDateTime modifiedTime;
	private LocalDateTime startTime;
	private int duration;
	private int priceEuro;
	private boolean taken;
	private boolean approved;
	
	
	public AppointmentDTO(Appointment a) {
		
		this.id = a.getId();
		this.center = a.getCenter();
		this.applicationUser = a.getApplicationUser();
		this.modifiedTime = a.getModifiedTime();
		this.startTime = a.getStartTime();
		this.duration = a.getDuration();
		this.priceEuro = a.getPriceEuro();
		this.taken = a.isTaken();
		this.approved = a.isApproved();
	}
	
	
	
}
