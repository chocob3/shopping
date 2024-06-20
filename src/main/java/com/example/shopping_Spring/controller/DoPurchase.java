package com.example.shopping_Spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.shopping_Spring.entity.Products;
import com.example.shopping_Spring.form.ProductForm;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/purchase")
public class DoPurchase {
	@Autowired
	ServletContext application;
	@Autowired
	HttpSession session;
	@ModelAttribute
	public ProductForm setUpForm() {
		return new ProductForm();
	}
	@GetMapping("/purchase")
	public String purchse() {
		return "purchase";
	}
	@PostMapping("/check")
	public String check(HttpServletRequest request,Model model) {
		Iterable<Products> productsList=(Iterable<Products>) application.getAttribute("productsList");
		Map<Products,Integer> purchaseMap=new HashMap<Products,Integer>();
		String errmsg=" ";
		
		for(Products p:productsList) {
			if(p.getQuantity()>0) {
				String str=p.getProductsname();
				Integer sales=Integer.parseInt(request.getParameter(str));
				
				if(sales!=0) {
					purchaseMap.put(p,sales);
				}
			}
		}
	
		for(Integer val: purchaseMap.values()) {
			if(val!=0) {
				session.setAttribute("purchaseMap", purchaseMap);
				return "purchaseOk";
			}
		}
		errmsg="値が入力されていません";
		model.addAttribute("errmsg", errmsg);
		return "purchase";
		}

}
