package com.futureforce.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CART")
public class Cart {
	
	@Id
	@Column(name="cart_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="user_id")
	private int userId;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	@Column(name="qty")
	private int qtyAdded;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQtyAdded() {
		return qtyAdded;
	}

	public void setQtyAdded(int qtyAdded) {
		this.qtyAdded = qtyAdded;
	}
	
	public Cart()
	{
		
	}
	
	public Cart(int userId,Product product,int qtyAdded)
	{
		super();
		this.userId = userId;
		this.product = product;
		this.qtyAdded = qtyAdded;
	}

	

}
