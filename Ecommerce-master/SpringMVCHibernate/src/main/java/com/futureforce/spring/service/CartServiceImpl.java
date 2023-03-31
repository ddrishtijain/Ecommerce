package com.futureforce.spring.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.futureforce.spring.dao.CartDAO;
import com.futureforce.spring.model.Cart;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartDAO cartDAO;
	
	public void setCartDAO(CartDAO cartDAO)
	{
		this.cartDAO = cartDAO;
	}
	
	@Override
	@Transactional
	public void addToCart(Cart u) {
		this.cartDAO.addToCart(u);
		
	}

	@Override
	@Transactional
	public List<Cart> showCart(){
		return this.cartDAO.showCart();
	}

	@Override
	@Transactional
	public void removeFromCart(int id) {
		this.cartDAO.removeFromCart(id);
	}
	
	

}
