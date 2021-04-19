package com.ram.controller;

import java.util.Random;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ram.model.Employee;

@RestController
public class EmployeeController
{

	@RequestMapping(value = "/employees", method = RequestMethod.POST)
	public Employee createEmployee(@Valid @RequestBody Employee employee)
	{
		System.out.println("employee = " + employee);
		employee.setId(new Random().nextInt());
		return employee;
	}

}
