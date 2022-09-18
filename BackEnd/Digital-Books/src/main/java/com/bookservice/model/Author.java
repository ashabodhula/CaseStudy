package com.bookservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity

public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	@Email(message = "Please Enter a Valid Email")
	@NotBlank(message = "Author Email cannot be blank")
	private String email;
	@NotBlank(message = "Password cannot be blank")
	@Size(min = 8, max = 120 ,message ="size should be mininum 0f 8 characters")
	private String password;
	private boolean loginstatus;
}
