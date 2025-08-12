package com.litmus7.inventorymanagement.ui;

import com.litmus7.inventorymanagement.controller.ProductController;
import com.litmus7.inventorymanagement.dto.Electronics;

public class RetailStoreApp {

	public static void main(String[] args) {
		ProductController controller = new ProductController();
		
		//Add Product
		Electronics electronics=new Electronics("P101", "Laptop", "Electronics", "AVAILABLE", 55000, "HP", 12);
		controller.addProduct(electronics); //returns respond
		//View All Products
		controller.viewAllProducts();//returns respond
		//View Products by Category
		String category="Clothing";
		controller.viewProductsByCategory(category);//returns respond
		//Sort Products
		controller.sortProducts(1);//returns respond
	}

}
