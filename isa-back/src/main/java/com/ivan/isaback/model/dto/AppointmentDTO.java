package com.ivan.isaback.model.dto;

import com.ivan.isaback.model.Appointment;

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
	private String username;
	
	public AppointmentDTO(Appointment a) {
		this.id = a.getId();
	}
	
	
	
}
