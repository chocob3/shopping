package com.example.shopping_Spring.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.shopping_Spring.entity.Products;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/changeProducts")
public class ChangeProducts {
	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	ServletContext application;
	@Autowired
	HttpServletRequest request;
	@GetMapping("/changeProducts")
	public String changeProducts() {
		return "changeProducts";
		
	}
	@PostMapping("/doChangeProducts")
	public String dochangeProducts() {
		File jsonFile=Paths.get("src", "main", "resources", "static", "js", "showProducts.json").toFile();
		List<Products> productsList=(List<Products>)application.getAttribute("productsList");
		String errmsg="";
		Map<Products,String> textList=new HashMap<>();

		try {
			JsonNode rootNode=objectMapper.readTree(jsonFile);
			ArrayNode productsArray;
			
			if(rootNode.isArray()) { //rootNodeが配列形式であるかどうかを確認し
				productsArray=(ArrayNode) rootNode;
			}else {
				productsArray=objectMapper.createArrayNode();
			}
			 for(Products products:productsList) {
				 String productsName=products.getProductsname();
				 String text=(String)request.getParameter(productsName);
				 if(text!=null&&!text.equals("")) {
					 for(JsonNode product:productsArray) {
						 String name=product.get("name").asText();
						 if(productsName==name) {
							 ((ObjectNode)product).put("text",text);
							 textList.put(products, text);
							 break;
						 }else {
							 ObjectNode newProduct = objectMapper.createObjectNode();
							 newProduct.put("name", productsName);
							 newProduct.put("description", text);
						 } 
					 }
				 }
			 }
			 
		}catch(IOException e) {
			e.printStackTrace();
			errmsg="Jsonファイル読み込みに失敗しました";
			return "changeProducts";
		}
		if(textList.isEmpty()) {
			errmsg="一つも内容の書き換えがありません";
			return "changeProducts";
		}
		 application.setAttribute("textList", textList);
		 return "changeDone";
		
	}
	

}
