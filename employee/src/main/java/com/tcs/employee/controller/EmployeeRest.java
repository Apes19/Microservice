package com.tcs.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.employee.beans.CustomResponse;
import com.tcs.employee.beans.Employee;
import com.tcs.employee.exceptions.EmployeeNotFoundException;
import com.tcs.employee.service.EmployeeService;




@RestController
@RequestMapping("employee")

public class EmployeeRest {
	
	@Autowired
	private EmployeeService service;
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveEmployee(@RequestBody Employee employee){
		ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).body(service.store(employee));
		return response;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getEmployees() {
		return ResponseEntity.status(HttpStatus.OK).body(service.fetchEmployees());
	}
	
	@GetMapping(path = "{employeenumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findEmployee(@PathVariable("employeenumber") int employeeNumber){
		ResponseEntity<Object> response = null;
		try {
			Employee employee = service.fetchEmployeeByEmployeeNumber(employeeNumber);
			response = ResponseEntity.status(HttpStatus.OK).body(employee);
		}catch(EmployeeNotFoundException e) {
			CustomResponse data = new CustomResponse();
			data.setMsg(e.getMessage());
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
		}
		return response;
	}
	
	@PutMapping(path = "{employeenumber}/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateEmployeeEmail(@PathVariable("employeenumber") int employeeNumber, @PathVariable("email") String email){
		ResponseEntity<Object> response = null;
		try {
			Employee employee = service.updateEmployeeEmail(employeeNumber,email);
			response = ResponseEntity.status(HttpStatus.OK).body(employee);
		}catch(EmployeeNotFoundException e) {
			CustomResponse data = new CustomResponse();
			data.setMsg(e.getMessage());
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
		}
		return response;
	}
	
	@DeleteMapping(path = "{employeenumber}")
	public ResponseEntity<Object> deleteEmployeeById(@PathVariable("employeenumber") int employeeNumber) {
		ResponseEntity<Object> response = null;
		try {
	    service.deleteEmployeeById(employeeNumber);
		response = ResponseEntity.status(HttpStatus.OK).body("");
	}
		catch(EmployeeNotFoundException e){
			CustomResponse data = new CustomResponse();
			data.setMsg(e.getMessage());
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
		}
		return response;
	}

}
