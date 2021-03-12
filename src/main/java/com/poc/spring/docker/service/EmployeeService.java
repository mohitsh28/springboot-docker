package com.poc.spring.docker.service;

import com.poc.spring.docker.model.domain.Employee;
import com.poc.spring.docker.model.dto.EmployeeRequest;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface EmployeeService {
    CompletableFuture<Employee> addEmployeeAsync(EmployeeRequest employeeRequest);

    CompletableFuture<List<Employee>> findEmployeesAsync();

    CompletableFuture<Employee> findEmployeeByIdAsync(UUID id);

    CompletableFuture<Employee> deleteEmployeeByIdAsync(UUID id);

    CompletableFuture<Employee> updateEmployeeAsync(UUID id,EmployeeRequest employeeRequest);
}
