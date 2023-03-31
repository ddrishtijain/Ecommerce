package com.futureforce.spring.dao;


import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpSession;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.futureforce.spring.model.Order;
import com.futureforce.spring.model.Product;
import com.futureforce.spring.model.UserAddress;
import com.futureforce.spring.model.UserInfo;





@Repository
public class UserInfoDAOImpl implements UserInfoDAO {

	@Autowired
	private SessionFactory factory;
	
	@Override
	public Integer validateUser(String email, String password) {		
		

		Session session1 = factory.getCurrentSession();
		
		UserInfo actualUser = (UserInfo) session1.createQuery("from UserInfo where email= :Email")
							.setParameter("Email", email).uniqueResult();				
		
		if(actualUser != null) {			
			if(actualUser.getPassword().equals(password))
				{return actualUser.getId();}
		}
		
		return null;
	}

	@Override
	public int addUser(String firstName, String lastName, String email, String psw, String repeatPsw, String phone,String role, String address, String city, String state, String country, String zipcode,String recipientName, String recipientPhone) {

		Session session = factory.getCurrentSession();
		
		UserInfo checkUserEmail = (UserInfo) session.createQuery("from UserInfo where email= :inEmail")
										.setParameter("inEmail", email).uniqueResult();
		
		if(checkUserEmail == null) {
		
			UserInfo tempUser = new UserInfo(firstName, lastName, email, psw, role, phone);
			
			session.save(tempUser);
			
			if(role.equals("customer")||role.equals("delivery")) {
			

				UserAddress newAddress = new UserAddress(recipientName, recipientPhone, address, city, country, state, zipcode, true);
				
				newAddress.setCurrUser(tempUser);
				
				tempUser.addAddress(newAddress);
				
				session.save(newAddress);
				
			}			
		
			return tempUser.getId();
		
		}
				
		return -1;
		
	}
	
	@Override
	public UserInfo getUserbyUserID(int userID) {
		
		Session session = factory.getCurrentSession();
		
		UserInfo retUser = (UserInfo) session.createQuery("from UserInfo where id= :userID")
				.setParameter("userID", userID).uniqueResult();
		
		System.out.println(retUser.getAddresses());
		
		return retUser;
	}
	
	@Override
	public List<UserAddress> getAddressesByUserID(int userID) {
		Session session = factory.getCurrentSession();
		
		UserInfo tempUser = (UserInfo) session.createQuery("from UserInfo where id= :userID")
				.setParameter("userID", userID).uniqueResult();
		
		return tempUser.getAddresses();
		
	}
	


	@Override
	public void addAddress(int userID, String address, String city, String state, String country, String zipcode,
			String recipientName, String recipientPhone) {
		
		Session session = factory.getCurrentSession();
		
		
		UserInfo currUser = (UserInfo) session.get(UserInfo.class, userID);
		
		UserAddress temp = new UserAddress(recipientName, recipientPhone, address, city, country, state, zipcode, false);
		
		temp.setCurrUser(currUser);
		
		session.save(temp);
		
	}

	@Override
	public void setDefaultAddress(int userID, int addressID) {

		Session session = factory.getCurrentSession();
		
		session.createQuery("update UserAddress set isDefault= :setFalse where currUser= :tempUser")
			.setParameter("setFalse", false)
			.setParameter("tempUser", (UserInfo)session.get(UserInfo.class,userID))
			.executeUpdate();
		
		session.createQuery("update UserAddress set isDefault= :setTrue where id= :addressID")
			.setParameter("setTrue", true)
			.setParameter("addressID", addressID)
			.executeUpdate();
		
		
	}

	@Override
	public void deleteAddress(int addressID) {

		Session session = factory.getCurrentSession();
		
		UserAddress tempAddress = new UserAddress();		
		
		tempAddress.setId(addressID);
		
		session.delete(tempAddress);
		
		return;
	}
	@Override
	public UserAddress getDefaultAddressAsUA(int userID) {
		
		Session session = factory.getCurrentSession();		
		UserAddress add = (UserAddress) session.createQuery("from UserAddress where currUser= :tempUser and isDefault = :setTrue")
				.setParameter("tempUser", (UserInfo)session.get(UserInfo.class,userID))
				.setParameter("setTrue", true).uniqueResult();
		UserInfo temp = getUserbyUserID(userID);
		
		if(add.getRecipientName().equals("")) {
			
			add.setRecipientName(temp.getFirstName()+" "+temp.getLastName());
			System.out.println("Hello Name : "+add.getRecipientName());
		}
		if(add.getRecipientPhone().equals("")) {
			add.setRecipientPhone(temp.getPhoneNumber());
			System.out.println("Hello Phone : "+add.getRecipientPhone());
		}
		
		return add;
		
	}
	

	@Override
	public String getPincodeForThisID(int userID) {
		Session session = factory.getCurrentSession();
		UserAddress add = (UserAddress) session.createQuery("from UserAddress where currUser= :tempUser and isDefault = :setTrue")
				.setParameter("tempUser", (UserInfo)session.get(UserInfo.class,userID))
				.setParameter("setTrue", true).uniqueResult();
		return add.getZipcode();
	}
	
	@Override
	public List<UserInfo> getListOfDeliveryExecs(){
		Session session = factory.getCurrentSession();
		List<UserInfo> listOfExecs = (List<UserInfo>) session.createQuery("from UserInfo where role= :getRole")
				.setParameter("getRole", "delivery")
				.list();
		return listOfExecs;
	}
	
	
	
	
}
