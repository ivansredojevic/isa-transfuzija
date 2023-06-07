package com.ivan.isaback.model.dto;

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
public class InsertComplaintDTO {
	
	private String username;
	private String complaintText;
	private int centerId;
	private int personnelId;
	private int appointmentId;

}
