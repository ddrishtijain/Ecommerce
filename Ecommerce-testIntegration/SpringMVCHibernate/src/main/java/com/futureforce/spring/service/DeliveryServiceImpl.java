package com.futureforce.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.futureforce.spring.dao.DeliveryDAO;
import com.futureforce.spring.model.Delivery;

@Service
public class DeliveryServiceImpl implements DeliveryService {

	@Autowired
	private DeliveryDAO deliveryDAO;

//	@Autowired(required = true)
//	@Qualifier(value = "deliveryDAO")
	public void setDeliveryDAO(DeliveryDAO deliveryDAO) {
		this.deliveryDAO = deliveryDAO;
	}

	@Override
	@Transactional
	public List<Delivery> getListDelivery(int deliveryExecutiveId) {
		return this.deliveryDAO.getListDelivery(deliveryExecutiveId);
	}
	
	@Override
	@Transactional
	public Boolean updateStatus(int orderId, String orderStatus) {
		return this.deliveryDAO.updateStatus(orderId, orderStatus);
	}
	
	@Override
	@Transactional
	public List getAssignedDeliveryDetails(int deliveryId) {
		return this.deliveryDAO.getAssignedDeliveryDetails(deliveryId);
	}
	
	@Override
	@Transactional
	public List<Delivery> getListDeliveryByDate(int dId, String date) {
		return this.deliveryDAO.getListDeliveryByDate(dId, date);
	}

	@Override
	@Transactional
	public boolean addDelivery(int orderId) {
		return this.deliveryDAO.addDelivery(orderId);
	}

}
