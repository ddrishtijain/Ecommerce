package com.futureforce.spring.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futureforce.spring.dao.OrderDAO;
import com.futureforce.spring.model.Cart;
import com.futureforce.spring.model.Order;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDAO orderDAO;
	
	

	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}



	@Override
	@Transactional
	public void addOrder(Cart c) {
		this.orderDAO.addOrder(c);
		
	}

}
