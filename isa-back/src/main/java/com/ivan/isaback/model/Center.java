package com.ivan.isaback.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Table(name = "center", schema = "isa")
public class Center {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String centerName;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = true)
	private float rating;

	@Column(nullable = true)
	private LocalTime openTime;

	@Column(nullable = true)
	private LocalTime closedTime;
		
}
