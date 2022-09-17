package com.tcs.employee.exceptions;

@SuppressWarnings("serial")

public class EmployeeNotFoundException extends Exception {
	
public EmployeeNotFoundException() {
		
		super();
		
	}

	public EmployeeNotFoundException(String message) {
		
		super(message);
		
	}


}
