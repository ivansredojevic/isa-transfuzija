package com.ivan.isaback.model.dto;

import java.time.LocalDate;

import javax.persistence.Column;

import com.ivan.isaback.model.ApplicationUser;

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
public class ApplicationUserDTO {
	
	private int id;
	private String email;
	private String username;
	private String name;
	private String surname;
	private String address;
	private String phone;
	private String jmbg;
	private String sex;
	private String occupation;
	private String jobinformation;
	private Boolean activated;
	private String role;
	private int penalty;
	
	public ApplicationUserDTO(ApplicationUser user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.username = user.getUsername();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.address = user.getAddress();
		this.phone = user.getPhone();
		this.jmbg = user.getJmbg();
		this.sex = user.getSex();
		this.occupation = user.getOccupation();
		this.jobinformation = user.getJobinformation();
		this.activated = user.getActivated();
		this.role = user.getRole();
		this.penalty = user.getPenalty();
	}
	
	

	
	
	
}
