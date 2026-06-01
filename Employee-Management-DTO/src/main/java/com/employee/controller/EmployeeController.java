package com.employee.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
//for pagination
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import com.employee.DTO.EmployeeRequestDTO;
import com.employee.DTO.EmployeeResponseDTO;
import com.employee.response.ApiResponse;
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
	public ApiResponse<EmployeeResponseDTO> addEmployee(@Valid @RequestBody EmployeeRequestDTO dto) {
		
		EmployeeResponseDTO employee = service.saveEmployee(dto);
		return new ApiResponse<>(
				true,
				 "Employee created successfully",
				 employee
				);
	}
	
	//GET API
	@GetMapping
	public ApiResponse<List<EmployeeResponseDTO>> getEmployees(){
		List<EmployeeResponseDTO> employees = service.getAllEmployee();
		return new ApiResponse<>(
				true,
				 "Employee fetched successfully",
				 employees
				);
		
	}
	@GetMapping("/{id}")
	public ApiResponse<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
		EmployeeResponseDTO employee =service.getEmployeeById(id);
		return new ApiResponse<>(
				true,
				 "Employee fetched successfully",
				 employee
				);
	}
	@PutMapping("/{id}")
	public ApiResponse<EmployeeResponseDTO> updateEmployeeById(@PathVariable Long id,@Valid @RequestBody EmployeeRequestDTO dto) {
		EmployeeResponseDTO employee = service.updateEmployee(id,dto);
		return new ApiResponse<>(
				true,
				 "Employee updated successfully",
				 employee
				);
		
	}
	
	@DeleteMapping("/{id}")
	public ApiResponse<String> deleteEmployeeById(@PathVariable Long id) {
		service.deleteEmployeeById(id);
		return new ApiResponse<>(
	            true,
	            "Employee deleted successfully",
	            "Deleted"
	    );
	}
	
	@GetMapping("/location/{location}")
	public ApiResponse<List<EmployeeResponseDTO>> getEmployeeByLocation(@PathVariable String location){
		List<EmployeeResponseDTO> employees=service.getEmployeeByLocation(location);
		return new ApiResponse<>(
	            true,
	            "Employee fetched successfully based on location",
	            employees
	    );
	}
	
	@GetMapping("/role/{role}")
	public ApiResponse<List<EmployeeResponseDTO>> getEmployeeByRole(@PathVariable String role){
		List<EmployeeResponseDTO> employees= service.getEmployeeByRole(role);
		return new ApiResponse<>(
	            true,
	            "Employee fetched successfully based on role",
	            employees
	    );
	}
	
	//Pagination logic
	@GetMapping("/pagination")
	public ApiResponse<Page<EmployeeResponseDTO>> getEmployeeWithPagination(@RequestParam int page,@RequestParam int size){
		Page<EmployeeResponseDTO> p= service.getEmployeesWithPagination(page, size);
		return new ApiResponse<>(
	            true,
	            "Employee fetched in pages",
	            p
	    );
	}
	
	//Sorting logic
	@GetMapping("/sort/{field}")
	public ApiResponse<List<EmployeeResponseDTO>> getEmployeeWithSorting(@PathVariable String field){
		List<EmployeeResponseDTO> s= service.getEmployeesWithSorting(field);
		return new ApiResponse<>(
	            true,
	            "Employee sorted",
	            s
	    );
	}
	
	//pagination + sorting
	@GetMapping("/paginationAndSorting")
	public ApiResponse<Page<EmployeeResponseDTO>> getEmployeeWithPaginationAndSorting(@RequestParam int page,@RequestParam int size,@RequestParam String field){
		Page<EmployeeResponseDTO> ps= service.getEmployeesWithPaginationAndSorting(page, size,field);
		return new ApiResponse<>(
	            true,
	            "Employee fetched in pages and sorted",
	            ps
	    );
	}
	

}
