package com.ivan.isaback.model;

import java.time.LocalTime;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
//@Table(name = "center", schema = "isa")
public class Center {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String centerName;
	
	@OneToMany(mappedBy = "center", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Personnel> personnelList = new HashSet<Personnel>();
	
	@OneToMany(mappedBy = "center", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Appointment> appointments = new HashSet<Appointment>();
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = true)
	private float rating;

	@Column(nullable = true)
	private LocalTime openTime;

	@Column(nullable = true)
	private LocalTime closedTime;
	
	// one to many getters and seters
	public void addPersonnel(Personnel p) {
        p.setCenter(this);
        personnelList.add(p);
    }
	
	public void addAppointment(Appointment a) {
        a.setCenter(this);
        appointments.add(a);
    }
	
	public void removePersonnel(Personnel p) {
		personnelList.remove(p);
		p.setCenter(null);
	}
	
	public void removeAppointment(Appointment a) {
		appointments.remove(a);
		a.setCenter(null);
	}
	
}
