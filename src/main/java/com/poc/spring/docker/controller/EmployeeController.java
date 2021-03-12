package com.poc.spring.docker.controller;

import com.poc.spring.docker.model.domain.Employee;
import com.poc.spring.docker.model.dto.EmployeeRequest;
import com.poc.spring.docker.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "View a list of available employees", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping
    @Async
    public CompletableFuture<ResponseEntity<List<Employee>>> getAllEmployeesAsync() throws ExecutionException, InterruptedException {
        return CompletableFuture.completedFuture(
                ResponseEntity.ok(
                        getEmployees()
                )
        );
    }

    private List<Employee> getEmployees() throws ExecutionException, InterruptedException {
        CompletableFuture<List<Employee>> completableFuture = employeeService.findEmployeesAsync();
        return completableFuture.get();
    }

    @ApiOperation(value = "Get an employee by Id")
    @GetMapping("/{id}")
    @Async
    public CompletableFuture<ResponseEntity<Employee>> getEmployeeByIdAsync(
            @ApiParam(value = "Employee id from which employee object will retrieve", required = true) @PathVariable UUID id) throws ExecutionException, InterruptedException {
        return CompletableFuture.completedFuture(
                ResponseEntity.ok(
                        employeeService.findEmployeeByIdAsync(id).get()
                )
        );
    }

    @ApiOperation(value = "Add an employee")
    @PostMapping
    @Async
    public CompletableFuture<ResponseEntity<Employee>> createNewEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) throws ExecutionException, InterruptedException {
        return CompletableFuture.completedFuture(
                new ResponseEntity<>(
                        employeeService.addEmployeeAsync(employeeRequest).get(),
                        HttpStatus.CREATED)
        );
    }

    @ApiOperation(value = "Update an employee")
    @PutMapping("/{id}")
    @Async
    public CompletableFuture<ResponseEntity<Employee>> updateEmployeeAsync(@PathVariable UUID id,@Valid @RequestBody EmployeeRequest employeeRequest) throws ExecutionException, InterruptedException {
        return CompletableFuture.completedFuture(
                ResponseEntity.ok(
                        employeeService.updateEmployeeAsync(id,employeeRequest).get()
                )
        );
    }

    @ApiOperation(value = "Delete an employee")
    @DeleteMapping("/{id}")
    @Async
    public CompletableFuture<ResponseEntity<Employee>> deleteEmployeeByIdAsync(@PathVariable UUID id) throws ExecutionException, InterruptedException {
        return CompletableFuture.completedFuture(
                ResponseEntity.ok(
                        employeeService.deleteEmployeeByIdAsync(id).get()
                )
        );
    }
}