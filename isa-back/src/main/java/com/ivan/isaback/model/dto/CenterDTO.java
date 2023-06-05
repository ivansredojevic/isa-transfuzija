package com.ivan.isaback.model.dto;

import java.time.LocalTime;

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
public class CenterDTO {
	
	private int id;
	private String centerName;
	private String address;
	private float rating;
	private LocalTime openTime;
	private LocalTime closedTime;
	
	public CenterDTO(Center c) {
		this.id = c.getId();
		this.centerName = c.getCenterName();
		this.address = c.getAddress();
		this.rating = c.getRating();
		this.openTime = c.getOpenTime();
		this.closedTime = c.getClosedTime();
	}
	
}
