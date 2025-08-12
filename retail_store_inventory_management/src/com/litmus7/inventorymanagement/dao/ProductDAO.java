package com.litmus7.inventorymanagement.dao;

import java.util.ArrayList;
import java.util.List;

import com.litmus7.inventorymanagement.dto.Product;

public class ProductDAO {
	public void saveProduct(Product product) {
		//Save product to file
		//if errors found throw Exception
	}
	
	public List<Product> getAllProducts(){
		//get all products from file and save as list
		//if errors found throw Exception
		//return the the list of products
		return null;
	}
	
	public List<Product> getProductsByCategory(String category) {
		//get products from file
		//filter it according to category
		//if errors found throw Exception
		//return the list of filtered products
		return null;
	}
}
