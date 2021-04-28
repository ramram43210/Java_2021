package com.ram.controller;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ram.model.User;

@Controller
public class FormController
{
	@GetMapping("/")
	public String index()
	{
		return "redirect:/form";
	}

	@GetMapping("/form")
	public String formGet(Model model)
	{
		model.addAttribute("user", new User());
		return "form";
	}

	@PostMapping("/form")
	public String formPost(@Valid User user, BindingResult bindingResult, Model model)
	{
		if (!bindingResult.hasErrors())
		{
			model.addAttribute("noErrors", true);
		}
		model.addAttribute("user", user);
		return "form";
	}
}
