package com.futureforce.spring.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

//import java.net.http.HttpRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.futureforce.spring.model.UserAddress;
import com.futureforce.spring.model.UserInfo;
import com.futureforce.spring.model.UserOrder;
import com.futureforce.spring.model.Order;
import com.futureforce.spring.model.OrderDetail;
import com.futureforce.spring.service.OrderService;
import com.futureforce.spring.service.UserInfoService;


@Controller
public class UserProfileController {

	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String login(HttpSession session) {
		return "login";
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String processLogin(	HttpSession session,
								Model model,
							   	@RequestParam("email") String email, 
							   	@RequestParam("password") String password) {
				
		Integer isValid = userInfoService.validateUser(email, password);
		if(isValid!=null) {

			UserInfo temp = userInfoService.getUserbyUserID(isValid);
			session.setAttribute("userID", isValid);

			session.setAttribute("firstName", temp.getFirstName());
			session.setAttribute("lastName", temp.getLastName());
			session.setAttribute("role", temp.getRole());
			return "redirect:/profile";

		
		}
		else {
			model.addAttribute("email", email);
			model.addAttribute("password", password);
			model.addAttribute("errorMsg", "Email/Password is incorrect");
			return "login";
		}

	
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("userID");

		session.removeAttribute("firstName");
		session.removeAttribute("lastName");
		session.removeAttribute("role");
		return "redirect:/login";
	}
	
	@RequestMapping(value="/success", method= RequestMethod.GET)
	public String success() {
		return "login-success";
	}
	
	@RequestMapping(value = "/signup", method=RequestMethod.GET)
	public String signup() {
//		System.out.println("Inside signup controller");
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method=RequestMethod.POST)
	public String processSignup(Model model,
								@RequestParam("firstName") String firstName,
								@RequestParam("lastName") String lastName,
								@RequestParam("email") String email,
								@RequestParam("psw") String psw,
								@RequestParam("psw-repeat") String repeatPsw,
								@RequestParam("phone") String phone,
								@RequestParam("role") String role,
								@RequestParam("recipientName") String recipientName,
								@RequestParam("recipientPhone") String recipientPhone,
								@RequestParam("address") String address,
								@RequestParam("city") String city,
								@RequestParam("state") String state,
								@RequestParam("country") String country,
								@RequestParam("zipcode") String zipcode ) {
		
		

		int res = userInfoService.addUser(firstName, lastName, email, psw, repeatPsw, phone, role, address, city, state, country, zipcode, recipientName, recipientPhone);
		
		if(res == -1) {
			model.addAttribute("errorMsg", "Email already exists");
			model.addAttribute("firstName", firstName);
			model.addAttribute("lastName", lastName);
			model.addAttribute("phone", phone);
			model.addAttribute(role, "selected");
			model.addAttribute("address", address);
			model.addAttribute("city", city);
			model.addAttribute("state", state);
			model.addAttribute("country", country);
			model.addAttribute("zipcode", zipcode);

			model.addAttribute("recipientName",recipientName);
			model.addAttribute("recipientPhone",recipientPhone);
			return "signup";
		}
		
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String loadPersonalInfo(Model model1, HttpSession session) {
		int userID = (int) session.getAttribute("userID");
		
		UserInfo currUser = (UserInfo) userInfoService.getUserbyUserID(userID);
		
		List<UserOrder> userOrders = orderService.getOrdersByUserId(userID);

//		for (UserOrder userOrder : userOrders) {
//			System.out.println(userOrder.getOrder().getOrderId());
//			for (OrderDetail temp : userOrder.getOrderDetails()) {
//				System.out.println(temp.getProductName());
//			}
//		}
		
		model1.addAttribute("userOrders", userOrders);
		model1.addAttribute("currUser", currUser);
		model1.addAttribute("currUIS",userInfoService);

		return "profile";
	}
	
	@RequestMapping(value="/addAddress", method = RequestMethod.GET)
	public String addAddress() {
		return "addAddress";
	}
	
	@RequestMapping(value="/addAddress", method = RequestMethod.POST)
	public String addAddressToDatabase(	HttpSession session,
										@RequestParam("address") String address,
										@RequestParam("city") String city,
										@RequestParam("state") String state,
										@RequestParam("country") String country,
										@RequestParam("zipcode") String zipcode,
										@RequestParam("recipientName") String recipientName,
										@RequestParam("recipientPhone") String recipientPhone) {
		
		userInfoService.addAddress((int)session.getAttribute("userID"), address, city, state, country, zipcode, recipientName, recipientPhone);
		
		return "redirect:/profile";
	}
	
	
	
	
}
