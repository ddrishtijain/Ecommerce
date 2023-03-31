package com.futureforce.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.futureforce.spring.dao.ProductDAO;
import com.futureforce.spring.model.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAO productDAO;

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Override
	@Transactional
	public void addProduct(Product p) {
		this.productDAO.addProduct(p);
	}

	@Override
	@Transactional
	public void updateProduct(Product p) {
		this.productDAO.updateProduct(p);
	}

	@Override
	@Transactional
	public List<Product> listProducts() {
		return this.productDAO.listProducts();
	}

	@Override
	@Transactional
	public Product getProductById(int id) {
		return this.productDAO.getProductById(id);
	}

	@Override
	@Transactional
	public void removeProduct(int id) {
		this.productDAO.removeProduct(id);
	}

}
