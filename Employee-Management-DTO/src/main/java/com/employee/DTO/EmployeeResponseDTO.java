package com.employee.DTO;
import java.io.Serializable;
import java.util.*;

import io.swagger.v3.oas.annotations.media.Schema;
//swagger api
@Schema(description = "Employee Request DTO")

public class EmployeeResponseDTO implements Serializable {
	
	   private static final long serialVersionUID = 1L;
	   
	 	private Long id;
	 	//for swagger- it show what i have to write as json
	 	@Schema(
	 	        description = "Employee Name",
	 	        example = "Rahul"
	 	    )
	 	
		private String name;
	 	
	 	//for swagger
	 	@Schema(
	 	        description = "Employee Role",
	 	        example = "Java Developer"
	 	    )
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
