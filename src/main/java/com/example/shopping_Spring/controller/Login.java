package com.example.shopping_Spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.shopping_Spring.entity.Products;
import com.example.shopping_Spring.entity.Register;
import com.example.shopping_Spring.form.RegisterForm;
import com.example.shopping_Spring.service.ProductsService;
import com.example.shopping_Spring.service.RegisterService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class Login {
	@Autowired
	RegisterService registerService;
	@Autowired
	ProductsService productsService;
	@Autowired
	ServletContext application;
	@Autowired
	HttpSession session;
	@ModelAttribute 
	public RegisterForm setUpForm() {
		return new RegisterForm();
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/regist")
	public String regist(@Validated RegisterForm registerForm,BindingResult bindingResult,Model model) {
		Register register=new Register();
		Optional<Register> r=registerService.findRegister(registerForm.getId(),registerForm.getPassword());
		Iterable<Products> productsList=productsService.findAll();
		String errmsg="";
		
		if(r.isPresent()) {
			session.setAttribute("register", r);
			application.setAttribute("productsList", productsList);
			return "showProducts";
		}else {
			if(bindingResult.hasErrors()) {
				 errmsg="入力に間違いがあります";
			}else {
			 errmsg="アカウントに間違いがあります";
			 }
			model.addAttribute("errmsg", errmsg);
			return "login";
		}
	}
	

}
