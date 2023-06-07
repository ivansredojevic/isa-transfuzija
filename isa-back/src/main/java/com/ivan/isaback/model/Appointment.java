package com.ivan.isaback.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
//	@JsonIgnoreProperties("appointmentList")
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
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "appointment_personnel", joinColumns = @JoinColumn(name = "appointment_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "personnel_id", referencedColumnName = "id"))
//	@JsonIgnoreProperties("appointments")
	private Set<Personnel> doctors = new HashSet<Personnel>();
	
}
