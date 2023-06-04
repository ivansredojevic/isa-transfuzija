package com.ivan.isaback.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
public class Complaint {
	
	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column
	private String text;
	
	@Column(nullable = true)
	private String reply;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id", nullable = true)
    private User admin;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_id", nullable = true)
	private Appointment appointment;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personnel_id", nullable = true)
    private User personnelUser;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "center_id", nullable = true)
    private Center center;
	
}
