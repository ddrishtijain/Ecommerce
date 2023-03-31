package com.futureforce.spring.service;

import java.util.List;

import com.futureforce.spring.model.Delivery;

public interface DeliveryService {
	
	public boolean addDelivery(int orderId);
	public List<Delivery> getListDelivery(int deliveryExecutiveId);
	public List<Delivery> getListDeliveryByDate(int dId, String date);

	public Boolean updateStatus(int orderId, String orderStatus);
	public List getAssignedDeliveryDetails(int deliveryId);
}
