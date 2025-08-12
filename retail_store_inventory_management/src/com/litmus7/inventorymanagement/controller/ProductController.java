package com.litmus7.inventorymanagement.controller;

import java.util.List;

import com.litmus7.inventorymanagement.dto.Product;
import com.litmus7.inventorymanagement.dto.Response;
import com.litmus7.inventorymanagement.service.ProductService;

public class ProductController {
	private ProductService productservice =new ProductService();
	
	 public Response<String> addProduct(Product product) {
		 //validate if input is correct or not
		 productservice.addProduct(product);
		 return new Response<>(100,"success"); 
	 }
	 
	 public Response<List<Product>> viewAllProducts() {
		 List<Product> products = productservice.viewAllProducts();
		 return new Response<>(100,"Success",products);
	 }
	 
	 public Response<List<Product>> viewProductsByCategory(String category) {
		 //validate the input
		 List<Product> products = productservice.viewProductsByCategory(category);
		 return new Response<>(100,"Success",products);
	 }
	 
	 public Response<List<Product>> sortProducts(int choice) {
		 //validate the input
		 List<Product> products=productservice.sortProducts(choice);
		 return new Response<>(100,"Success",products);
	 }
}
