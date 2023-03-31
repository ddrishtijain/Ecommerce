package com.futureforce.spring.dao;

import java.util.List;

import com.futureforce.spring.model.Cart;

public interface CartDAO {

	public void addToCart(Cart c);
	public List<Cart> showCart();
	public void removeFromCart(int id);
}
