package com.tcs.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.employee.beans.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	

	
}
