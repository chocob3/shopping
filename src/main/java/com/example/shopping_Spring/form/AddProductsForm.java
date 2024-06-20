package com.example.shopping_Spring.form;



import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddProductsForm {
	private String productName;
	private Integer quantity;
	private Integer productsPrice;
	private MultipartFile file;
}