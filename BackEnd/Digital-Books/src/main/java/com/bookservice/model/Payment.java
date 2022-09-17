package com.bookservice.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity

public class Payment {
	@Id
	@GeneratedValue
	private Integer paymentid;
	
	private int bookid;
	private int readerid;
	private String readeremail;
	
	
}
