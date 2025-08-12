package com.litmus7.inventorymanagement.dto;

abstract public class Product {
	private String productId;
	private String productName;
    private String category;
	private String status;
	private double price;
	public Product(String productId, String productName, String category, String status, double price) {
		this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.status = status;
        this.price = price;
	}
	
	public String getProductId() {return productId;}
	public void setProductId(String productId) {this.productId = productId;}
	public String getProductName() {return productName;}
	public void setProductName(String productName) {this.productName = productName;}
	public String getCategory() {return category;}
	public void setCategory(String category) {this.category = category;}
	public String getStatus() {return status;}
	public void setStatus(String status) {this.status = status;}
	public double getPrice() {return price;}
	public void setPrice(double price) {this.price = price;}
}
