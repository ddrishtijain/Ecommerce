package com.futureforce.spring.service;

import java.util.List;
import java.util.Map;

import com.futureforce.spring.model.Product;

public interface ProductService {

	public void addProduct(Product u);
	public void updateProduct(Product u);
	public List<Product> listProducts();
	public Product getProductById(int id);
	public void removeProduct(int id);
	public Map<String, List<Product>> getProductsByCategory();
	List<Product> searchProducts(String pattern);	

}
