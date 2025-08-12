package com.litmus7.inventorymanagement.dto;

import java.util.Date;

public class Grocery extends Product{
	 private Date expiryDate;
	 private double weightKg;
	 public Grocery(String productId, String productName, String category, String status,
             double price, Date expiryDate, double weightKg) {
		 
		 super(productId, productName, category, status, price);
		 this.setExpiryDate(expiryDate);
	     this.setWeightKg(weightKg);
	}
	 
	 public Date getExpiryDate() {return expiryDate;}
	 public void setExpiryDate(Date expiryDate) {this.expiryDate = expiryDate;}
	 public double getWeightKg() {return weightKg;}
	 public void setWeightKg(double weightKg) {this.weightKg = weightKg;}
}
