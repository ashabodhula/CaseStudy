package com.bookservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;



@Entity

public class Payment {
	
	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	 private int readermail;
	private String paymentId;
	private String book;
	

}
