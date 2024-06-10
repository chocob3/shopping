package com.example.shopping_Spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.shopping_Spring.entity.Purchase;

public interface PurcahseRepository  extends CrudRepository<Purchase,Integer>{
	Iterable<Purchase> findByPurchaseuser(String purchaseuser);
}
