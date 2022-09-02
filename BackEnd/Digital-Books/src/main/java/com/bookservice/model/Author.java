package com.bookservice.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Author {
	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "authorid")
	private int id;
	@Column(name = "username", nullable = false)
	private String username;
	@Column(name = "email", unique = true)
	private String eMail;
	@Column(name = "password", nullable = false)
	private String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Author [id=" + id + ", username=" + username + ", eMail=" + eMail + ", password=" + password + "]";
	}
	public Author(int id, String username, String eMail, String password) {
		super();
		this.id = id;
		this.username = username;
		this.eMail = eMail;
		this.password = password;
	}
	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
