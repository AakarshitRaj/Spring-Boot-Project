package com.employee.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.employee.entity.Employee;
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
	public Employee addEmployee(@Valid @RequestBody Employee employee) {
		return service.saveEmployee(employee);
	}
	
	//GET API
	@GetMapping
	public List<Employee> getEmployees(){
		return service.getAllEmployee();
	}
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable Long id) {
		return service.getEmployeeById(id);
	}
	@PutMapping("/{id}")
	public Employee updateEmployeeById(@PathVariable Long id,@RequestBody Employee employee) {
		return service.updateEmployee(id,employee);
	}
	
	@DeleteMapping("/{id}")
	public String deleteEmployeeById(@PathVariable Long id) {
		service.deleteEmployeeById(id);
		return "Deleted Successfully";
	}
	
	@GetMapping("/location/{location}")
	public List<Employee> getEmployeeByLocation(@PathVariable String location){
		return service.getEmployeeByLocation(location);
	}
	
	@GetMapping("/role/{role}")
	public List<Employee> getEmployeeByRole(@PathVariable String role){
		return service.getEmployeeByRole(role);
	}
	

}
