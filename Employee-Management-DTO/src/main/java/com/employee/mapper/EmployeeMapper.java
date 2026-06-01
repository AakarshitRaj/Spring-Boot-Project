package com.employee.mapper;

import com.employee.DTO.EmployeeRequestDTO;
import com.employee.DTO.EmployeeResponseDTO;
import com.employee.entity.Employee;

public class EmployeeMapper {
	
	public static EmployeeResponseDTO convertToDTO(Employee employee) {
		EmployeeResponseDTO dto=new EmployeeResponseDTO();
		
		dto.setName(employee.getName());
		dto.setId(employee.getId());
		dto.setLocation(employee.getLocation());
		dto.setRole(employee.getRole());
		dto.setSalary(employee.getSalary());
		
		return dto;
		
	}
	
	public static Employee convertToEntity(EmployeeRequestDTO dto) {
		Employee employee=new Employee();
		
		employee.setName(dto.getName());
		employee.setLocation(dto.getLocation());
		employee.setRole(dto.getRole());
		employee.setSalary(dto.getSalary());
		
		return employee;
		
	}
	
	public static void updateEntityFromDTO(EmployeeRequestDTO dto,Employee employee) {

	    employee.setName(dto.getName());
	    employee.setRole(dto.getRole());
	    employee.setLocation(dto.getLocation());
	    employee.setSalary(dto.getSalary());
	}

}
