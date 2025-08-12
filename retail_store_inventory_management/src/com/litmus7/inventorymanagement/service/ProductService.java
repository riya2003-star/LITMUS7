package com.litmus7.inventorymanagement.service;

import java.util.List;

import com.litmus7.inventorymanagement.dao.ProductDAO;
import com.litmus7.inventorymanagement.dto.Product;

public class ProductService {
	private ProductDAO productdao = new ProductDAO();
	
	public void addProduct(Product product) {
		//Validate product. 
        productdao.saveProduct(product);
      //if errors found throw Exception
    }
	
	public List<Product> viewAllProducts() {
        return productdao.getAllProducts();
      //if errors found throw Exception
    }
	
	public List<Product> viewProductsByCategory(String category) {
		//validate category
		return productdao.getProductsByCategory(category);
		//if errors found throw Exception
	}
	
	public List<Product> sortProducts(int choice) {
		List<Product> products=productdao.getAllProducts();//calling all products from file
		
		switch (choice) {
        case 1: // Price Ascending
            
            break;
        case 2: // Price Descending
            
            break;
        case 3: // Name A–Z
            
            break;
        default:
            System.out.println("Invalid choice! No sorting applied.");
    }
		//if errors found throw Exception
        return products;
    }
}
