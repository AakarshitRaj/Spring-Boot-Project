package com.test.dto;

public class testRequest {
	private String name;
	private String password;
	private String city;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public testRequest(String name, String password, String city) {
		this.name = name;
		this.password = password;
		this.city = city;
	}
	public testRequest() {
	}
}
