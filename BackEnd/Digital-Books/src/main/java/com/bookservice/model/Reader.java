package com.bookservice.model;

import javax.persistence.Column;
import javax.persistence.*;

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
		return email;
	}

	public void seteMail(String eMail) {
		this.email = eMail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Reader [id=" + id + ", username=" + username + ", eMail=" + email + ", password=" + password + "]";
	}

	public Reader(int id, String username, String eMail, String password) {
		super();
		this.id = id;
		this.username = username;
		this.email = eMail;
		this.password = password;
	}

	public Reader() {
		super();
		// TODO Auto-generated constructor stub
	}

}
