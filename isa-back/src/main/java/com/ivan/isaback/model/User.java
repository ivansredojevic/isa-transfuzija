package com.ivan.isaback.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "USER")
public class User {
	
	@Id
	@SequenceGenerator(name = "user_id", sequenceName = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id" )
	private Integer id;
	
	@Column
	private String email;

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
	

	@Column
	private Boolean activated;
	

	@Column
	private String role;
	

}
