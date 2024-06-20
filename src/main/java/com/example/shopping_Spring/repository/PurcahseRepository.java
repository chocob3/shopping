package com.example.shopping_Spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.shopping_Spring.entity.Purchase;
@Repository
public interface PurcahseRepository  extends CrudRepository<Purchase,Integer>{
	Iterable<Purchase> findByPurchaseuser(String purchaseuser);
}
