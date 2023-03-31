package com.futureforce.spring.dao;

import java.util.List;

import com.futureforce.spring.model.Product;

public interface ProductDAO {

	public void addProduct(Product p);
	public void updateProduct(Product p);
	public List<Product> listProducts();
	public Product getProductById(int id);
	public void removeProduct(int id);
}
