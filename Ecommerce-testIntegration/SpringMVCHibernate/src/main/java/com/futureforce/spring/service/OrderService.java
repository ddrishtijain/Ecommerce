package com.futureforce.spring.service;

import java.util.List;

import com.futureforce.spring.model.Cart;
import com.futureforce.spring.model.UserOrder;

public interface OrderService {
	
	public void addOrder(int userId,List<Cart> c);
	
	public List<UserOrder> getOrdersByUserId(int userId);

}
