package com.ram.viewcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserViewController
{
	
	@RequestMapping("/add-users")
	public String addUsers()
	{
		return "add-users";
	}

}
