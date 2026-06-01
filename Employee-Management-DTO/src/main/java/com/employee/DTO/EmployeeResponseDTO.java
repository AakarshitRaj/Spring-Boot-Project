package com.employee.DTO;
import java.util.*;
public class EmployeeResponseDTO {
	 	private Long id;

		private String name;

	    private String role;

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
		
		public EmployeeResponseDTO(Long id, String name, String role, String location, Long salary) {
			this.id = id;
			this.name = name;
			this.role = role;
			this.location = location;
			this.salary = salary;
		}
		public EmployeeResponseDTO() {
		}


}
