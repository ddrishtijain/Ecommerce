package com.futureforce.spring.dao;

import java.util.List;




import com.futureforce.spring.model.Order;
import com.futureforce.spring.model.Product;
import com.futureforce.spring.model.UserAddress;
import com.futureforce.spring.model.UserInfo;


public interface UserInfoDAO {
	
	public Integer validateUser(String email, String password);
	
	public int addUser(	String firstName,
			String lastName,
			String email,
			String psw,
			String repeatPsw,
			String phone,
			String role,
			String address,
			String city,
			String state,
			String country,
			String zipcode,
			String recipientName,
			String recipientPhone);

	
	public UserInfo getUserbyUserID(int userID);
	public List<UserAddress> getAddressesByUserID(int userID);
	

	public void addAddress(int userID, String address, String city, String state, String country, String zipcode, String recipientName, String recipientPhone);

	public void setDefaultAddress(int userID, int addressID);
	
	public void deleteAddress(int addressID);

	public UserAddress getDefaultAddressAsUA(int userID);

	public String getPincodeForThisID(int userID);
	public List<UserInfo> getListOfDeliveryExecs();

	
	
}
