package com.futureforce.spring;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.futureforce.spring.model.Cart;
import com.futureforce.spring.model.Product;
import com.futureforce.spring.service.CartService;
import com.futureforce.spring.service.OrderService;
import com.futureforce.spring.service.ProductService;


@Controller
public class FController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired(required=true)
	@Qualifier(value="productService")
	public void setPersonService(ProductService ps){
		this.productService = ps;
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String listPersons(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("listProducts", this.productService.listProducts());
		return "product";
	}
	
	//For add and update product both
	@RequestMapping(value= "/product/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("product") Product p){
		
		if(p.getId() == 0){
			//new person, add it
			this.productService.addProduct(p);
		}else{
			//existing person, call update
			this.productService.updateProduct(p);
		}
		
		return "redirect:/products";
		
	}
	
	@RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){
		
        this.productService.removeProduct(id);
        return "redirect:/products";
    }
 
    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("product", this.productService.getProductById(id));
        model.addAttribute("productPersons", this.productService.listProducts());
        return "product";
    }
   
    @RequestMapping(value="/addtocart",method=RequestMethod.GET)
    public String addtoCart(Model model) {
    	model.addAttribute("Cart",new Cart());
    	return "addtocart";
    }
    
    @RequestMapping(value="/addtocart",method=RequestMethod.POST)
    public String addtoCart(@ModelAttribute("Cart") Cart cart,BindingResult result) {
    	cartService.addToCart(cart);
    	return "redirect:/addtocart";
    }
    
    @RequestMapping(value="/viewcart",method=RequestMethod.GET)
    public String viewCart(Model model,HttpServletRequest request) {
    	List<Cart> cartList = cartService.showCart();
    	model.addAttribute("cartlist",cartList);
    	int total=0;
    	for(Cart temp: cartList)
    	{
    		total+= (temp.getQtyAdded()*temp.getProduct().getPrice());
    	}
    	model.addAttribute("total",total);
    	return "viewcart";
    }
    
    @RequestMapping(value="/placeorder",method=RequestMethod.GET)
    public String placeOrder(Model model) {
    	List<Cart> cartList = cartService.showCart();
    	
    	int isEmpty = 1;
    	
    	model.addAttribute("isEmpty",isEmpty);

    	if(!cartList.isEmpty()) {
    		
    		for(Cart temp: cartList) {
        		orderService.addOrder(temp);
        	}
    		
    	}
    	return "placeorder";
//    	else {
//    		
//    		model.addAttribute("isEmpty",isEmpty);
//    		return "viewcart";
//    	}
		
    	
    }
    
    @RequestMapping(value="/delete",method=RequestMethod.GET)
    public String deleteFromCart(@RequestParam("id") int id,Model model)
    {	
    	System.out.println("dfdfdf");
    	cartService.removeFromCart(id);
    	return "redirect:/viewcart";
    }
	
}
