package com.bookservice.model;

import javax.persistence.Column;

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
	
	@Column(name = "username", nullable = false)
	private String username;
	@Column(name = "email", unique = true)
	private String email;
	@Column(name = "password", nullable = false)
	private String password;
	//@GeneratedValue(strategy = GenerationType.AUTO)
     @Column (name="paymentid")
   private int paymentId;
     private String myBooks;
  
	
}
