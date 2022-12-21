package com.ivan.isaback.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user", schema = "isa")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	@SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 100, initialValue=1)
	private int id;
	
	@Column
	private String email;
	
	@Column
	private String username;

	@Column
	private String password;

	@Column
	private String name;

	@Column
	private String surname;

	@Column
	private String address;

	@Column
	private String city;

	@Column
	private String state;

	@Column
	private String phone;

	@Column
	private String jmbg;

	@Column
	private String sex;

	@Column
	private String occupation;

	@Column
	private String jobinformation;
	
	@Column(nullable = false)
	private Boolean activated;
	
	@Column
	private String role;
	
	@Column
	private String token;
	
}
