package com.ram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController
{
	
	@GetMapping({ "/" })
	public String index()
	{
		System.out.println("inside index method");
		return "index";
	}

	@GetMapping({ "/hello" })
	public String hello(Model model,
			@RequestParam(value = "name", required = false, defaultValue = "World") String name)
	{
		model.addAttribute("name", name);
		return "hello";
	}
}
