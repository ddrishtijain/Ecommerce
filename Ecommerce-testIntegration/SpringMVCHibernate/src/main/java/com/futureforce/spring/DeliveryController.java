package com.futureforce.spring;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.futureforce.spring.model.Delivery;
import com.futureforce.spring.service.DeliveryService;
import com.futureforce.spring.service.UserInfoService;

@Controller
public class DeliveryController {

	@Autowired
	private DeliveryService deliveryService;
	
	@Autowired
	private UserInfoService userInfoService;


	@RequestMapping(value = "/delivery/assign/{orderId}", method = RequestMethod.GET)
	public String assignDelivery(@PathVariable("orderId") int orderId, Model model) {
		deliveryService.addDelivery(orderId);
		return "test";
	}

	@RequestMapping(value = "/delivery/{deliveryExecutiveId}", method = RequestMethod.GET)
	public String deliveryHome(@PathVariable("deliveryExecutiveId") int deliveryExecutiveId,
							   @RequestParam(value = "date", required = false) String date,
							   Model model) {
		String Name = userInfoService.getUserbyUserID(deliveryExecutiveId).getFirstName();
		List<Delivery> deliveryDetails;
		
		Date dateToday = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		//System.out.println("Hello: " + formatter.format(dateToday));
		
		if(date == null)
			deliveryDetails = this.deliveryService.getListDeliveryByDate(deliveryExecutiveId, formatter.format(dateToday));
		else
			deliveryDetails = this.deliveryService.getListDeliveryByDate(deliveryExecutiveId, date);
		
		System.out.println(deliveryDetails);
		
		int ordersCompleted = 0;
		int ordersPending = 0;
		
		for (Delivery d : deliveryDetails) {
			
			if(d.getOrderId().getStatus().equals("Delivered"))
				ordersCompleted += 1;
			else
				ordersPending += 1;
		}
		
		
		//if queried date parameter is same as today's date, then set date to NULL
		if(date != null && date.equals(formatter.format(dateToday)))
			date = null;
		
		model.addAttribute("totalOrders", deliveryDetails.size());
		model.addAttribute("deliveryExeName", Name);
		model.addAttribute("deliveryDetails", deliveryDetails);
		model.addAttribute("ordersPending", ordersPending);
		model.addAttribute("ordersCompleted", ordersCompleted);
		model.addAttribute("date", date);
		
		System.out.println("Delivery Executive Id: " + deliveryExecutiveId);
		
		return "deliveryHome";
	}
	
	@RequestMapping(value = "/delivery/{dId}/updateStatus/{orderId}/{orderStatus}", method = RequestMethod.GET)
	public String updateStatus(@PathVariable("dId") int dId,
							   @PathVariable("orderId") int orderId, 
							   @PathVariable("orderStatus") String orderStatus,
							   @RequestParam(value = "date", required = false) String date,
							   Model model) {
		
		Boolean isUpdated = this.deliveryService.updateStatus(orderId, orderStatus);
		
		if(isUpdated) {
			System.out.println("Successfully updated");
		}
		
		else {
			System.out.println("Redirect to Home page");
		}
		
		if(date == null)
			return "redirect:/delivery/" + dId;
		else
			return "redirect:/delivery/" + dId + "?date=" + date;
	}
	
	@RequestMapping(value = "/deliveryAdmin/updateStatus/{orderId}/{orderStatus}", method = RequestMethod.GET)
	public String updateStatusAdmin(@PathVariable("orderId") int orderId, 
							   		@PathVariable("orderStatus") String orderStatus,
							   		@RequestParam(value = "date", required = false) String date,
							   		Model model) {
		
		Boolean isUpdated = this.deliveryService.updateStatus(orderId, orderStatus);
		
		if(isUpdated) {
			System.out.println("Status successfully updated.");
		}
		
		else {
			System.out.println("Redirect to Home page");
		}
		
		if(date == null)
			return "redirect:/deliveryAdmin";
		else
			return "redirect:/deliveryAdmin?date="+date;
	}	
	
	@RequestMapping(value = "/deliveryAdmin", method = RequestMethod.GET)
	public String deliveryAdmin(@RequestParam(value = "date", required = false) String date, Model model) {
		
		System.out.println("Hello: " + date);
		
		List<Delivery> allDeliveries;
		
		Date dateToday = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		
		if(date == null)
			allDeliveries = this.deliveryService.getListDeliveryByDate(0, formatter.format(dateToday));
		else
			allDeliveries = this.deliveryService.getListDeliveryByDate(0, date);
		
		int ordersCompleted = 0;
		int ordersPending = 0;
		
		for (Delivery d : allDeliveries) {
			
			if(d.getOrderId().getStatus().equals("Delivered"))
				ordersCompleted += 1;
			else
				ordersPending += 1;
		}
		
		//if queried date parameter is same as today's date, then set date to NULL
		if(date != null && date.equals(formatter.format(dateToday)))
			date = null;
		
		model.addAttribute("totalOrders", allDeliveries.size());
		model.addAttribute("allDeliveries", allDeliveries);
		model.addAttribute("ordersPending", ordersPending);
		model.addAttribute("ordersCompleted", ordersCompleted);
		model.addAttribute("date", date);
		
		return "deliveryAdmin";
	}
	
	
	@RequestMapping(value = "/deliveryAdmin/viewOrderInfo/{deliveryId}", method = RequestMethod.GET)
	public String orderDetails(@PathVariable("deliveryId") int deliveryId, Model model) {
		
		List deliveryDetail = this.deliveryService.getAssignedDeliveryDetails(deliveryId);
		
		model.addAttribute("deliveryDetail", deliveryDetail.get(0));
		
		return "deliveryAdminOrderDetails";
	}

}
