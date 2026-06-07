package com.employee.entity;

import java.time.Instant;

import jakarta.persistence.*;

@Entity
public class RefreshToken {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String token;
	private Instant expiryDate;
	private String username;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Instant getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Instant expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public RefreshToken(Long id, String token, Instant expiryDate, String username) {
		this.id = id;
		this.token = token;
		this.expiryDate = expiryDate;
		this.username = username;
	}
	public RefreshToken() {
	}
	
	
}
