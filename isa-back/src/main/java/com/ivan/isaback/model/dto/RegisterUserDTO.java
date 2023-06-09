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
public class RegisterUserDTO {
	
	private String username;
	private String password;
	private String email;
	private String name;
	private String surname;
	private String address;
	private String phone;
    private String jmbg;
    private String sex;
    private String occupation;
    private String jobinformation;
    private String lastDonationDate;
    private String penalty;
    private String role;
    private String questionaireId;
    private String canDonate;
	
}
