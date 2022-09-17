package com.tcs.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.employee.beans.Employee;
import com.tcs.employee.dao.EmployeeRepository;
import com.tcs.employee.exceptions.EmployeeNotFoundException;

@Service
@CacheConfig(cacheNames = "employee_cache")
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository dao;
	
	@Override
	@Transactional
	public Employee store(Employee employee) {
		return dao.save(employee);
	}
	
	@Override
	@Cacheable(value = "employee_cache")
	public List<Employee> fetchEmployees() {
		return dao.findAll();
	}
	
	@Override
	@Cacheable(value = "employee_cache", key ="#employeeNumber")
	public Employee fetchEmployeeByEmployeeNumber(int employeeNumber) throws EmployeeNotFoundException {
		Employee emp = dao.findById(employeeNumber).orElse(null);
		if(emp==null)
			throw new EmployeeNotFoundException("Account No. "+employeeNumber+" does not exist");
		
		return emp;
	}

	@Override
	@Transactional
	@CachePut(value = "employee_cache", key = "#employeeNumber")
	public Employee updateEmployeeEmail(int employeeNumber, String email) throws EmployeeNotFoundException {
		Employee emp = fetchEmployeeByEmployeeNumber(employeeNumber);
		emp.setEmail(email);
		return emp;
	}
	
	@Override
	@Transactional
	@CacheEvict(value = "employee_cache", key = "#employeeNumber")
	public void deleteEmployeeById(int employeeNumber) throws EmployeeNotFoundException {
		dao.deleteById(employeeNumber);
	}

}
