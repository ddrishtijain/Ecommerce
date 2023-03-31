package com.futureforce.spring;


import java.util.ArrayList;
import java.util.Arrays;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.futureforce.spring.model.Cart;
import com.futureforce.spring.model.Product;
import com.futureforce.spring.service.CartService;
import com.futureforce.spring.service.OrderService;

import com.futureforce.spring.model.Cart;
import com.futureforce.spring.model.Product;
import com.futureforce.spring.model.Quantity;
import com.futureforce.spring.service.CartService;
import com.futureforce.spring.service.OrderService;


import com.futureforce.spring.model.Inventory;
import com.futureforce.spring.model.Product;
import com.futureforce.spring.service.InventoryService;
import com.futureforce.spring.service.ProductService;


@Controller
public class FController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private InventoryService inventoryService;

		
	
	@RequestMapping(value = "/search",method = RequestMethod.POST)
	public String searchProducts(	HttpSession session,
								Model model,
							   	@RequestParam("pattern") String pattern) {
				model.addAttribute("results",productService.searchProducts(pattern));
				return "search";

	
	}

	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String listPersons(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("listProducts", this.productService.listProducts());
		model.addAttribute("productsAndCat",productService.getProductsByCategory());
		return "product";
	}
	
	//Saurav For HomePage
	@RequestMapping(value = "/")
	public String indexPage() {
		return "index";
	}
	
	//Saurav For product Page
	@RequestMapping(value= "/product_detail/{id}")
	public String ShowDetail(@PathVariable("id") int id, Model model){
	
		//model.addAttribute("product", new Product());
		model.addAttribute("quantity",new Quantity());
		model.addAttribute("product", this.productService.getProductById(id));
		return "product_description";
		
	} 
	

	@RequestMapping(value= "/addToCart",method = RequestMethod.POST)
	public String AddToCart(@RequestParam("prod_id") int prod_id,
	@RequestParam("prod_name") String prod_name,
	@RequestParam("quantity") int quantity,Model model){
	System.out.println(quantity);
	System.out.println(prod_id + prod_name);
	return "cart";

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
    public String addtoCart(Model model,HttpSession session) {
    	model.addAttribute("Cart",new Cart());
    	return "addtocart";
    }
    
//    @RequestMapping(value="/addtocart",method=RequestMethod.POST)
//    public String addtoCart(@ModelAttribute("Cart") Cart cart,BindingResult result,HttpSession session) {
//    	int userId = (int) session.getAttribute("userID");
//    	System.out.println(userId);
//    	cartService.addToCart(cart,userId);
//    	return "redirect:/addtocart";
//    }
    
    @RequestMapping(value="/addtocart",method=RequestMethod.POST)
    public String addtoCart(HttpSession session,@RequestParam("prod_id") int prod_id,
    		@RequestParam("prod_name") String prod_name,
    		@RequestParam("quantity") int quantity) {
    	int userId = (int) session.getAttribute("userID");
    	System.out.println(userId);
    	cartService.addToCart(prod_id,quantity,userId);
    	return "redirect:/viewcart";
    }
    
    @RequestMapping(value="/viewcart",method=RequestMethod.GET)
    public String viewCart(Model model,HttpServletRequest request,HttpSession session) {
    	int userId = (int) session.getAttribute("userID");
    	List<Cart> cartList = cartService.showCart(userId);
    	model.addAttribute("cartlist",cartList);
    	model.addAttribute("Cart",new Cart());
    	int total=0;
    	if(cartList.isEmpty())
    		return "emptycart";
    	for(Cart temp: cartList)
    	{
    		total+= (temp.getQtyAdded()*Integer.parseInt(temp.getProduct().getPrice()));
    	}
    	model.addAttribute("total",total);
    	return "viewcart";
    }
    
    @RequestMapping(value="/placeorder",method=RequestMethod.GET)
    public String placeOrder(Model model,HttpSession session) {
    	int userId = (int) session.getAttribute("userID");
    	List<Cart> cartList = cartService.showCart(userId);
    	List<Integer> productId = new ArrayList<Integer>();
    	List<Integer> qty = new ArrayList<Integer>();
    	
    	for(Cart temp: cartList) {
    		productId.add(temp.getProduct().getId());
    		qty.add(temp.getQtyAdded());
    	}
    	

    	String success="";
    	String failure="";
    	int pid=0;
    	int flag=inventoryService.checkout(productId, qty);
    	System.out.println(flag+"---flag---");
    	if(flag == 0) {
    	int isEmpty = 1;
    	success = "Your order has been successfully placed";
    	model.addAttribute("isEmpty",isEmpty);
    	
//    	if(!cartList.isEmpty()) {
//    		

    	
//    		for(Cart temp: cartList) {
//        		orderService.addOrder(userId,temp);
//        	}
//    		
//    	}
    	orderService.addOrder(userId, cartList);
    	}

    	else 
    		{
    			failure="Your order was not placed because the following product went out of stock : "+
    						this.productService.getProductById(flag).getName();
    			
    		}
    		
    	model.addAttribute("success",success);
    	model.addAttribute("failure",failure);

    	return "placeorder";
    	
//    	else {
//    		return "viewcart";
//    	}
		
    	
    }
    
    @RequestMapping(value="/delete",method=RequestMethod.GET)
    public String deleteFromCart(@RequestParam("id") int id,Model model,HttpSession session)
    {	
    	int userId = (int)session.getAttribute("userID");
    	cartService.removeFromCart(id,userId);
    	return "redirect:/viewcart";
    }
    

    @RequestMapping(value="/updatecart",method=RequestMethod.GET)
    public String updateCartProductQuantity(@RequestParam("id") int id,@RequestParam("qty") int qty,Model model,HttpSession session)
    {
    	int userId = (int)session.getAttribute("userID");
    	cartService.updateCartProductQuantity(userId,id,qty);
    	model.addAttribute("Cart", new Cart());
    	System.out.println(qty);
    	return "redirect:/viewcart";
    }
    
//    @RequestMapping(value="/updatecart",method=RequestMethod.POST)
//    public String updateProductQuantity(@RequestParam("qty") int newqty)
//    {
////    	int newqty = cart.getQtyAdded();
////    	int newqty = (int) request.getAttribute("newqty");
//    	System.out.println(newqty);
//    	return "redirect:/viewcart";
//    }
    
    

    @RequestMapping(value="/inventoryHome")
    public String renderVendorPage(HttpSession session,Model model)
    {
    	String vendorid=((Integer) session.getAttribute("userID")).toString();

    	System.out.println(vendorid+"-----------------");
//    	model.addAttribute("inventory",new Inventory());
    	List<Inventory> li= this.inventoryService.listInventoryByVendor(vendorid);
    	if(li!=null)
    	{
    		model.addAttribute("disable",0);
    	}
    	else
    		model.addAttribute("disable",1);
    	model.addAttribute("listInventory",li);
    	System.out.println("\n \n \n \n \n  the list is as follows : "+  li);
    	for (Inventory i : li) {
    		System.out.println(i.getName());
    	}
    	return "inventoryHome";
    	
    }
    
    @RequestMapping (value="addToInventory")
    public String addInventoryHome(Model model)
    {
    	List<String> a=Arrays.asList("Shoes", "Shirt", "T-shirt", "Deodrant", "Watch" );
    	model.addAttribute("categories",a);

    	System.out.println("asfdasdasdasd a \n \n n \n");
    	return "addToInventory";

    }

	
    @RequestMapping(value="vendorAdd",method=RequestMethod.POST)
    public String showAdditions(HttpSession session,@RequestParam("name") String name,
    					@RequestParam("description") String description,
    					@RequestParam("quantity") int quantity, @RequestParam("category")String category, 
    				    @RequestParam("imageLink") String imageLink,@RequestParam("price") String price, Model model)
    {
    	
    	System.out.println("AYA VENDOR ADD MEIN \n");
    	
    	
    	String valid="";
    	
    	String vendorid=((Integer) session.getAttribute("userID")).toString();
    	
    	Inventory ab = this.inventoryService.getInventoryByProductName(name);
    	System.out.println("aya check ke baad");
    	if(ab!=null)
    	{
    		valid="Product Name Already exists";
    		model.addAttribute("valid",valid);
    		return "forward:addToInventory";
    	}
    	
    	
    	 Product product=new Product();
    	    product.setName(name);
    	    product.setQuantity(quantity);
    	    product.setDescription(description);
    	    product.setImage_link(imageLink);
    	    product.setCategory(category);
    	    product.setPrice(price);
    	    this.productService.addProduct(product);
    	    Inventory inventory=new Inventory();
    	    inventory.setName(name);
    	    inventory.setProduct(product);
    	    inventory.setVendorId(vendorid);
    	    inventory.setQuantity(quantity);
    	    this.inventoryService.addProductToInventory(inventory);
    	    return "forward:inventoryHome";
    }
    
    @RequestMapping(value="updateToInventory")
    public String updateInventoryHome(HttpSession session, Model model) {    	
    	System.out.println("Inventory home me ayaa--- \n");
    	
    	String vendorid=((Integer) session.getAttribute("userID")).toString();
//    	String vendorid="abyss";
    	
        model.addAttribute("inventory",new Inventory());
        List<Inventory> li= inventoryService.listInventoryByVendor(vendorid);
        model.addAttribute("listInventory",li);
        
    	return "updateToInventory";
    }
    
    @RequestMapping(value="vendorUpdate", method=RequestMethod.POST)
    public String updateInventory(Model model, @RequestParam("product_name") String pname,
			@RequestParam("quantity") int quantity, 
			@RequestParam("operation") String operation,
			@RequestParam("price") String price,
			@RequestParam("description") String description ) {
    	
    	
    	
    	System.out.println(quantity+" ****************vggyg");

    	System.out.println("operation = "+ operation);
    	if(operation.equals("Add")) {
    		System.out.println("hehehkjsbfhisbch      \n n\n \n \n  ");
    		quantity = -quantity;
    	}
    	
//    	Inventory inventory=new Inventory();
    	Inventory inventory=inventoryService.getInventoryByProductName(pname);
//    	inventory.setQuantity(inventory.getQuantity()quantity);
    	int success = this.inventoryService.updateInventory(inventory.getProduct().getId(), quantity);
    	String str = null,fail=null;
    	if(success == 1) {
        	System.out.println("\n \n \n \n \n Success = "+success);
    		str = "successfully updated";
    		fail="";
    		System.out.println("\n \n \n n\n  price = "+price + "\n \n"+description+" \n  \n ");
    		Product product = this.productService.getProductById(inventory.getProduct().getId());
    		

    		if(!price.isEmpty())
        	{
        		product.setPrice(price);
        	}

    		if(!description.isEmpty())
        	{
        		product.setDescription(description);
        	}
    		
    		this.productService.updateProduct(product);


    	}
    	else {
    		fail = "Failed to update";
    		str="";
    	}
    	
    	
    	
    	System.out.println(str);
    	model.addAttribute("success", str);
    	model.addAttribute("failure", fail);
    	
    	return "forward:inventoryHome";
    	
    }
    
    @RequestMapping(value="removeFromInventory")
    public String removeFromInventory(HttpSession session, Model model) {

    	String vendorid=((Integer) session.getAttribute("userID")).toString();
//    	String vendorid="abyss";
    	
        model.addAttribute("inventory",new Inventory());
        List<Inventory> li= inventoryService.listInventoryByVendor(vendorid);
        model.addAttribute("listInventory",li);
    	return "removeFromInventory";
    }
    
    
    @RequestMapping(value="vendorRemove", method=RequestMethod.POST)
    public String removeFromInventory(@RequestParam("product_name") String pname) {
    	Inventory inventory=new Inventory();
    	inventory=inventoryService.getInventoryByProductName(pname);
    	Product p = new Product();
    	p=this.productService.getProductById(inventory.getProduct().getId());
    	System.out.println("IDDDDDDDDDD "+ p.getId());
    	this.inventoryService.removeFromInventory(p.getId());
    //	this.inventoryService.removeFromInventory(inventory);
    //	this.productService.removeProduct(p.getId());
    	return "forward:inventoryHome";
    	
    }
    
    @RequestMapping(value="checkoutCart")
    public String dummy(Model model)
    {
    	
//    	Inventory i = new Inventory();
//    	i = this.inventoryService.getInventoryByProductId(1);
//    	i.setQuantity(50);
//    	int success = this.inventoryService.updateInventory(i);
    	List<Integer> product_ids=Arrays.asList(1, 2, 3);
    	List<Integer> quantities = Arrays.asList(10, 10, 35);
//    	
//    	int i=0, success;
//		List<Inventory> inventories=new ArrayList<Inventory>();
//		for(i=0;i<product_ids.size();i++)
//		{
//			int tmp=0;
//			Inventory inventory = this.inventoryService.getInventoryByProductId(product_ids.get(i));
//			inventory.setQuantity(quantities.get(i));
//			tmp=this.inventoryService.updateInventory(inventory);
//			inventories.add(inventory);
//			if(tmp==0) {
//				break;
//			}
//		}
//
//		if(i<product_ids.size())
//		{
//			for(int j=i-1;j>=0;j--)
//			{
//				Inventory inventory = inventories.get(j);
//				inventory.setQuantity(-quantities.get(j));
//				this.inventoryService.updateInventory(inventory);
//			}
//			success = 0;
//		}
//		else
//			success = 1;
	
    	int success = this.inventoryService.checkout(product_ids, quantities);
    	System.out.println(" \n \n \n \n Dummy Success = "+success);
    	

    	return "forward:inventoryHome";
    }
    

    
    @RequestMapping (value="checkProductName", method=RequestMethod.GET)
    @ResponseBody
    public String matchName(@RequestParam("product_name") String pname) {
    	
    Inventory i = this.inventoryService.getInventoryByProductName(pname);
    //System.out.println("ayayayayayyayaya.....");
    if (i==null) {
    System.out.println("Product name already exists");
    return "N";
    }
    else {
    return "Y";
    }
    }
    
    
    		
    	
    }
