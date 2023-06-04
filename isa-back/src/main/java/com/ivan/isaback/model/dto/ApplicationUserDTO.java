package com.ivan.isaback.model.dto;

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
	private String password;
	private String name;
	private String surname;
	private String address;
	private String city;
	private String state;
	private String phone;
	private String jmbg;
	private String sex;
	private String occupation;
	private String jobinformation;
	private String role;
	
}
