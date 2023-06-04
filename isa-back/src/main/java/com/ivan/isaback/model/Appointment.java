package com.ivan.isaback.model;

import java.sql.Timestamp;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "center_id", nullable = false)
	private Center center;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = true)
	private User user;
	
	@ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "appointment_personnel", joinColumns = @JoinColumn(name = "personnel_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "appointment_id", referencedColumnName = "id"))
    private Set<Personnel> personnelList = new HashSet<Personnel>();
	
	@Column
	private LocalDateTime modifiedTime;
	
	@Column
	private LocalDateTime startTime;
	
	@Column
	private int duration;
	
	@Column(nullable = true)
	private boolean status;
	
	@Column(nullable = true)
	private boolean approved;
	
}
