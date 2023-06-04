package com.ivan.isaback.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@DiscriminatorValue("application_user")
//@Table(name = "user", schema = "isa")
public class ApplicationUser {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	@SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 100, initialValue=1)
	private int id;
	
	@Column
	private String email;
	
	@Column(unique = true)
	private String username;

	@Column
	private String password;

	@Column
	private String name;

	@Column(nullable = true)
	private String surname;

	@Column(nullable = true)
	private String address;

	@Column(nullable = true)
	private String city;

	@Column(nullable = true)
	private String state;

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
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "questionnaire_id", nullable = true)
	private Questionnaire questionnaire;

}
