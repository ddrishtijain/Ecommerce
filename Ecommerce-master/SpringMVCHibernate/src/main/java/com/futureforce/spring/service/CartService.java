package com.futureforce.spring.service;

import java.util.List;

import com.futureforce.spring.model.Cart;
import com.futureforce.spring.model.Product;

public interface CartService {

	public void addToCart(Cart u);
	public List<Cart> showCart();
	public void removeFromCart(int id);
	
}
