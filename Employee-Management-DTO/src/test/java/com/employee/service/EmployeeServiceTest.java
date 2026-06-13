package com.employee.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import com.employee.DTO.EmployeeRequestDTO;
import com.employee.DTO.EmployeeResponseDTO;
import com.employee.entity.Employee;
import com.employee.exception.ResourceNotFoundException;
import com.employee.repository.EmployeeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeService service;

    private Employee employee;
    private EmployeeRequestDTO requestDTO;

    @BeforeEach
    void setup() {

        employee = new Employee();

        employee.setId(1L);
        employee.setName("Rahul");
        employee.setRole("Java Developer");
        employee.setLocation("Kolkata");
        employee.setSalary(50000L);

        requestDTO = new EmployeeRequestDTO();

        requestDTO.setName("Rahul");
        requestDTO.setRole("Java Developer");
        requestDTO.setLocation("Kolkata");
        requestDTO.setSalary(50000L);
    }

    @Test
    void shouldReturnEmployeeById() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(employee));

        EmployeeResponseDTO result =
                service.getEmployeeById(1L);

        assertNotNull(result);
        assertEquals("Rahul", result.getName());

        verify(repository, times(1))
                .findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenEmployeeNotFound() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.getEmployeeById(1L)
        );
    }

    @Test
    void shouldSaveEmployee() {

        when(repository.save(any(Employee.class)))
                .thenReturn(employee);

        EmployeeResponseDTO result =
                service.saveEmployee(requestDTO);

        assertNotNull(result);
        assertEquals("Rahul", result.getName());

        verify(repository, times(1))
                .save(any(Employee.class));
    }

    @Test
    void shouldDeleteEmployee() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(employee));

        service.deleteEmployeeById(1L);

        verify(repository, times(1))
                .delete(employee);
    }

    @Test
    void shouldUpdateEmployee() {

        EmployeeRequestDTO updateDTO =
                new EmployeeRequestDTO();

        updateDTO.setName("Updated Rahul");
        updateDTO.setRole("Senior Developer");
        updateDTO.setLocation("Delhi");
        updateDTO.setSalary(90000L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(employee));

        when(repository.save(any(Employee.class)))
                .thenReturn(employee);

        EmployeeResponseDTO result =
                service.updateEmployee(1L, updateDTO);

        assertNotNull(result);

        verify(repository, times(1))
                .save(any(Employee.class));
    }

    @Test
    void shouldReturnAllEmployees() {

        Employee employee2 = new Employee();

        employee2.setId(2L);
        employee2.setName("Akash");
        employee2.setRole("Tester");
        employee2.setLocation("Delhi");
        employee2.setSalary(40000L);

        when(repository.findAll())
                .thenReturn(
                        Arrays.asList(
                                employee,
                                employee2
                        )
                );

        var employees =
                service.getAllEmployee();

        assertEquals(2,
                employees.size());

        verify(repository, times(1))
                .findAll();
    }

    @Test
    void shouldReturnEmployeesByRole() {

        when(repository.findByRole("Java Developer"))
                .thenReturn(
                        Arrays.asList(employee)
                );

        var employees =
                service.getEmployeeByRole(
                        "Java Developer"
                );

        assertEquals(1,
                employees.size());
    }

    @Test
    void shouldThrowExceptionWhenRoleNotFound() {

        when(repository.findByRole("Manager"))
                .thenReturn(
                        Arrays.asList()
                );

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.getEmployeeByRole("Manager")
        );
    }

    @Test
    void shouldReturnEmployeesByLocation() {

        when(repository.findByLocation("Kolkata"))
                .thenReturn(
                        Arrays.asList(employee)
                );

        var employees =
                service.getEmployeeByLocation(
                        "Kolkata"
                );

        assertEquals(1,
                employees.size());
    }

    @Test
    void shouldThrowExceptionWhenLocationNotFound() {

        when(repository.findByLocation("Mumbai"))
                .thenReturn(
                        Arrays.asList()
                );

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.getEmployeeByLocation("Mumbai")
        );
    }
}