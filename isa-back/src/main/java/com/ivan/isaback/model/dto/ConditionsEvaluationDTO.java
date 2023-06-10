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
public class ConditionsEvaluationDTO {
	
	private boolean canMakeAppointment;
	private String reason;

}
