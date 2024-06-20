package com.example.shopping_Spring.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping_Spring.entity.Products;
import com.example.shopping_Spring.repository.ProductsRepository;
@Service
public class ProductsServiceImpl implements ProductsService {
	
    @Autowired
    ProductsRepository repository;
	@Override
	public Iterable<Products> findAll() {
	  return repository.findAll();
	}

	@Override
	public Products registByProducts(String productsName, Integer price, Integer quantity) {
		Products product=new Products();
		product.setProductsname(productsName);
		product.setPrice(price);
		product.setQuantity(quantity);
		LocalDateTime now=LocalDateTime.now();
		Timestamp timestamp =Timestamp.valueOf(now);
		product.setInsertdate(timestamp);
		return repository.save(product);
	}

	@Override
	public Products updateByProduct(Products products) {
		Integer productsId=products.getProductsid();
		Integer quantity=products.getQuantity();
		repository.changeQuantity(quantity, productsId);
		return products;
	}

	@Override
	public Optional<Products> chooseProducts(Integer productsId) {
		return repository.findById(productsId);
	}
	@Override
	public Optional<Products> chooseProductsByName(String productsName){
		Iterable<Products> productList=repository.findAll();
		Products pro =null;
		for(Products p: productList) {
			if(productsName.equals(p.getProductsname())){
				 pro=p;
			}
			
		}
		Optional<Products> optionalProduct = Optional.ofNullable(pro);
		return optionalProduct;
	}

	@Override
	public Boolean existProducts() {
		Iterable<Products> productsIterable=repository.findAll();
		for(Products p: productsIterable) {
			if(p.getQuantity()>0) {
				return true;
			}
		}
		return false;
	}

	

}
