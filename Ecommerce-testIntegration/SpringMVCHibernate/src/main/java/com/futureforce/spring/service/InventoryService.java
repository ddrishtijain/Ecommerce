package com.futureforce.spring.service;

import java.util.List;

import com.futureforce.spring.model.Inventory;
import com.futureforce.spring.model.Product;

public interface InventoryService {


	public void addProductToInventory(Inventory u);

	public int updateInventory(int product_id, int quantity);

	public List<Inventory> listInventoryByVendor(String vendorId);
	public Inventory getInventoryById(int id);
	public Inventory getInventoryByProductId(int id);
	

	public Inventory getInventoryByProductName(String pname);
	public int checkout(List<Integer> product_ids, List<Integer> quantites);



	public void removeFromInventory(int id);
	
}
