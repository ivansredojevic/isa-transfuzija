package com.ivan.isaback.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
//@Table(name = "appointment", schema = "isa")
public class Appointment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private Center center;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private ApplicationUser applicationUser;
	
	@Column
	private LocalDateTime modifiedTime;
	
	@Column
	private LocalDateTime startTime;
	
	@Column
	private int duration;
	
	@Column
	private int priceEuro;
	
	@Column(nullable = true)
	private boolean taken;
	
	@Column(nullable = true)
	private boolean approved;
	
}
