package com.futureforce.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futureforce.spring.dao.OrderDAO;
import com.futureforce.spring.model.Cart;
import com.futureforce.spring.model.Order;
import com.futureforce.spring.model.UserOrder;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDAO orderDAO;
	@Override
	@Transactional
	public void addOrder(int userId,List<Cart> c) {
		this.orderDAO.addOrder(userId,c);
		
	}
	
	@Override
	@Transactional
	public List<UserOrder> getOrdersByUserId(int userId){
		return this.orderDAO.getOrdersByUserId(userId);
	}

}
