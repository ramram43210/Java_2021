package com.ram.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

	private static Map<Integer, Employee> employeeMap = new HashMap<Integer, Employee>();

	@RequestMapping(value = "/employee/{employeeId}",
			method = RequestMethod.GET,
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Employee> getEmployee(@PathVariable int employeeId)
	{
		/*
		 * String employeeName = null; int length =
		 * employeeName.length();
		 */
		
		if(true)
		{
			throw new EmployeeServiceException("Employee Service Exception");
		}
		
		if (employeeMap.containsKey(employeeId))
		{
			Employee employee = employeeMap.get(employeeId);
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
