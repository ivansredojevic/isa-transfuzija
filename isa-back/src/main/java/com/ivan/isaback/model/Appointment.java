package com.ivan.isaback.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "appointment", schema = "isa")
public class Appointment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "center_id", nullable = false)
	private Center center;
	
	// one appointment connected to many users
	@ManyToOne
	@JoinColumn(name = "short_number_id", nullable = false)
	private User user;
	
	@Column
	private Timestamp dateModified;
	
	@Column
	private Timestamp startTime;
	
	@Column
	private String price;
	
	@Column
	private String duration;
	
	@Column
	private boolean status;
	
	@Column
	private boolean approved;

	
	
	
}
