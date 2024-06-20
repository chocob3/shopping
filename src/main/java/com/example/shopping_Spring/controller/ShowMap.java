package com.example.shopping_Spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/showMap")
public class ShowMap {
	@GetMapping("")
	public String showMap() {
		return "showMap";
		
	}
	

}
