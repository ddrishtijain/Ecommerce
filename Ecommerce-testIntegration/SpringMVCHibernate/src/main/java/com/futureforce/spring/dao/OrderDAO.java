package com.futureforce.spring.dao;

import java.util.List;

import com.futureforce.spring.model.Cart;
import com.futureforce.spring.model.Order;
import com.futureforce.spring.model.UserOrder;

public interface OrderDAO {
	
	public void addOrder(int userId,List<Cart> c);
	
	public List<UserOrder> getOrdersByUserId(int userId);

}
