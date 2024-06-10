package com.example.shopping_Spring.service;

import java.util.Map;
import java.util.Optional;

import com.example.shopping_Spring.entity.Products;
import com.example.shopping_Spring.entity.Purchase;
import com.example.shopping_Spring.entity.Register;

public interface PurchaseService {
	
	Iterable<Purchase>findAll();
	Purchase registByPurchase(Integer purchaseproduct,String purchaseuser,Integer purchaseitems);
	Optional<Purchase> choosePurchase(Integer purchaseid);
	Iterable<Purchase> choosePrivatePurchaseList(Register user);
	Map<Purchase,String> choosePurchaseListWithProductName(Iterable<Purchase>privatePurchaseList);
	Iterable<Products> addQuantityRegist(Map<Products,Integer> purchaseMap);
	Iterable<Products> purchaseQuantityRegist(Map<Products,Integer> purchaseMap);
}
