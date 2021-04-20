package com.ram.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
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

	@RequestMapping(value = "/employees", method = RequestMethod.POST)
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee)
	{

		System.out.println("employee = " + employee);
		employee.setId(++key);
		employeeMap.put(employee.getId(), employee);
		System.out.println("employeeMap = " + employeeMap);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@RequestMapping(value = "/employees/{employeeId}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployee(@PathVariable int employeeId)
	{
		if (employeeMap.containsKey(employeeId))
		{
			Employee employee = employeeMap.get(employeeId);
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/employees/{employeeId}", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmployee(@PathVariable int employeeId, @RequestBody Employee employee)
	{
		if (employeeMap.containsKey(employeeId))
		{
			Employee storedEmployee = employeeMap.get(employeeId);
			storedEmployee.setName(employee.getName());
			storedEmployee.setAge(employee.getAge());
			storedEmployee.setEmail(employee.getEmail());
			storedEmployee.setSalary(employee.getSalary());

			employeeMap.put(employeeId, storedEmployee);
			return new ResponseEntity<Employee>(storedEmployee, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/employees/{employeeId}", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteEmployee(@PathVariable int employeeId)
	{
		if (employeeMap.containsKey(employeeId))
		{
			employeeMap.remove(employeeId);
			return new ResponseEntity<Employee>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
