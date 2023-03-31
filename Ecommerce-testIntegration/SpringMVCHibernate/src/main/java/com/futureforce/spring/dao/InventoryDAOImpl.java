package com.futureforce.spring.dao;



import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.futureforce.spring.model.Inventory;
import com.futureforce.spring.model.Product;


@Repository
public class InventoryDAOImpl implements InventoryDAO {

	private static final Logger logger = LoggerFactory.getLogger(InventoryDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addProduct(Inventory i) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(i);
		logger.info("Product saved successfully, product Details="+i);
		
		// TODO Auto-generated method stub
	}

	@Override
	public int updateProduct(int pid, int myQuantity) {

		Session session = this.sessionFactory.getCurrentSession();
//		int myQuantity = i.getQuantity();
//		int pid = i.getProduct().getId();
		System.out.println(myQuantity+" ----- ");
		String sql = "UPDATE Inventory " + "SET quantity = quantity-:myQuantity " + 
		"WHERE quantity>=:myQuantity and product_id=:pid";
		
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("pid", pid);
		query.setParameter("myQuantity", myQuantity);
		int res = query.executeUpdate();
		
		
		//System.out.println(myQuantity+" aya----- ");
		
		String sql2 = "UPDATE Product " + "SET quantity = quantity-:myQuantity " + 
				"WHERE quantity>=:myQuantity and id=:pid";
		
		
		
		
		
		
		SQLQuery query2 = session.createSQLQuery(sql2);
		query2.setParameter("pid", pid);
		query2.setParameter("myQuantity", myQuantity);
		int res2 = query2.executeUpdate();
		
		System.out.println(res2);
		
//			session.update(i);
//		logger.info("Product updated successfully, Product Details="+i);
		return res2;
		// TODO Auto-generated method stub
	}

//	@Override
//	public int updateProduct2(int myQuantity, int pid) {
//		Session session = this.sessionFactory.getCurrentSession();
//		String sql = "UPDATE Inventory " + "SET quantity = quantity-:myQuantity " + 
//				"WHERE quantity>=:myQuantity and product_id=:pid";
//				
//				SQLQuery query = session.createSQLQuery(sql);
//				query.setParameter("pid", pid);
//				query.setParameter("myQuantity", myQuantity);
//				int res = query.executeUpdate();
//				System.out.println(res);
//				
////					session.update(i);
////				logger.info("Product updated successfully, Product Details="+i);
//				return res;
//		
//	}
	@Override
	public List<Inventory> listProductIds(String vId) {

		Session session = this.sessionFactory.getCurrentSession();		
		boolean isDeleted=false;
		List<Inventory> inventoryList = (List<Inventory>) session.createSQLQuery("Select * from INVENTORY where vendorId=:vId and isDeleted=:isDeleted ")
				.addEntity(Inventory.class).setParameter("vId", vId).setParameter("isDeleted", isDeleted).list();

		logger.info("Product loaded successfully, Product details="+inventoryList);

		return inventoryList; 
		// TODO Auto-generated method stub

	}

	@Override
	public Inventory getInventoryById(int id) {
		
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Inventory i = (Inventory) session.load(Inventory.class, new Integer(id));
		logger.info("Product loaded successfully, Product details="+i);
		return i;
	}

	@Override
	public Inventory getInventoryByProductId(int product_id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		
		String sql = "SELECT * FROM INVENTORY WHERE product_id = :pid";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Inventory.class);
		query.setParameter("pid", product_id);
		List results = query.list();
		
		if(results.isEmpty())
		{
			return null;
		}
		
		Inventory i = (Inventory) results.get(0);
		return i;
	}

	@Override
	public void removeFromInventory(int id) {
		
		Session session = this.sessionFactory.getCurrentSession();
		boolean x=true;
		String sql="UPDATE INVENTORY SET quantity=:qid , isDeleted=:isDeleted where product_id=:id";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Inventory.class);
		query.setParameter("qid",0);
		query.setParameter("id", id);
		query.setParameter("isDeleted",x);
		int res = query.executeUpdate();
		System.out.println("RESULT         "+res+"\n \n");
		
		
		
		
		
		String sql2="UPDATE Product SET quantity=:qid where id=:id";
		SQLQuery query2 = session.createSQLQuery(sql2);
		query2.addEntity(Inventory.class);
		query2.setParameter("qid",0);
		query2.setParameter("id", id);
		int res2 = query2.executeUpdate();
		
		return;
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public Inventory getInventoryByProductName(String pname) {
	// TODO Auto-generated method stub
	Session session = this.sessionFactory.getCurrentSession();
	
	String sql = "SELECT * FROM INVENTORY WHERE name = :pid";
	SQLQuery query = session.createSQLQuery(sql);
	query.addEntity(Inventory.class);
	query.setParameter("pid", pname);
	List results = query.list();

	if(results.isEmpty())
		return null;
	Inventory i = (Inventory) results.get(0);
	return i;
	
	}

	
	
	



}