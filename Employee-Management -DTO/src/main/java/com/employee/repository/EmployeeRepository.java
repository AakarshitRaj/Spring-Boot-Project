package com.employee.repository;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.entity.*;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{
//Search APIs
	List<Employee> findByRole(String role);
	List<Employee> findByLocation(String location);

}
