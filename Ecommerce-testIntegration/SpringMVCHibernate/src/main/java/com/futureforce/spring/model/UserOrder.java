package com.futureforce.spring.model;

import java.util.List;

public class UserOrder {
	
	private Order order;
	private List<OrderDetail> orderDetails;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	

}
