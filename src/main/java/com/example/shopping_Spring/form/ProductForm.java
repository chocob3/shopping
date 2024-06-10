package com.example.shopping_Spring.form;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductForm {
	
	private String productsname;
	@NotNull
	private Integer quantity;
	
}
