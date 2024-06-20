package com.example.shopping_Spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/forDeveloper")
@Controller
public class ForDeveloper {
	@GetMapping("")
	public String forDeveloper() {
		return "developer";
	}
	

}
