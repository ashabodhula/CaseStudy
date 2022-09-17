package com.bookservice.model;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Reader {
	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "readerid")
	private int id;

	@NotBlank(message = "Reader Name cannot be blank")
	private String username;
	@Email(message = "Please Enter a Valid Email")
	@NotBlank(message = "Reader Email cannot be blank")
	private String email;
	@NotBlank(message = "Password cannot be blank")
	@Size(min = 8, max = 120, message = "size should be mininum  of 8 characters")
	private String password;
	private int paymentId;
	private int myBooks;

}


