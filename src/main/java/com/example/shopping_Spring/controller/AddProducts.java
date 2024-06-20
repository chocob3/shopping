package com.example.shopping_Spring.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.shopping_Spring.entity.Products;
import com.example.shopping_Spring.form.AddProductsForm;
import com.example.shopping_Spring.service.ProductsService;
import com.example.shopping_Spring.service.PurchaseService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;



@Controller
@RequestMapping("/addProducts")
public class AddProducts {
	@Autowired
	ServletContext application ;
	@Autowired
	PurchaseService purchaseService;
	@Autowired
	ProductsService productsService;
	@Autowired
	ServletContext servletContext;
	
	@ModelAttribute
	public AddProductsForm setUpForm() {
		return new AddProductsForm();
	}
	@GetMapping("/addProducts")
	public String addProducts() {
		return "addProducts";
		
	}
	@PostMapping("/doAddExistingProducts")
	public String doAddProducts(HttpServletRequest request,Model model) {
		Iterable<Products> productsList=(Iterable<Products>)application.getAttribute("productsList");
		Map<Products,Integer> changeProductsMap= new HashMap<Products,Integer>();
		for(Products p: productsList) {
			String strQuantity=(String)request.getParameter(p.getProductsname());
			if(strQuantity!=null&&!strQuantity.equals("")) {
				Integer quantity=Integer.parseInt(strQuantity);
				changeProductsMap.put(p,quantity);
				}
			}
		
		Iterable<Products> changeProductsList=purchaseService.addQuantityRegist(changeProductsMap);
		if(changeProductsList!=null&&!((List<Products>) changeProductsList).isEmpty()) {
			model.addAttribute("changeProductsList", changeProductsList);
		}else {
			String errmsg="商品を追加してください";
			model.addAttribute(errmsg);
			return "addProducts";
		}

		return "addComplete";
	}
	@PostMapping("/doAddNewProducts")
	public String doAddNewProducts(@ModelAttribute AddProductsForm form,Model model) {
			
		
			// 新しい商品の追加処理
        String newProductName = form.getProductName();
        Integer newProductQuantity = form.getQuantity();
        Integer newProductPrice = form.getProductsPrice();
        MultipartFile newProductFile = form.getFile();
        Optional<Products> newProductsName=productsService.chooseProductsByName(newProductName);
		if(newProductsName.isEmpty()) {
		Products registProduct=productsService.registByProducts(newProductName,newProductQuantity,newProductPrice);
		
        
		if(newProductName!=null&&!newProductName.equals("")) {
			//アップロード処理
			String uploadDir="static/img";
			String uploadPath;
			String fileName="";
			try {
				// 保存先ディレクトリのパス
				uploadPath = new ClassPathResource(uploadDir).getFile().getAbsolutePath();

				fileName = newProductName+".jpg";
				String filePath = uploadPath + File.separator + fileName;
				byte[] bytes=newProductFile.getBytes();
	            Path path=Paths.get(filePath);
				Files.write(path, bytes);
				
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			
			//追加処理
			
			
			model.addAttribute("productFile", fileName);
			model.addAttribute("registProduct",registProduct);
		}  else {
			model.addAttribute("err","新規商品を追加してください");
			return "addProducts";
		}
		
		return "addNewComplete";
		}
		model.addAttribute("err","既に存在する商品です");
		return "addProducts";
	}


}
