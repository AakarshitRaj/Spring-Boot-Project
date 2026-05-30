package com.employee.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
//for pagination
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import com.employee.DTO.EmployeeRequestDTO;
import com.employee.DTO.EmployeeResponseDTO;
import com.employee.service.EmployeeService;

import jakarta.validation.*;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	//POST API
	@PostMapping
	//@Valid used for validation
	public EmployeeResponseDTO addEmployee(@Valid @RequestBody EmployeeRequestDTO employee) {
		return service.saveEmployee(employee);
	}
	
	//GET API
	@GetMapping
	public List<EmployeeResponseDTO> getEmployees(){
		return service.getAllEmployee();
	}
	@GetMapping("/{id}")
	public EmployeeResponseDTO getEmployeeById(@PathVariable Long id) {
		return service.getEmployeeById(id);
	}
	@PutMapping("/{id}")
	public EmployeeResponseDTO updateEmployeeById(@PathVariable Long id,@RequestBody EmployeeRequestDTO employee) {
		return service.updateEmployee(id,employee);
	}
	
	@DeleteMapping("/{id}")
	public String deleteEmployeeById(@PathVariable Long id) {
		service.deleteEmployeeById(id);
		return "Deleted Successfully";
	}
	
	@GetMapping("/location/{location}")
	public List<EmployeeResponseDTO> getEmployeeByLocation(@PathVariable String location){
		return service.getEmployeeByLocation(location);
	}
	
	@GetMapping("/role/{role}")
	public List<EmployeeResponseDTO> getEmployeeByRole(@PathVariable String role){
		return service.getEmployeeByRole(role);
	}
	
	//Pagination logic
	@GetMapping("/pagination")
	public Page<EmployeeResponseDTO> getEmployeeWithPagination(@RequestParam int page,@RequestParam int size){
		return service.getEmployeesWithPagination(page, size);
	}
	
	//Sorting logic
	@GetMapping("/sort/{field}")
	public List<EmployeeResponseDTO> getEmployeeWithSorting(@PathVariable String field){
		return service.getEmployeesWithSorting(field);
	}
	
	//pagination + sorting
	@GetMapping("/paginationAndSorting")
	public Page<EmployeeResponseDTO> getEmployeeWithPaginationAndSorting(@RequestParam int page,@RequestParam int size,@RequestParam String field){
		return service.getEmployeesWithPaginationAndSorting(page, size,field);
	}
	

}
