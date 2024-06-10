package com.example.shopping_Spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.shopping_Spring.entity.Products;
import com.example.shopping_Spring.service.ProductsService;

import jakarta.servlet.ServletContext;

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
