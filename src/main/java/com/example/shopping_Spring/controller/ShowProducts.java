package com.example.shopping_Spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.shopping_Spring.entity.Products;
import com.example.shopping_Spring.service.ProductsService;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/showProducts")
public class ShowProducts {
	@Autowired
	ProductsService productsService;
	@Autowired
	ServletContext application;
	@GetMapping("/showProducts")
	public String showProducts() {
		
		Iterable<Products> productsList=productsService.findAll();
		application.setAttribute("productsList", productsList);
		return "showProducts";
	}

}
