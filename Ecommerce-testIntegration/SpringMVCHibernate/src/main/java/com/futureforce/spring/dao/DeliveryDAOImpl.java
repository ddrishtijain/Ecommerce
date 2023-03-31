package com.futureforce.spring.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

import com.futureforce.spring.model.Delivery;
import com.futureforce.spring.model.Order;
import com.futureforce.spring.model.UserInfo;

@Repository
public class DeliveryDAOImpl implements DeliveryDAO {

	private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserInfoDAO userInfoDAO;

//	public void setSessionFactory(SessionFactory sf) {
//		this.sessionFactory = sf;
//	}

	@Override
	public boolean addDelivery(int orderId) {

		Session session = sessionFactory.getCurrentSession();
		
		Query query= session.createQuery("from Order where orderId=:o").setParameter("o",orderId);
		
		List results = query.list();
		
		Order order=(Order)results.get(0);
		
		int orderPin = Integer.parseInt(order.getZipcode());
		
		LocalDate date = order.getDeliveryDate();
		java.sql.Date deliveryDate = java.sql.Date.valueOf(date);
		System.out.println(deliveryDate);
		
		List<UserInfo> executiveList = new ArrayList<UserInfo>();
		System.out.println(executiveList);
		executiveList = userInfoDAO.getListOfDeliveryExecs();
		System.out.println(executiveList);		
		int minDiff = Integer.MAX_VALUE;
		UserInfo assignedDeliveryExec = null;
		for (UserInfo exec : executiveList) {
			int execPin = Integer.parseInt(userInfoDAO.getPincodeForThisID(exec.getId()));
			if (Math.abs(execPin - orderPin) < minDiff) {
				assignedDeliveryExec = exec;
				minDiff = Math.abs(execPin - orderPin);
			}
		}
//		if (minDiff > 10) {
//			assignedDeliveryExec = null;
//		}
		if (assignedDeliveryExec != null) {
			Delivery temp = new Delivery();
			temp.setDate(deliveryDate);
			temp.setDeliveryExecutiveId(assignedDeliveryExec);
			temp.setOrderId(order);
			session.save(temp);
			return true;
		} else
			return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Delivery> getListDelivery(int deliveryExecutiveId) {
		
		Session session = sessionFactory.getCurrentSession();

		//		java.util.Date date = new java.util.Date();
		//		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		//		System.out.println(sqlDate);
		
		List<Delivery> deliveryList = new ArrayList<Delivery>();
		
		if(deliveryExecutiveId == 0) {
			
			deliveryList = session.createQuery("FROM Delivery").list();
		}
		
		else {
			
			//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			//Date date = new Date();
			
			//System.out.println(dateFormat.format(date));
			
			//java.sql.Date sqlDate = java.sql.Date.valueOf(dateFormat.format(date));
			
			
			//Query query = session.createQuery("FROM Delivery "
		    //        + "WHERE deliveryExecutiveId= :did AND date=:delDate");
		    //        //+ "ORDER BY e.dateTime");
			
			Query query = session.createQuery("FROM Delivery "
				          + "WHERE deliveryExecutiveId= :did");
		    
			query.setInteger("did", deliveryExecutiveId);
			//query.setDate("delDate", date);
			
			deliveryList = query.list(); //session.createQuery("FROM Delivery WHERE deliveryExecutiveId=" + deliveryExecutiveId + " AND date=" +  sqlDate).list();
		}
				
		return deliveryList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Delivery> getListDeliveryByDate(int dId, String date) {
		
		//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//Date date = new Date();
		
		//System.out.println(dateFormat.format(date));
		
		Session session = sessionFactory.getCurrentSession();
		
		//java.sql.Date sqlDate = java.sql.Date.valueOf(date);
		
		List<Delivery> deliveryList = new ArrayList<Delivery>();
		
		if(dId == 0) {
		
			Query query = session.createQuery("FROM Delivery "
						  + "WHERE date=:delDate");
	            		  //+ "ORDER BY e.dateTime");
	    
			query.setString("delDate", date);
			
			deliveryList = query.list();
		}
		
		else {
			
			Query query = session.createQuery("FROM Delivery "
					  + "WHERE deliveryExecutiveId=:did AND date=:delDate");
          		  //+ "ORDER BY e.dateTime");
			
			query.setInteger("did", dId);
			query.setString("delDate", date);
		
			deliveryList = query.list();
		}
		
		return deliveryList;
	}
	
	@Override
	public Boolean updateStatus(int orderId, String orderStatus) {
		
		Session session = sessionFactory.getCurrentSession();
		
		if(orderStatus.equals("shipped"))
			orderStatus = "Shipped";
		else if(orderStatus.equals("aas"))
			orderStatus = "Arrived at Salesforce E-commerce";
		else if(orderStatus.equals("lfs"))
			orderStatus = "Left from Salesforce E-commerce";
		else if(orderStatus.equals("fds"))
			orderStatus = "Arrived at final delivery station";
		else if(orderStatus.equals("ofd"))
			orderStatus = "Out for delivery";
		else if(orderStatus.equals("delivered"))
			orderStatus = "Delivered";
		
		//Transaction tx = session.beginTransaction();

		String hqlUpdate = "update Order c set c.status = :newStatus where c.id = :id";

		int updatedEntities = session.createQuery( hqlUpdate )
		        .setString( "newStatus", orderStatus )
		        .setString( "id", Integer.toString(orderId) )
		        .executeUpdate();
		//tx.commit();
		
		System.out.println("Updated Entities: " + updatedEntities);
		
		if(updatedEntities != 0)
			return true;
		else
			return false;
	}
	
	@Override
	public List getAssignedDeliveryDetails(int deliveryId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		List deliveryDetail = session.createQuery("FROM Delivery WHERE id=" + deliveryId).list();
		
		return deliveryDetail;
	}
}
