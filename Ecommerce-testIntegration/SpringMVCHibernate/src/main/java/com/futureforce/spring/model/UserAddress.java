package com.futureforce.spring.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_address")
public class UserAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	
	@Column(name="recipient_name")
	private String recipientName;
	
	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientPhone() {
		return recipientPhone;
	}

	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}

	@Column(name="recipient_phone")
	private String recipientPhone;


	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "country")
	private String country;

	@Column(name = "state")
	private String state;

	@Column(name = "zipcode")
	private String zipcode;

	@Column(name = "is_default")
	private boolean isDefault;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private UserInfo currUser;

	public UserInfo getCurrUser() {
		return currUser;
	}

	public void setCurrUser(UserInfo currUser) {
		this.currUser = currUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	
	public boolean getIsDefault() {
		return isDefault;
	}
	
	public UserAddress() {
		// TODO Auto-generated constructor stub
	}



	public UserAddress(String recipientName, String recipientPhone, String address, String city, String country,
			String state, String zipcode, boolean isDefault) {
		this.recipientName = recipientName;
		this.recipientPhone = recipientPhone;
		this.address = address;
		this.city = city;
		this.country = country;
		this.state = state;
		this.zipcode = zipcode;
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return "UserAddress [address=" + address + ", city=" + city + ", country=" + country + ", state=" + state
				+ ", zipcode=" + zipcode + ", isDefault=" + isDefault + "]";
	}
	
	

}