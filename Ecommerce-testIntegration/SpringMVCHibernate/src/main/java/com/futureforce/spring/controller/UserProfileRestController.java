package com.futureforce.spring.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.futureforce.spring.service.UserInfoService;

@RestController
public class UserProfileRestController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(value="setDefaultAddress", method = {RequestMethod.POST, RequestMethod.GET})	
	public String setDefaultAddress(@RequestBody String json,HttpSession session) {
		int userID = (int) session.getAttribute("userID");
		String temp = json.substring(1, json.length()-1);
		Integer addressID = Integer.parseInt(temp);
		userInfoService.setDefaultAddress(userID, addressID);
		return "ok";
	}
	
	@RequestMapping(value="deleteAddress", method = {RequestMethod.POST, RequestMethod.GET})	
	public String deleteAddress(@RequestBody String json,HttpSession session) {
		int userID = (int) session.getAttribute("userID");		
		String temp = json.substring(1, json.length()-1);
		System.out.println(temp);
		Integer addressID = Integer.parseInt(temp);
		userInfoService.deleteAddress(userID, addressID);
		return "ok";
	}
	
}
