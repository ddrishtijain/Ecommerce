package com.futureforce.spring.service;

import java.util.List;

import com.futureforce.spring.model.Cart;
import com.futureforce.spring.model.Product;

public interface CartService {

	public void addToCart(int productId,int qty,int userId);
	public List<Cart> showCart(int userId);
	public void removeFromCart(int id,int userId);
	public void updateCartProductQuantity(int userId,int id,int qty);
}
