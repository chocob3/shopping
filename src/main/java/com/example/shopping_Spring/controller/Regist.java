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

import com.example.shopping_Spring.entity.Register;
import com.example.shopping_Spring.form.NewRegisterForm;
import com.example.shopping_Spring.service.RegisterService;

@Controller
@RequestMapping("/regist")
public class Regist {
	@Autowired
	RegisterService registerService;
	@ModelAttribute
	public NewRegisterForm setUpForm() {
		return new NewRegisterForm();
	}
	
	@GetMapping("/regist")
	public String regist() {
		return "registForm";
	}
	@PostMapping("/check")
	public String check(@Validated NewRegisterForm newregisterForm,BindingResult bindingResult, Model model){
		    String registerrmsg="";
		if(bindingResult.hasErrors()|!newregisterForm.getPassword().equals(newregisterForm.getPassword2()) ){
			 registerrmsg="入力に間違いがあります";
		}else {
			Optional<Register> r=registerService.findRegisterField(newregisterForm.getId(),newregisterForm.getPassword(),newregisterForm.getMail());
			if(r.isEmpty()) {
				Register register=registerService.createRegister(newregisterForm);
				registerService.registByRegist(register);
				return "registOk";
			}else {
				registerrmsg="既にアカウントは存在します";
			}
		}
		model.addAttribute("registerrmsg",registerrmsg);
		return "registForm";
		
		
	}

}
