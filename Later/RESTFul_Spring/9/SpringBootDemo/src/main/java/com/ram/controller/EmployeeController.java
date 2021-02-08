package com.ram.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ram.model.Employee;

@RestController
public class EmployeeController
{

	private static Map<Integer, Employee> employeeMap = new HashMap<Integer, Employee>();
	private static int key = 0;

	@RequestMapping(value = "/employee",
			method = RequestMethod.POST,
			produces = { MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee)
	{

		System.out.println("employee = " + employee);
		employee.setId(++key);
		employeeMap.put(key, employee);
		System.out.println("employeeMap = " + employeeMap);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@RequestMapping(value = "/employee/{employeeId}",
			method = RequestMethod.GET,
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Employee> getEmployee(@PathVariable int employeeId)
	{
		if (employeeMap.containsKey(employeeId))
		{
			Employee employee = employeeMap.get(employeeId);
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
