package com.ivan.isaback.model.dto;

import com.ivan.isaback.model.Center;
import com.ivan.isaback.model.Personnel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonnelDTO {
	
	private int id;
	private String email;
	private String username;
	private String password;
	private String name;
	private String surname;
	private String address;
	private String phone;
	private String jmbg;
	private String sex;
	private String occupation;
	private String jobinformation;
	private Center center;
	
	public PersonnelDTO(Personnel user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.address = user.getAddress();
		this.phone = user.getPhone();
		this.jmbg = user.getJmbg();
		this.sex = user.getSex();
		this.occupation = user.getOccupation();
		this.jobinformation = user.getJobinformation();
		this.center = user.getCenter();
	}
	
}
