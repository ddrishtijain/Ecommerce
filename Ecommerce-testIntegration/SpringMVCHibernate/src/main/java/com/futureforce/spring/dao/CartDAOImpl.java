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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.futureforce.spring.model.Cart;
import com.futureforce.spring.model.Product;

@Repository
public class CartDAOImpl implements CartDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override

	public void addToCart(int productId,int qty,int userId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Product where id=:id");
		query.setInteger("id",productId);
		List<Product> productList = query.list();
		if(!productList.isEmpty()) {
			Product temp = productList.get(0);			
			Query query2 = session.createQuery("From Cart where user_id=:userId and product_id=:temp");
			query2.setInteger("userId",userId);
			query2.setEntity("temp", temp);
			List<Cart> cartList = query2.list();
			Cart c = new Cart();
			c.setUserId(userId);
			c.setProduct(temp);
			c.setQtyAdded(qty);
			if(cartList.isEmpty()) {				
				session.persist(c);
			}
			else {
				Cart temp2 = cartList.get(0);
					c.setQtyAdded(qty+temp2.getQtyAdded());
					removeFromCart(productId,userId);					
					session.persist(c);
				}
			}
		
	}

	public List<Cart> showCart(int userId) {
		Session session = sessionFactory.getCurrentSession();
//		int userId=10;

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

	public void removeFromCart(int id,int userId) {
		
		Session session = sessionFactory.getCurrentSession();
		System.out.println("Hello "+userId);
//		int userId=10;

		Query query = session.createQuery("from Product where id=:id");
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
	
	public void updateCartProductQuantity(int userId,int id,int qty) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Cart set qty=:qty where user_id=:userId and product_id=:id");
		query.setInteger("qty", qty);
		query.setInteger("userId", userId);
		query.setInteger("id", id);
		query.executeUpdate();
	}

}
