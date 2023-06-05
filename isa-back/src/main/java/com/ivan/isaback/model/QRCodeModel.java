package com.ivan.isaback.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QRCodeModel {
	
	private String centerName;
	private String centerAddress;
	private String appointmentDate;
	private String userDetails;
	
	
	@Override
	public String toString() {
		return "User" + userDetails + ", time " + appointmentDate + ", at " + centerName + ", " + centerAddress + ".";
	}
}
