package com.futureforce.spring.dao;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.futureforce.spring.model.Cart;
import com.futureforce.spring.model.Order;

@Repository
public class OrderDAOImpl implements OrderDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addOrder(Cart c) {
		
		Session session = sessionFactory.getCurrentSession();
		int userId=10;
		
		Order order = new Order();
		
		order.setUserId(c.getUserId());
		order.setProductId(c.getProduct().getId());
		order.setQty(c.getQtyAdded());
		order.setAmount(c.getQtyAdded()*c.getProduct().getPrice());
		order.setOrderDate(new Date());
		order.setDeliveryDate(new Date());
		order.setStatus("Pending");
		order.setAddress("Salesforce Hyderabad");
		
		session.persist(order);
		
		Query query = session.createQuery("Delete Cart where cart_id=:id");
		query.setInteger("id", c.getId());
		query.executeUpdate();
	}

	

}
