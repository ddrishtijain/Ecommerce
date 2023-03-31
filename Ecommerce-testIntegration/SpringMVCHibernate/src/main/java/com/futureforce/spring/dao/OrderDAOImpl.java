package com.futureforce.spring.dao;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.futureforce.spring.model.Cart;
import com.futureforce.spring.model.Order;
import com.futureforce.spring.model.OrderDetail;
import com.futureforce.spring.model.UserAddress;
import com.futureforce.spring.model.UserOrder;
import com.futureforce.spring.service.DeliveryService;
import com.futureforce.spring.service.UserInfoService;

@Repository
public class OrderDAOImpl implements OrderDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Autowired
	private UserInfoService userService;
	
	@Autowired
	private DeliveryService deliveryService;
	
	@Override
	public void addOrder(int userId,List<Cart> cartList) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Order order = new Order();
		
		order.setUserId(userId);
		order.setOrderDate(LocalDate.now());
		order.setDeliveryDate(LocalDate.now().plusDays(6));
		order.setStatus("Pending");
		
		UserAddress temp = userService.getDefaultAddressAsUA(userId);
		
		order.setAddress(temp.getAddress());
		order.setCity(temp.getCity());
		order.setState(temp.getState());
		order.setCountry(temp.getCountry());
		order.setZipcode(temp.getZipcode());
		order.setRecipientName(temp.getRecipientName());
		order.setRecipientPhone(temp.getRecipientPhone());
		
//		order.setProductId(c.getProduct().getId());
//		order.setQty(c.getQtyAdded());
//		order.setAmount(c.getQtyAdded()*Integer.parseInt(c.getProduct().getPrice()));
		int total=0;
		
		for(Cart temp2: cartList)
		{
			int amount = (temp2.getQtyAdded()*Integer.parseInt(temp2.getProduct().getPrice()));
			total+=amount;
		}
		
		order.setTotalAmount(total);
		session.persist(order);
		
		for(Cart temp2: cartList) {
		
			int amount = (temp2.getQtyAdded()*Integer.parseInt(temp2.getProduct().getPrice()));
			total+=amount;
			Query query = session.createQuery("Delete Cart where cart_id=:id");
			query.setInteger("id", temp2.getId());
			query.executeUpdate();
			
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setProductName(temp2.getProduct().getName());
			orderDetail.setOrderId(order.getOrderId());;
			orderDetail.setQty(temp2.getQtyAdded());
			orderDetail.setAmount(amount);
			orderDetail.setProductId(temp2.getProduct().getId());
			session.persist(orderDetail);
			boolean result = false;
			 result = deliveryService.addDelivery(order.getOrderId());
			System.out.println(result);
		}
	
	}

	public List<UserOrder> getOrdersByUserId(int userId){
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("From Order where userId=:userId order by orderId desc");
		query.setInteger("userId",userId);
		List<Order> orders = query.list();
		
		List<UserOrder> userOrders = new ArrayList<UserOrder>();
		
		for(Order tempOrder: orders)
		{
			query = session.createQuery("from OrderDetail where orderId=:orderId");
			query.setInteger("orderId",tempOrder.getOrderId());
			List<OrderDetail> orderDetails = query.list();
			
			UserOrder tempUserOrder = new UserOrder();
			tempUserOrder.setOrder(tempOrder);
			tempUserOrder.setOrderDetails(orderDetails);
			userOrders.add(tempUserOrder);
		}
		
		return userOrders;
		
	}
	

}
