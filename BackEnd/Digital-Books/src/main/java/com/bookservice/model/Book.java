package com.bookservice.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity

public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int authorid;
	private String author;
	@NotBlank(message = "Book should have Title ")
	private String title;
	@Enumerated(EnumType.STRING)
	@NotNull(message=" Book should have Category ")
	private Category category;
	@NotNull(message="Book price should not be less than 1")
	
	private Double price;
	@NotBlank(message = "Book should have Publisher ")
	private String publisher;
	@NotNull(message="Book should have Published Date ")
	private Date publisheddate;
	@Min(value = 1, message = "Book should have atleast one chapter")
	private int chapters;
	@NotBlank(message = "Book content cannot be blank")
	private String content;
	
	private boolean bookstatus;
	

	
}
