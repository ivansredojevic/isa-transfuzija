package com.ivan.isaback.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@DiscriminatorValue("personnel")
@ToString
public class Personnel extends ApplicationUser {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "center_id", nullable = true)
	private Center center;
	
	@ManyToMany( cascade = {CascadeType.ALL})
	@JoinTable(name = "appointment_personnel", joinColumns = @JoinColumn(name = "personnel_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "appointment_id", referencedColumnName = "id"))
	@JsonIgnore
	private Set<Appointment> appointments = new HashSet<Appointment>();
	
}
