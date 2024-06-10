package com.example.shopping_Spring.form;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductFormList {
	 @NotNull
	    private List<ProductForm> productList;

}
