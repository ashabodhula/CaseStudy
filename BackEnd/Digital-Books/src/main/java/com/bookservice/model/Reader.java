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
     @Column (name="paymentid")
     
	private String paymentId;
     
     private  String role ;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	@Override
	public String toString() {
		return "Reader [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", paymentId=" + paymentId + "]";
	}

	public Reader(int id, String username, String email, String password, String paymentId) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.paymentId = paymentId;
	}

	public Reader() {
		super();
		// TODO Auto-generated constructor stub
	}

}
