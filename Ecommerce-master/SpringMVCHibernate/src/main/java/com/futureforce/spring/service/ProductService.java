package com.futureforce.spring.service;

import java.util.List;

import com.futureforce.spring.model.Product;

public interface ProductService {

	public void addProduct(Product u);
	public void updateProduct(Product u);
	public List<Product> listProducts();
	public Product getProductById(int id);
	public void removeProduct(int id);
	
}
