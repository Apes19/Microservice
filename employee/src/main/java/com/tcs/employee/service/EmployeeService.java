package com.tcs.employee.service;

import java.util.List;

import com.tcs.employee.beans.Employee;
import com.tcs.employee.exceptions.EmployeeNotFoundException;



public interface EmployeeService {
	
	public List<Employee> fetchEmployees();
	public Employee store(Employee employee);
	public Employee fetchEmployeeByEmployeeNumber(int employeeNumber) throws EmployeeNotFoundException;
	public Employee updateEmployeeEmail(int employeeNumber, String email) throws EmployeeNotFoundException;
	public void deleteEmployeeById(int employeeNumber) throws EmployeeNotFoundException;

}
