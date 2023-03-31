package com.futureforce.spring.dao;

import java.util.List;

import com.futureforce.spring.model.Cart;

public interface CartDAO {

	public void addToCart(int productId,int qty,int userId);
	public List<Cart> showCart(int userId);
	public void removeFromCart(int id,int userId);
	public void updateCartProductQuantity(int userId,int id,int qty);

}
