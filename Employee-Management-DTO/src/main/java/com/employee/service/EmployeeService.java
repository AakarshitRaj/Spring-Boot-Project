package com.employee.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.employee.DTO.EmployeeRequestDTO;
import com.employee.DTO.EmployeeResponseDTO;
import com.employee.entity.*;
import com.employee.exception.ResourceNotFoundException;
import com.employee.mapper.EmployeeMapper;
import com.employee.repository.*;


import java.util.*;
import java.util.stream.Collectors;

//for pagination+sorting
import org.springframework.data.domain.*;

//For Logger
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//@Service
//public class EmployeeService {
//	
//	//ENTITY -> DTO
//	private EmployeeResponseDTO convertToDTO(Employee employee) {
//		EmployeeResponseDTO dto=new EmployeeResponseDTO();
//		dto.setId(employee.getId());
//		dto.setName(employee.getName());
//		dto.setRole(employee.getRole());
//		dto.setLocation(employee.getLocation());
//		dto.setSalary(employee.getSalary());
//		
//		return dto;
//	}
//
//	@Autowired
//	private EmployeeRepository repository;
//
//	//Add Employee
//	public EmployeeResponseDTO saveEmployee(EmployeeRequestDTO dto) {
//		Employee employee=new Employee();
//		
//		employee.setName(dto.getName());
//		employee.setRole(dto.getRole());
//		employee.setLocation(dto.getLocation());
//		employee.setSalary(dto.getSalary());
//		
//		Employee savedEmployee= repository.save(employee);
//		return convertToDTO(savedEmployee);
//	}
//	
//	//Get All Employee
//	public List<EmployeeResponseDTO> getAllEmployee(){
//		return repository.findAll()
//				.stream()
//				.map(this::convertToDTO)
//				.collect(Collectors.toList());
//	}
//	
//	//Get Employee By Id
//	public EmployeeResponseDTO getEmployeeById(Long id) {
//		Employee employee= repository.findById(id).
//				orElseThrow(()->
//				new ResourceNotFoundException("Employee Not Found with this id: "+id));
//		
//		return convertToDTO(employee);
//	//new keyword is used because ResourceNotFoundException is a class, not a method.
//	}
//	//Update Employee
//	public EmployeeResponseDTO updateEmployee(Long id,EmployeeRequestDTO dto) {
//		Employee employee = repository.findById(id).
//				orElseThrow(()->
//				new ResourceNotFoundException("Employee Not Found with this id: "+id));
//		
//		
//			 employee.setName(dto.getName());
//		        employee.setRole(dto.getRole());
//		        employee.setLocation(dto.getLocation());
//		        employee.setSalary(dto.getSalary());
//
//		        Employee updatedEmployee= repository.save(employee);
//		        return convertToDTO(updatedEmployee);
//		    }
//
//	
//	//Delete Employee
//	public void deleteEmployeeById(Long id) {
//		Employee employee = repository.findById(id)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException(
//                                "Employee Not Found with this id: " + id));
//
//        repository.delete(employee);
//	}
////Search By Role
//	public List<EmployeeResponseDTO> getEmployeeByRole(String role){
//		List<Employee> employees =repository.findByRole(role);
//		//because it give Multiple records
//		if(employees.isEmpty()) {
//		throw new ResourceNotFoundException("Employee Not Found with this role: "+role);
//	}
//		return employees.stream()
//				.map(this::convertToDTO)
//				.collect(Collectors.toList());
//	}
//	
////Search By Location
//	
//	public List<EmployeeResponseDTO> getEmployeeByLocation(String location){
//		List<Employee> employees =repository.findByLocation(location);
//		//because it give Multiple records
//		if(employees.isEmpty()) {
//		throw new ResourceNotFoundException("Employee Not Found with this location: "+location);
//	}
//		return employees.stream()
//				.map(this::convertToDTO)
//				.collect(Collectors.toList());
//	}
//	
//	//for pagination
//	public Page<EmployeeResponseDTO> getEmployeesWithPagination(int page,int size){
//		Pageable pageable =PageRequest.of(page,size);
//		
//		Page<Employee> employeePage =
//                repository.findAll(pageable);
//
//        return employeePage.map(this::convertToDTO);
//	}
//	
//	//for sorting
//	public List<EmployeeResponseDTO> getEmployeesWithSorting(String field){
//		return repository.findAll(Sort.by(Sort.Direction.ASC,field))
//				.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//	}
//	
//	//pagination+sorting
//	public Page<EmployeeResponseDTO> getEmployeesWithPaginationAndSorting(int page,int size,String field){
//		Pageable pageable =PageRequest.of(page,size,Sort.by(field));
//		
//		Page<Employee> employeePage =
//                repository.findAll(pageable);
//
//        return employeePage.map(this::convertToDTO);
//	}
//}


@Service
public class EmployeeService {
	//For Logger
	private static final Logger log =
	        LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired
	private EmployeeRepository repository;
	@CachePut(value = "employees", key = "#result.id")
	public EmployeeResponseDTO saveEmployee(EmployeeRequestDTO dto) {
		Employee employee=EmployeeMapper.convertToEntity(dto);
		
		
		// Log before saving
				log.info("Saving employee: {}", dto.getName());
				
		Employee savedEmployee=repository.save(employee);
		
		// Log after saving
		log.info("Employee saved successfully with ID: {}",savedEmployee.getId());
		
		return EmployeeMapper.convertToDTO(savedEmployee);
	}
	
