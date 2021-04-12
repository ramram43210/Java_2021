package com.ram.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController
{

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public String getEmployees(@RequestParam(value = "page") int pageValue,
			@RequestParam(value = "limit") int limitValue)
	{

		return "getEmployees method was called with page=" + pageValue + " ,and limit="
				+ limitValue;
	}

}
