package com.futureforce.spring.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.futureforce.spring.model.Cart;
import com.futureforce.spring.model.Product;

@Repository
public class CartDAOImpl implements CartDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addToCart(Cart c) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(c);
		System.out.println("Inside the function");
		logger.info("Cart saved successfully, cart Details="+c);
		
	}

	public List<Cart> showCart() {
		Session session = sessionFactory.getCurrentSession();
		int userId=10;
		Query query = session.createQuery("From Cart where userId=:userId");
		query.setInteger("userId", userId);
//		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<Cart> cartList = query.list();
//		ArrayList list2 = new ArrayList();
//		for(Cart temp: cartList)
//		{
//			System.out.println(temp.getQtyAdded());
//		}
		return cartList;
	}

	public void removeFromCart(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		System.out.println("no session");
		int userId=10;
		Query query = session.createQuery("from Product where product_id=:id");
		query.setInteger("id",id);
		List<Product> productList = query.list();
		for(Product temp: productList) {
			System.out.println(temp.getId());
			Query query2 = session.createQuery("delete Cart where user_id=:userId and product_id=:temp");
			query2.setEntity("temp", temp);
			query2.setInteger("userId",userId);
			query2.executeUpdate();
		}
		
	}

}
