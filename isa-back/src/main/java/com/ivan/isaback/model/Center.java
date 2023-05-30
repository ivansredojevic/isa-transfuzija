package com.ivan.isaback.model;

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
@Table(name = "center", schema = "isa")
public class Center {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String centerName;
	
	@ManyToOne
	@JoinColumn(name = "admin_id", nullable = false)
	private User admin;
	
	@Column
	private String city;
	
	@Column
	private String rating;
	
}
