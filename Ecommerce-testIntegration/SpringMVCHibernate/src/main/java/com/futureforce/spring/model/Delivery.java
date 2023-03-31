package com.futureforce.spring.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Delivery")
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToOne
	@JoinColumn(name = "orderId")
	private Order orderId;

	@ManyToOne
	@JoinColumn(name = "deliveryExecutiveId")
	private UserInfo deliveryExecutiveId;

	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UserInfo getDeliveryExecutiveId() {
		return deliveryExecutiveId;
	}

	public void setDeliveryExecutiveId(UserInfo deliveryExecutiveId) {
		this.deliveryExecutiveId = deliveryExecutiveId;
	}

	public Order getOrderId() {
		return orderId;
	}

	public void setOrderId(Order orderId) {
		this.orderId = orderId;
	}

//	public Delivery(int deliveryExecutiveId, int orderId, Date date) {
//		super();
//		this.deliveryExecutiveId = deliveryExecutiveId;
//		this.orderId = orderId;
//		this.date = date;
//	}

}
