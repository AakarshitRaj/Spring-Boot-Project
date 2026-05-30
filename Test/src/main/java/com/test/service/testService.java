package com.test.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dto.testRequest;
import com.test.dto.testResponse;
import com.test.entity.Test;
import com.test.repository.testRepository;

@Service
public class testService {

	private testResponse convertToDTO(Test test) {
		testResponse dto=new testResponse();
		dto.setId(test.getId());
		dto.setName(test.getName());
		dto.setCity(test.getCity());
		
		return dto;
	}
	
	@Autowired
	private testRepository repository;
	
	//Add
	public testResponse saveTest(testRequest dto) {
		Test test=new Test();
		
		test.setName(dto.getName());
		test.setCity(dto.getCity());
		test.setPassword(dto.getPassword());
		Test savedTest=repository.save(test);
		
		return convertToDTO(test);
	}
	
	//Get All Employee
	public List<testResponse> getAllTest(){
		return repository.findAll()
							.stream()
							.map(this::convertToDTO)
							.collect(Collectors.toList());
	}
	
	
	//Get EmployeeById
	public testResponse getTestById(Long id) {

	    Test test = repository.findById(id)
	                          .orElse(null);

	    return convertToDTO(test);
	}
	
}
