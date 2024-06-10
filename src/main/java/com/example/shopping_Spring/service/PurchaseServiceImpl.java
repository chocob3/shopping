package com.example.shopping_Spring.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping_Spring.entity.Products;
import com.example.shopping_Spring.entity.Purchase;
import com.example.shopping_Spring.entity.Register;
import com.example.shopping_Spring.repository.PurcahseRepository;

@Service
public class PurchaseServiceImpl implements PurchaseService{
	@Autowired
	PurcahseRepository repository;
	@Autowired
	ProductsService productsService;

	@Override
	public Iterable<Purchase> findAll() {
		return repository.findAll();
	}

	@Override
	public Purchase registByPurchase(Integer purchaseproduct, String purchaseuser, Integer purchaseitems) {
		LocalDateTime now=LocalDateTime.now();
		Purchase purchase=new Purchase();
		purchase.setPurchasedate(Timestamp.valueOf(now));
		purchase.setPurchaseproduct(purchaseproduct);
		purchase.setPurchaseuser(purchaseuser);
		purchase.setPurchaseitems(purchaseitems);
		return repository.save(purchase);
		
	}

	@Override
	public Optional<Purchase> choosePurchase(Integer purchaseid) {
		return repository.findById(purchaseid);
	}

	@Override
	public Iterable<Purchase> choosePrivatePurchaseList(Register user) {
		String purchaseuser=user.getId();
		return repository.findByPurchaseuser(purchaseuser);
	}

	@Override
	public Map<Purchase, String> choosePurchaseListWithProductName(Iterable<Purchase> privatePurchaseList) {
		// 商品とその購買履歴をみる(購買履歴とその名前）
		Map<Purchase,String> privateProductsMap=new HashMap<Purchase,String>();
		for(Purchase p:privatePurchaseList) {
			Optional<Products> products=productsService.chooseProducts(p.getPurchaseproduct());
			if(products.isPresent()) {
				Products pro=products.get();
			    String productsName=pro.getProductsname();
			    privateProductsMap.put(p, productsName);
			}
		}
		return privateProductsMap;
	}

	@Override
	//該当の購入情報から、在庫の量を増加する
	public Iterable<Products> addQuantityRegist(Map<Products, Integer> purchaseMap) {
		List<Products> purchaseProductsList=new ArrayList<Products>();
		for(Products p:purchaseMap.keySet()) {
			if(!(purchaseMap.get(p)==0)) {
				purchaseProductsList.add(p);
				Integer quantity=p.getQuantity();
				p.setQuantity(quantity+(purchaseMap.get(p)));
				productsService.updateByProduct(p);
			}
		}
		return purchaseProductsList;
		
	}
	@Override
	//該当の購入情報から在庫を減らす
	public Iterable<Products> purchaseQuantityRegist(Map<Products,Integer> purchaseMap){
		List<Products> purchaseProductsList=new ArrayList<>();
		for(Products p: purchaseMap.keySet()) {
			if(!(purchaseMap.get(p)==0)) {
				purchaseProductsList.add(p);
				Integer quantity=p.getQuantity();
				p.setQuantity(quantity-(purchaseMap.get(p)));
				productsService.updateByProduct(p);
			}
		}
		return purchaseProductsList;
	}
	

}
