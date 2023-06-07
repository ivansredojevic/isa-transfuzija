package com.ivan.isaback.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@DiscriminatorValue("application_user")
public class ApplicationUser {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true)
	private String email;
	
	@Column(unique = true)
	private String username;

	@Column
	private String password;

	@Column(nullable = true)
	private String name;

	@Column(nullable = true)
	private String surname;

	@Column(nullable = true)
	private String address;

	@Column(nullable = true)
	private String phone;

	@Column(nullable = true)
	private String jmbg;

	@Column(nullable = true)
	private String sex;

	@Column(nullable = true)
	private String occupation;

	@Column(nullable = true)
	private String jobinformation;
	
	@Column(nullable = false)
	private Boolean activated;
	
	@Column(nullable = true)
	private String role;
	
	@Column(nullable = true)
	private String token;
	
	@Column(nullable = true)
	private int penalty;
	
}
