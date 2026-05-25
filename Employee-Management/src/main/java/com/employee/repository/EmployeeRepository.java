package com.employee.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.entity.*;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{


}
