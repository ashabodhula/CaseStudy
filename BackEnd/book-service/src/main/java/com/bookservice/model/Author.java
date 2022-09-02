package com.bookservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	String authorNmae;
	String emMail;
	String password;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAuthorNmae() {
		return authorNmae;
	}
	public void setAuthorNmae(String authorNmae) {
		this.authorNmae = authorNmae;
	}
	public String getEmMail() {
		return emMail;
	}
	public void setEmMail(String emMail) {
		this.emMail = emMail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Author(long id, String authorNmae, String emMail, String password) {
		super();
		this.id = id;
		this.authorNmae = authorNmae;
		this.emMail = emMail;
		this.password = password;
	}

	
	
}
