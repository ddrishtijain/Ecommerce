package com.futureforce.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getSession().getAttribute("userID")==null) {
			if(request.getRequestURI().equals("/Ecommerce/login")||request.getRequestURI().equals("/Ecommerce/signup")||request.getRequestURI().matches("/Ecommerce/resources/(.*)")) {
				
//				System.out.println("Hello1");
				return true;
			}
			else if(request.getRequestURI().equals("/Ecommerce/logout")) {
//				System.out.println("Hello3");
				return true;
			}
			else {
//				System.out.println(request.getRequestURI());
//				System.out.println("Hello2");

				response.sendRedirect("login");
//				System.out.println("Hello");
				return false;
			}
		}

		else
		{
			//System.out.println("shanu");
			String role = (String) request.getSession().getAttribute("role");
			//System.out.println(role);
			if(role.equals("customer"))
			{
				if(request.getRequestURI().equals("/Ecommerce/login")||request.getRequestURI().equals("/Ecommerce/signup")) {
	//				System.out.println("Hello1");
					response.sendRedirect("profile");
					return true;
				}
				else if(request.getRequestURI().equals("/product/add")||request.getRequestURI().equals("/remove/{id}")
						||request.getRequestURI().equals("/edit/{id}")||request.getRequestURI().equals("/inventoryHome")
						||request.getRequestURI().equals("/addToInventory")||request.getRequestURI().equals("/vendorAdd")
						||request.getRequestURI().equals("/updateToInventory")||request.getRequestURI().equals("/vendorUpdate")
						||request.getRequestURI().equals("/removeFromInventory")||request.getRequestURI().equals("/vendorRemove")) {
					response.sendRedirect("profile");
					return false;
				}
				else
					return true;
			}
			
			else if(role.equals("manager"))
			{
				if(request.getRequestURI().equals("/Ecommerce/login")||request.getRequestURI().equals("/Ecommerce/signup")) {
	//				System.out.println("Hello1");
					response.sendRedirect("profile");
					return true;
				}
				else if(request.getRequestURI().equals("/Ecommerce/addToCart")||request.getRequestURI().equals("/Ecommerce/addtocart")
						||request.getRequestURI().equals("/Ecommerce/viewcart")||request.getRequestURI().equals("/Ecommerce/placeorder")
						||request.getRequestURI().equals("/Ecommerce/delete")) {
					response.sendRedirect("profile");
					return false;
				}
				else
					return true;
			}
			
			
			else if(role.equals("delivery"))
			{
				System.out.println("Hi delivery guy!");
				if(request.getRequestURI().equals("/Ecommerce/login")||request.getRequestURI().equals("/Ecommerce/signup")) {
	//				System.out.println("Hello1");
					response.sendRedirect("profile");
					return true;
				}
				else if(request.getRequestURI().equals("/Ecommerce/product/add")||request.getRequestURI().equals("/Ecommerce/remove/{id}")
						||request.getRequestURI().equals("/Ecommerce/edit/{id}")||request.getRequestURI().equals("/Ecommerce/inventoryHome")
						||request.getRequestURI().equals("/Ecommerce/addToInventory")||request.getRequestURI().equals("/Ecommerce/vendorAdd")
						||request.getRequestURI().equals("/Ecommerce/updateToInventory")||request.getRequestURI().equals("/Ecommerce/vendorUpdate")
						||request.getRequestURI().equals("/Ecommerce/removeFromInventory")||request.getRequestURI().equals("/Ecommerce/vendorRemove")
						||request.getRequestURI().equals("/Ecommerce/addToCart")||request.getRequestURI().equals("/Ecommerce/viewcart")
						||request.getRequestURI().equals("/Ecommerce/placeorder")||request.getRequestURI().equals("/Ecommerce/delete")) {
					response.sendRedirect("profile");
					return false;
				}
				else
					return true;
			}
			
			
			
			else if(role.equals("da"))
			{
				if(request.getRequestURI().equals("/Ecommerce/login")||request.getRequestURI().equals("/Ecommerce/signup")) {
	//				System.out.println("Hello1");
					response.sendRedirect("profile");
					return true;
				}
				else if(request.getRequestURI().equals("/Ecommerce/product/add")||request.getRequestURI().equals("/Ecommerce/remove/{id}")
						||request.getRequestURI().equals("/Ecommerce/edit/{id}")||request.getRequestURI().equals("/Ecommerce/inventoryHome")
						||request.getRequestURI().equals("/Ecommerce/addToInventory")||request.getRequestURI().equals("/Ecommerce/vendorAdd")
						||request.getRequestURI().equals("/Ecommerce/updateToInventory")||request.getRequestURI().equals("/Ecommerce/vendorUpdate")
						||request.getRequestURI().equals("/Ecommerce/removeFromInventory")||request.getRequestURI().equals("/Ecommerce/vendorRemove")
						||request.getRequestURI().equals("/Ecommerce/addToCart")||request.getRequestURI().equals("/Ecommerce/viewcart")
						||request.getRequestURI().equals("/Ecommerce/placeorder")||request.getRequestURI().equals("/Ecommerce/delete")) {
					response.sendRedirect("profile");
					return false;
				}
				else
					return true;
			}

		}
		//System.out.println("Out of Else\n");

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {		
		
		
		
		
	}

	
	
}
