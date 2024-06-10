package com.example.shopping_Spring.service;

import java.util.Optional;

import com.example.shopping_Spring.entity.Products;

public interface ProductsService {
	
	Iterable<Products> findAll();
	Products registByProducts(String productsName,Integer price,Integer quantity);
	Products updateByProduct(Products products);
	Optional<Products> chooseProducts(Integer productsId);
	Boolean existProducts();

}
