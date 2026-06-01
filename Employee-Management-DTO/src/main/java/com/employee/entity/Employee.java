package com.employee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name="employees")
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	//Not blank is type of validation
	@NotBlank(message="Name is required")
	private String name;
	
	@NotBlank(message="Role is required")
	private String role;
	
	@NotBlank(message="Location is required")
	private String location;
	
	
	private Long salary;
	
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	
	
	public Employee(){
		
	}
	
	public Employee(Long id, String name, String role, String location, Long salary) {
		this.id = id;
		this.name = name;
		this.role = role;
		this.location = location;
		this.salary = salary;
	}
	
	
	
	
}
