package com.example.ecommerce.entity.dto;

import lombok.Getter;
import lombok.Setter;

public class UserRegistrationDto {

	//@NotEmpty(message = "First name can't be empty!")
	@Getter@Setter
	private String firstName;

	//@NotEmpty(message = "Last name can't be empty!")
	@Getter@Setter
	private String lastName;

	//@NotEmpty(message = "Email name can't be empty!")
	//@Email(message = "*Please provide a valid Email")
	@Getter@Setter
	private String userName;

	//@Length(min = 5, message = "*Your password must have at least 5 characters")
	//@NotEmpty(message = "*Please provide your password")
	@Getter@Setter
	private String password;

	public UserRegistrationDto(){
	}
	
	public UserRegistrationDto(String firstName, String lastName, String userName, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}

}