	public List<EmployeeResponseDTO> getAllEmployee(){
		//Log before fetching
				log.info("Fetching employees");
				
				List<EmployeeResponseDTO> employees= repository.findAll().stream()
									.map(EmployeeMapper::convertToDTO)
									.collect(Collectors.toList());
		//Log after fetching
		 log.info("Employee found: {}", employees.size());
		 return employees;
	}
	
	//for redis cache
	@Cacheable(
	        value="employee",
	        key="#id"
	)
	public EmployeeResponseDTO getEmployeeById(Long id){
		
		 System.out.println(
		            "Fetching from Database");

		 
		//Log before fetching
		log.info("Fetching employee with ID: {}", id);
		
		Employee employee=repository.findById(id).orElseThrow(()->
				new ResourceNotFoundException(
                        "Employee Not Found with this id: " + id));
		//Log after fetching
		 log.info("Employee found: {}", employee.getName());
		 
		return EmployeeMapper.convertToDTO(employee);
	}

	@CacheEvict(
	        value="employee",
	        key="#id"
	)
	
	public void deleteEmployeeById(Long id) {
		//Log before deleting
				log.info("Deleting employee with ID: {}", id);
				
		Employee employee=repository.findById(id).orElseThrow(()->
		new ResourceNotFoundException(
                "Employee Not Found with this id: " + id));
		
		repository.delete(employee);
		
		//Log after deleting
		 log.info("Employee deleted successfully: {}", employee.getName());
	}
	
	@CachePut(value="employees", key="#id")
	public EmployeeResponseDTO updateEmployee(Long id,EmployeeRequestDTO dto) {
		//Log before updating
		log.info("Updating employee with ID: {}", id);
		
		Employee employee=repository.findById(id).orElseThrow(()->
		new ResourceNotFoundException(
                "Employee Not Found with this id: " + id));
		
			//to preserve ID
			EmployeeMapper.updateEntityFromDTO(dto, employee);
			Employee updatedEmployee=repository.save(employee);
			
			//Log after updated
			 log.info("Employee updated successfully with ID: {}", updatedEmployee.getId());
			
			return EmployeeMapper.convertToDTO(updatedEmployee);
	}
	//Search By Role
	public List<EmployeeResponseDTO> getEmployeeByRole(String role){
		//Log fetching based on role
				log.info("Fetching employee with Role: {}", role);
				
		List<Employee> employees =repository.findByRole(role);
		//because it give Multiple records
		if(employees.isEmpty()) {
			//logger
			 log.warn("No employee found for role: {}", role);
			 
		throw new ResourceNotFoundException("Employee Not Found with this role: "+role);
	}
		
		//Log fetching employees based on role
		log.info(
			    "Employees fetched for role {} : {} record(s)",
			    role,
			    employees.size()
			);
		
		return employees.stream()
				.map(EmployeeMapper::convertToDTO)
				.collect(Collectors.toList());
	}
	
//Search By Location
	
	public List<EmployeeResponseDTO> getEmployeeByLocation(String location){
		//Log fetching based on role
		log.info("Fetching employee with Location: {}", location);
		
		List<Employee> employees =repository.findByLocation(location);
		//because it give Multiple records
		if(employees.isEmpty()) {
		throw new ResourceNotFoundException("Employee Not Found with this location: "+location);
	}
		
		//Log fetched employees based on location
		log.info(
			    "Employees fetched for location {} : {} record(s)",
			    location,
			    employees.size()
			);
		 
		return employees.stream()
				.map(EmployeeMapper::convertToDTO)
				.collect(Collectors.toList());
	}
	
	//for pagination
	public Page<EmployeeResponseDTO> getEmployeesWithPagination(int page,int size){
		
		Pageable pageable =PageRequest.of(page,size);
		//Log fetching on page 
		log.info("Fetching employee page: {}, size: {}", page, size);
		
		Page<Employee> employeePage =
                repository.findAll(pageable);
		
		//Log fetched employees based on page with size
		log.info("Employee fetched page: {}, size: {}", page, size);
		 
        return employeePage.map(EmployeeMapper::convertToDTO);
	}
	
	//for sorting
	public List<EmployeeResponseDTO> getEmployeesWithSorting(String field){
		//Log Sorting employee based on field
				log.info("Sorting employee based on :{}", field);
				
				List<EmployeeResponseDTO> employee= repository.findAll(Sort.by(Sort.Direction.ASC,field))
				.stream()
                .map(EmployeeMapper::convertToDTO)
                .collect(Collectors.toList());
				
				//Log Sorted employee based on field
				 log.info("Sorted employee based on :{}", field);
				 
				return employee;
	}
	
	//pagination+sorting
	public Page<EmployeeResponseDTO> getEmployeesWithPaginationAndSorting(int page,int size,String field){
		Pageable pageable =PageRequest.of(page,size,Sort.by(field));
		//Log Pagination Sorting employee based on field
		log.info(
			    "Fetching page: {}, size: {}, sorted by: {}",
			    page,
			    size,
			    field
			);
		
		Page<Employee> employeePage =
                repository.findAll(pageable);
		
		//Log pagination and Sorted employee based on field
		log.info(
			    "Employees fetched page: {}, size: {}, sorted by: {}",
			    page,
			    size,
			    field
			);
		 
        return employeePage.map(EmployeeMapper::convertToDTO);
	}
}
