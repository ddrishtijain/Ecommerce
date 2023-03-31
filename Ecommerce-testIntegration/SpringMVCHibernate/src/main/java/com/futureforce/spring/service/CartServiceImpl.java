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
	
	@Override
	@Transactional

	public void addToCart(int productId,int qty,int userId) {
		System.out.println("ServiceImpl "+userId);
		this.cartDAO.addToCart(productId,qty,userId);
	}
	

	@Override
	@Transactional

	public List<Cart> showCart(int userId){
		return this.cartDAO.showCart(userId);
	}
	

	@Override
	@Transactional

	public void removeFromCart(int id,int userId) {
		this.cartDAO.removeFromCart(id,userId);
	}
	
	@Override
	@Transactional
	public void updateCartProductQuantity(int userId,int id,int qty) {
		this.cartDAO.updateCartProductQuantity(userId,id,qty);
	}
	

}
