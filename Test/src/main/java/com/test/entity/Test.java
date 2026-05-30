package com.test.entity;

import jakarta.persistence.*;

@Entity
@Table(name="test")
public class Test {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
private String password;
private String city;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
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
public Test(Long id, String name, String password, String city) {
	this.id = id;
	this.name = name;
	this.password = password;
	this.city = city;
}
public Test() {
}


}
