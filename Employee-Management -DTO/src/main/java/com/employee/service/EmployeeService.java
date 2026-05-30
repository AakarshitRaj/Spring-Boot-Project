package com.employee.service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.employee.DTO.EmployeeRequestDTO;
import com.employee.DTO.EmployeeResponseDTO;
import com.employee.entity.*;
import com.employee.exception.ResourceNotFoundException;
import com.employee.repository.*;

import java.util.*;
import java.util.stream.Collectors;

//for pagination+sorting
import org.springframework.data.domain.*;


@Service
public class EmployeeService {
	
	//ENTITY -> DTO
	private EmployeeResponseDTO convertToDTO(Employee employee) {
		EmployeeResponseDTO dto=new EmployeeResponseDTO();
		dto.setId(employee.getId());
		dto.setName(employee.getName());
		dto.setRole(employee.getRole());
		dto.setLocation(employee.getLocation());
		dto.setSalary(employee.getSalary());
		
		return dto;
	}

	@Autowired
	private EmployeeRepository repository;

	//Add Employee
	public EmployeeResponseDTO saveEmployee(EmployeeRequestDTO dto) {
		Employee employee=new Employee();
		
		employee.setName(dto.getName());
		employee.setRole(dto.getRole());
		employee.setLocation(dto.getLocation());
		employee.setSalary(dto.getSalary());
		
		Employee savedEmployee= repository.save(employee);
		return convertToDTO(savedEmployee);
	}
	
	//Get All Employee
	public List<EmployeeResponseDTO> getAllEmployee(){
		return repository.findAll()
				.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}
	
	//Get Employee By Id
	public EmployeeResponseDTO getEmployeeById(Long id) {
		Employee employee= repository.findById(id).
				orElseThrow(()->
				new ResourceNotFoundException("Employee Not Found with this id: "+id));
		
		return convertToDTO(employee);
	//new keyword is used because ResourceNotFoundException is a class, not a method.
	}
	//Update Employee
	public EmployeeResponseDTO updateEmployee(Long id,EmployeeRequestDTO dto) {
		Employee employee = repository.findById(id).
				orElseThrow(()->
				new ResourceNotFoundException("Employee Not Found with this id: "+id));
		
		
			 employee.setName(dto.getName());
		        employee.setRole(dto.getRole());
		        employee.setLocation(dto.getLocation());
		        employee.setSalary(dto.getSalary());

		        Employee updatedEmployee= repository.save(employee);
		        return convertToDTO(updatedEmployee);
		    }

	
	//Delete Employee
	public void deleteEmployeeById(Long id) {
		Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee Not Found with this id: " + id));

        repository.delete(employee);
	}
//Search By Role
	public List<EmployeeResponseDTO> getEmployeeByRole(String role){
		List<Employee> employees =repository.findByRole(role);
		//because it give Multiple records
		if(employees.isEmpty()) {
		throw new ResourceNotFoundException("Employee Not Found with this role: "+role);
	}
		return employees.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}
	
//Search By Location
	
	public List<EmployeeResponseDTO> getEmployeeByLocation(String location){
		List<Employee> employees =repository.findByLocation(location);
		//because it give Multiple records
		if(employees.isEmpty()) {
		throw new ResourceNotFoundException("Employee Not Found with this location: "+location);
	}
		return employees.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}
	
	//for pagination
	public Page<EmployeeResponseDTO> getEmployeesWithPagination(int page,int size){
		Pageable pageable =PageRequest.of(page,size);
		
		Page<Employee> employeePage =
                repository.findAll(pageable);

        return employeePage.map(this::convertToDTO);
	}
	
	//for sorting
	public List<EmployeeResponseDTO> getEmployeesWithSorting(String field){
		return repository.findAll(Sort.by(Sort.Direction.ASC,field))
				.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
	}
	
	//pagination+sorting
	public Page<EmployeeResponseDTO> getEmployeesWithPaginationAndSorting(int page,int size,String field){
		Pageable pageable =PageRequest.of(page,size,Sort.by(field));
		
		Page<Employee> employeePage =
                repository.findAll(pageable);

        return employeePage.map(this::convertToDTO);
	}
}
