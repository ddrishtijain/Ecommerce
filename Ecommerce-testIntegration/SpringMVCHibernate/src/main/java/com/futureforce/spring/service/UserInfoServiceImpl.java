package com.futureforce.spring.service;

import java.util.List;


import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futureforce.spring.dao.UserInfoDAO;
import com.futureforce.spring.model.Order;
import com.futureforce.spring.model.Product;
import com.futureforce.spring.model.UserAddress;
import com.futureforce.spring.model.UserInfo;



@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDAO userInfoDAO;
	
	@Override
	@Transactional
	public Integer validateUser(String email, String password) {
		
		return userInfoDAO.validateUser(email, password);
	
	}

	@Override
	@Transactional
	public int addUser(String firstName, String lastName, String email, String psw, String repeatPsw, String phone,

			String role, String address, String city, String state, String country, String zipcode,
			String recipientName, String recipientPhone) {

		return userInfoDAO.addUser(firstName, lastName, email, psw, repeatPsw, phone, role, address, city, state, country, zipcode, recipientName, recipientPhone);
	}
	
	@Override
	@Transactional
	public UserInfo getUserbyUserID(int userID) {
		return userInfoDAO.getUserbyUserID(userID);
	}
	
	@Override
	@Transactional
	public List<UserAddress> getAddressesByUserID(int userID){
		return userInfoDAO.getAddressesByUserID(userID);
	}


	@Override
	@Transactional
	public void addAddress(int userID, String address, String city, String state, String country, String zipcode,
			String recipientName, String recipientPhone) {
		
		 userInfoDAO.addAddress(userID, address, city, state, country, zipcode, recipientName, recipientPhone);
		
	}

	@Override
	@Transactional
	public void setDefaultAddress(int userID, int addressID) {
		
		userInfoDAO.setDefaultAddress(userID, addressID);
		
	}

	@Override
	@Transactional
	public void deleteAddress(int userID, Integer addressID) {
		userInfoDAO.deleteAddress(addressID);
	}
	@Override
	@Transactional
	public UserAddress getDefaultAddressAsUA(int userID) {
		 return userInfoDAO.getDefaultAddressAsUA(userID);
	}
	@Override
	@Transactional
	public String getPincodeForThisID(int userID) {
		return userInfoDAO.getPincodeForThisID(userID);
	}
	@Override
	@Transactional
	public List<UserInfo> getListOfDeliveryExecs(){
		return userInfoDAO.getListOfDeliveryExecs();
	}
	
	
}
