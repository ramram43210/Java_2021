package com.ram.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController
{

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public String getEmployees(@RequestParam(value = "page", defaultValue = "1") int pageValue,
			@RequestParam(value = "limit", defaultValue = "20") int limitValue,
			@RequestParam(value = "sort", required = true) String sortValue)
	{

		return "getEmployees method was called with page=" + pageValue + " ,limit=" + limitValue
				+ " ,sort=" + sortValue;
	}
	
}
