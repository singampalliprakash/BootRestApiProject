package com.sathya.restapiproject.controller;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sathya.restapiproject.data.Employee;
import com.sathya.restapiproject.data.ErrorResponse;
import com.sathya.restapiproject.service.EmployeeService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@PostMapping("/saveemployee")
	public ResponseEntity<Employee> saveEmployeeData(@RequestBody Employee employee) 
	{

		Employee emp= employeeService.saveEmployeeData(employee);
		return (ResponseEntity<Employee>) ResponseEntity.status(HttpStatus.CREATED)
				.header("info", "data saved succesfully---")
				.body(emp);
	}
	
	@PostMapping("/saveallemployee")
    public ResponseEntity<List<Employee>> saveallEmployeeData(@RequestBody List<Employee> employees) {
        List<Employee> emps = employeeService.saveEmployeesData(employees);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("info", "Data saved successfully")
                .body(emps);
	}
	
	@GetMapping("/getempbyid/{id}")
    public ResponseEntity<?> getEmpBy(@PathVariable long id) {
        Optional<Employee> emp = employeeService.getEmployeeById(id);

        if (emp.isPresent()) {
            Employee employee = emp.get();
            return ResponseEntity.status(HttpStatus.OK)
                    .header("info", "Data retrieved successfully")
                    .body(employee);
        } else {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setStatuscode(HttpStatus.NOT_FOUND.value());
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setErrormessage("Employee ID not found: " + id);

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("info", "Employee not found")
                    .body(errorResponse);
        }
    }
	
	
	@GetMapping("/getempbyemail/{email}")
	public ResponseEntity<?> getEmpByEmail(@PathVariable String email) {
	    Optional<Employee> emp = employeeService.getEmployeeByEmail(email);

	    if (emp.isPresent()) {
	    	Employee employee=emp.get();
	        return ResponseEntity.status(HttpStatus.OK)
	                .header("info", "Data retrieved successfully")
	                .body(employee);
	    } else {
	        ErrorResponse errorResponse = new ErrorResponse();
	        errorResponse.setStatuscode(HttpStatus.NOT_FOUND.value());
	        errorResponse.setTimestamp(LocalDateTime.now());
	        errorResponse.setErrormessage("Employee not found with email: " + email);

	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .header("info", "Employee not found")
	                .body(errorResponse);
	    }
	}
	
	@GetMapping("/GetEmpByDeptAndGender")
	public ResponseEntity<List<Employee>> findByDeptAndGender(@RequestParam String dept,String gender) 
	{
	    List<Employee> emp = employeeService.getEmployeeByDeptAndGender(dept,gender);
	        return ResponseEntity.status(HttpStatus.OK)
	                .header("info", "Data retrieved successfully")
	                .body(emp);
	    }
	
	@GetMapping("/getEmpByDeptOrGender")
	public ResponseEntity<List<Employee>> findByDeptOrGender(@RequestParam String dept,String gender) 
	{
	    List<Employee> emp = employeeService.getEmployeeByDeptOrGender(dept,gender);
	        return ResponseEntity.status(HttpStatus.OK)
	                .header("info", "Data retrieved successfully")
	                .body(emp);
	    }
	@GetMapping("/getEmpBySalaryBetween")
	public ResponseEntity<List<Employee>> findBySalaryBetween(@RequestParam("min") int min,@RequestParam("max") int max) 
	{
	    List<Employee> emp = employeeService.getEmpBySalaryBetween(min,max);
	        return ResponseEntity.status(HttpStatus.OK)
	                .header("info", "Data retrieved successfully")
	                .body(emp);
	    }
	@GetMapping("/getEmpBySalaryGreaterThan")
	public ResponseEntity<List<Employee>> findBySalaryGreaterThan(@RequestParam double salary) 
	{
	    List<Employee> emp = employeeService.getEmpBySalaryGreaterThan(salary);
	        return ResponseEntity.status(HttpStatus.OK)
	                .header("info", "Data retrieved successfully")
	                .body(emp);
	    }
	
	@GetMapping("/getEmpBySalaryLessThan")
	public ResponseEntity<List<Employee>> findBySalaryLessThan(@RequestParam double salary) 
	{
	    List<Employee> emp = employeeService.getEmpBySalaryLessThan(salary);
	        return ResponseEntity.status(HttpStatus.OK)
	                .header("info", "Data retrieved successfully")
	                .body(emp);
	    }
	
	@DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id) {
         Boolean status= employeeService.deleteById(id);

        if (status) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .header("info", "Data deleted successfully by ID")
                    .build();
            }
        else 
        {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setStatuscode(HttpStatus.NOT_FOUND.value());
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setErrormessage("Data is not found based on the id " + id);

            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .header("info", "Data is not found based on the id"+id)
                    .body(errorResponse);
        }
	}
	
	
	@DeleteMapping("/deleteByEmail/{email}")
	public ResponseEntity<?> deleteByEmail(@PathVariable String email) {
	    Boolean status = employeeService.deleteByEmail(email);

	    if (status) {
	        return ResponseEntity.status(HttpStatus.NO_CONTENT)
	                .header("info", "Data deleted successfully by email")
	                .build();
	    } else {
	        ErrorResponse errorResponse = new ErrorResponse();
	        errorResponse.setStatuscode(HttpStatus.NOT_FOUND.value());
	        errorResponse.setTimestamp(LocalDateTime.now());
	        errorResponse.setErrormessage("Employee not found with email: " + email);

	        return ResponseEntity.status(HttpStatus.NOT_FOUND) 
	                .header("info", "Employee not found")
	                .body(errorResponse);
	    }
	}

        
        @DeleteMapping("/employeall")
        public ResponseEntity<?> deleteAll() {
             Boolean status= employeeService.deleteAll();

            if (status) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .header("info", "Data deleted successfully by ID")
                        .build();
                }
            else 
            {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setStatuscode(HttpStatus.NOT_FOUND.value());
                errorResponse.setTimestamp(LocalDateTime.now());
                errorResponse.setErrormessage("Data is not found based on the id ");

                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .header("info", "Data is not found based on the id")
                        .body(errorResponse);
            }
    }
        
        
        
        
        @PutMapping("/employeeupdate/{id}")
        public ResponseEntity<?> updateById(@PathVariable long id,@RequestBody Employee employee) {
             Employee emp= employeeService.updateById(id,employee);

            if (emp!=null) {
                return ResponseEntity.status(HttpStatus.OK)
                        .header("info", "Data updated successfully by ID")
                        .body(employee);
                }
            else 
            {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setStatuscode(HttpStatus.NOT_FOUND.value());
                errorResponse.setTimestamp(LocalDateTime.now());
                errorResponse.setErrormessage("Data is not found based on the id " + id);

                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .header("info", "Data is not found based on the id"+id)
                        .body(errorResponse);
            }
    	}
	
	
	
	
	
	
	}
	
	


	






	
	
	


