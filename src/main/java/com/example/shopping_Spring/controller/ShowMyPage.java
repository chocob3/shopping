package com.example.shopping_Spring.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.shopping_Spring.entity.Purchase;
import com.example.shopping_Spring.entity.Register;
import com.example.shopping_Spring.service.PurchaseService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/showMyPage")
public class ShowMyPage {
	@Autowired
	PurchaseService purchaseService;
	@Autowired
	HttpSession session;
	 @GetMapping("/showMyPage")
	public String showMyPage(Model model) {
		 Register user=(Register)session.getAttribute("register");
		 Map<Purchase,String> purchaseMap=new HashMap<Purchase,String>();
		 
		 String msg="";
		  
		  try {
			  
			  Iterable<Purchase> list=purchaseService.choosePrivatePurchaseList(user);
			  List<Purchase> privateList=new ArrayList<>();
			  list.forEach(privateList::add);
			  Collections.sort(privateList);
			  purchaseMap=purchaseService.choosePurchaseListWithProductName(privateList);
			  session.setAttribute("purchaseMap",purchaseMap);
			  if(privateList.isEmpty()) {msg="購入履歴はございません";}
			  }catch(NullPointerException e) {
				  msg="購入履歴はございません";
				  }
		  
		  model.addAttribute("msg",msg);
		  return "myPage";
	}
	

}
