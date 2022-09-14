package com.bookservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity

public class Author {
	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "authorid")
	private int id;
	@Column(name = "username", nullable = false)
	private String username;
	@Column(name = "email", unique = true)
	private String email;
	@Column(name = "password", nullable = false)
	private String password;
	private boolean loginstatus;
	
}
