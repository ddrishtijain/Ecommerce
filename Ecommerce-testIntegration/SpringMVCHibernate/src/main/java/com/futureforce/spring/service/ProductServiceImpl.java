package com.futureforce.spring.service;

import java.util.*;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.futureforce.spring.dao.ProductDAO;
import com.futureforce.spring.model.Product;

import me.xdrop.fuzzywuzzy.FuzzySearch;

@Service
public class ProductServiceImpl implements ProductService {


	@Autowired
	private ProductDAO productDAO;

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

	@Override
	@Transactional
	public Map<String, List<Product>> getProductsByCategory() {

		Map<String, List<Product>> maps = new HashMap<String, List<Product>>();
		for (Product product : productDAO.listProducts()) {

			String cat = product.getCategory();
			if (maps.containsKey(cat)) {
				List<Product> pros = maps.get(cat);
				pros.add(product);
				maps.put(cat, pros);
			} else {
				List<Product> pros = new ArrayList<Product>();
				pros.add(product);
				maps.put(cat, pros);
			}

		}
		return maps;
	}

	@Transactional
	public List<Product> searchProducts(String pattern) {
		List<Product> products = new ArrayList<>();
		for (Product pro : productDAO.listProducts()) {
			String search = pro.getName() + " " + pro.getDescription() + " " + pro.getCategory();
			if (FuzzySearch.partialRatio(pattern.toLowerCase(), search.toLowerCase()) >= 80) {
				products.add(pro);
			}
		}
		return products;

	}

}
