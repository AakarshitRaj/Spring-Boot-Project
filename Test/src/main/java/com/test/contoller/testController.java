package com.test.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.test.dto.testRequest;
import com.test.dto.testResponse;
import com.test.service.testService;

import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/test")
public class testController {
@Autowired
private testService service;

@PostMapping
public testResponse addTest(@Valid @RequestBody testRequest test) {
	return service.saveTest(test);
}

@GetMapping
public List<testResponse> getTest(){
	return service.getAllTest();
}

@GetMapping("/{id}")
public testResponse getTestById(@PathVariable Long id) {
	return service.getTestById(id);
}


	
}
