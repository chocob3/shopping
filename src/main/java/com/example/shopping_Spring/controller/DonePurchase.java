package com.example.shopping_Spring.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.shopping_Spring.entity.Products;
import com.example.shopping_Spring.entity.Register;
import com.example.shopping_Spring.service.PurchaseService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/donePurchase")
public class DonePurchase {
	@Autowired
	PurchaseService purchaseService;
	@Autowired
	HttpSession session;
	
	
	@GetMapping("/donePurchase")
	public String donePurchase() {
		Map<Products,Integer> purchaseMap=(Map<Products, Integer>) session.getAttribute("purchaseMap");
		Iterable<Products> purchaseProductList=new ArrayList<Products>();
		purchaseProductList=purchaseService.purchaseQuantityRegist(purchaseMap);
		Register register=(Register)session.getAttribute("register");
		String registername=register.getId();
		for(Products p: purchaseMap.keySet()) {
			Integer purchaseItems=purchaseMap.get(p);
			Integer purchaseProduct=p.getProductsid();
			purchaseService.registByPurchase(purchaseProduct, registername, purchaseItems);
		}
		
		session.setAttribute("purchaseProductsList",purchaseProductList);
		
		return "purchaseConfirm";
	}
	

}
