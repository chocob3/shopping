package com.example.shopping_Spring.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Products implements Serializable,Comparable<Products>{
	@Id
	@NotBlank
	private Integer productsid;
	@NotBlank
	private String productsname;
	private Integer price;
	private Integer quantity;
	private Timestamp insertdate;
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj)return true;
		if(obj==null||getClass()!=obj.getClass())return false;
		Products products=(Products) obj;
		return Objects.equals(productsid,products.productsid);
	}
	public int hashCode() {
		return Objects.hash(productsid);
	}
	
	@Override
	public int compareTo(Products p) {
		return this.insertdate.compareTo(p.insertdate);
	}
	@Override
	public String toString() {
		return "Products{"+"id="+productsid+
				",productsName="+productsname+
				",price="+price+",quantity="+quantity+
				"}";
		
	}

	

}
