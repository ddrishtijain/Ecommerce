package com.futureforce.spring.dao;

import java.util.List;
import com.futureforce.spring.model.Inventory;

public interface InventoryDAO {

	
	public void addProduct(Inventory i);
	public int updateProduct(int product_id, int quantity);
	public List<Inventory> listProductIds(String vendorId);
	Inventory getInventoryById(int id);
	public Inventory getInventoryByProductId(int id);
	public void removeFromInventory(int id);
	public Inventory getInventoryByProductName(String pname);
//	int updateProduct2(int myQuantity, int pid);


}