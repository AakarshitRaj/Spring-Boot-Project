package com.student.service;

import com.student.entity.Student;
import com.student.repository.StudentRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.student.repository.*;
@Service
public class StudentService{
	@Autowired
	private StudentRepository repository;
	
	public Student saveStudent(Student student) {
		return repository.save(student);
	}
	
	public List<Student> getAllStudents(){
		return repository.findAll();
	}
}
