package com.student.controller;

import com.student.entity.Student;
import com.student.service.StudentService;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/students")
public class  StudentController{
	 
	@Autowired
	private StudentService service;
	
	@PostMapping
	public Student addStudent(@RequestBody Student student) {
		return service.saveStudent(student);
	}
	
	@GetMapping
	public List<Student> getStudents(){
		return service.getAllStudents();
	}
}
