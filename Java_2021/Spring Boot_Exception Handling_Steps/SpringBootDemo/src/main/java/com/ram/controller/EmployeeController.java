package com.ram.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ram.exception.EmployeeServiceException;
import com.ram.model.Employee;

@RestController
public class EmployeeController
{

	@RequestMapping(value = "/employees/{employeeId}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployee(@PathVariable int employeeId)
	{

		/*
		 * if(true) { throw new
		 * EmployeeServiceException("Employee Service Exception"); }
		 */

		String employeeName = null;
		int length = employeeName.length();

		return new ResponseEntity<>(new Employee("Peter", "Peter@gmail.com"),
				HttpStatus.NO_CONTENT);
	}

}
