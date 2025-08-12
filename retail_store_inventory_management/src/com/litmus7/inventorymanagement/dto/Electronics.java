package com.litmus7.inventorymanagement.dto;

public class Electronics extends Product{
	private String brand;
	private int warrantyMonths;
	public Electronics(String productId, String productName, String category, String status,
            double price, String brand, int warrantyMonths) {
		super(productId, productName, category, status, price);
        this.setBrand(brand);
        this.setWarrantyMonths(warrantyMonths);
	}
	public String getBrand() {return brand;}
	public void setBrand(String brand) {this.brand = brand;}
	public int getWarrantyMonths() {return warrantyMonths;}
	public void setWarrantyMonths(int warrantyMonths) {this.warrantyMonths = warrantyMonths;}
}
