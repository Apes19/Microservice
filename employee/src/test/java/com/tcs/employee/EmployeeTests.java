package com.tcs.employee;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.tcs.employee.beans.Employee;
import com.tcs.employee.dao.EmployeeRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EmployeeTests {
	
	@Autowired
	private EmployeeRepository repo;
	
	@Test
	@Rollback(false)
	@Order(1)
	public void testCreateEmployee(){
		
		Employee emp = new Employee("Ramesh","Kumar","ramesh@gmail.com");
		Employee savedEmployee = repo.save(emp);
		assertThat(savedEmployee.getId()).isGreaterThan(0);
	}
	
	@Test
	@Rollback(false)
	@Order(2)
	public void testFindEmployeeByIdExists() {
		int id = 3;
		Employee emp1 = repo.findById(id).orElse(null);
		assertThat(emp1.getId()).isEqualTo(id);
	}
	
	@Test
	@Rollback(false)
	@Order(3)
	public void testFindAllEmployees() {
		List<Employee> employees = repo.findAll();
		for(Employee emp: employees) {
			System.out.println(emp);
		}
		assertThat(employees).size().isGreaterThan(0);
		
	}
	
	@Test
	@Rollback(false)
	@Order(4)
	public void testUpdateEmail() {
		Employee emp = repo.findById(3).orElse(null);
	    emp.setEmail("albin@gmail.com"); 
	    repo.save(emp);  
	    
	    Employee updatedEmployee = repo.findById(3).orElse(null); 
	    
	    assertThat(updatedEmployee.getEmail()).isEqualTo("albin@gmail.com");
	}
	
	@Test
	@Rollback(false)
	@Order(5)
	public void testDeleteEmployee() {
		int id = 10;
		boolean present1 = repo.findById(id).isPresent();
		repo.deleteById(id);
		boolean present2 = repo.findById(id).isPresent();
		assertTrue(present1);
		assertFalse(present2);
				
	}
	

}
