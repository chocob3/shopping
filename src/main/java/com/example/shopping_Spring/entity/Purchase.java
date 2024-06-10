package com.example.shopping_Spring.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase implements Serializable,Comparable<Purchase>{
	private Integer purchaseproduct;
	private String purchaseuser;
	private Integer purchaseitems;
	private Timestamp purchasedate;
	private Integer purchaseid;
	
	@Override
	public String toString() {
		return "PurchaseId:"+purchaseid+"/Purchaseitmes:"+purchaseitems+
				"Purchasedate:"+purchasedate+"PurchaseUser:"+purchaseuser;
	}
	@Override
	public boolean equals(Object obj) {
		if(this==obj) return true;
		if(obj==null) return false;
		Purchase purchase=(Purchase) obj;
		return Objects.equals(purchaseid,purchase.purchaseid);
	}
	public int hashCode() {
		return Objects.hash(purchaseid);
	}
	@Override
	public int compareTo(Purchase p) {
		return this.purchasedate.compareTo(p.purchasedate);
	}
	
}
