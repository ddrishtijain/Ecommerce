package com.futureforce.spring.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futureforce.spring.dao.InventoryDAO;
import com.futureforce.spring.model.Inventory;
import com.futureforce.spring.model.Product;

@Service
public class InventoryServiceImpl implements InventoryService{

	@Autowired
	private InventoryDAO inventoryDAO;

	@Override
	@Transactional
	public void addProductToInventory(Inventory u) {
		// TODO Auto-generated method stub
		this.inventoryDAO.addProduct(u);
	}

	@Override
	@Transactional
	public int updateInventory(int product_id, int quantity) {
		return this.inventoryDAO.updateProduct(product_id, quantity);
		// TODO Auto-generated method stub
		

	}

	@Override
	@Transactional
	public List<Inventory> listInventoryByVendor(String vendorId) {
		// TODO Auto-generated method stub
		List<Inventory> list= this.inventoryDAO.listProductIds(vendorId);
		return list;
	}

	@Override
	@Transactional
	public Inventory getInventoryById(int id) {
		
		
		Inventory i = this.inventoryDAO.getInventoryById(id);
		// TODO Auto-generated method stub
		return i;
	}

	@Override
	@Transactional
	public Inventory getInventoryByProductId(int id) {
		// TODO Auto-generated method stub
		Inventory i = this.inventoryDAO.getInventoryByProductId(id);
		
		return i;
	}

	@Override
	@Transactional
	public void removeFromInventory(int id) {
		this.inventoryDAO.removeFromInventory(id);
		// TODO Auto-generated method stub
		
	}

	
	@Override
	@Transactional
	public Inventory getInventoryByProductName(String pname) {
	// TODO Auto-generated method stub
	return this.inventoryDAO.getInventoryByProductName(pname);
	}
	
	@Override
	@Transactional
	public int checkout(List<Integer> product_ids, List<Integer> quantities) {
		    	
    	int i=0, success;
//		List<Inventory> inventories=new ArrayList<Inventory>();
		for(i=0;i<product_ids.size();i++)
		{
			int tmp=0;
			tmp=this.updateInventory(product_ids.get(i), quantities.get(i));
			//System.out.println(tmp+"-------------tmp value");
			if(tmp==0) {
				//System.out.println("aana chahiye------\n\n\n");
				break;
			}
		}

		if(i<product_ids.size())
		{
		
			for(int j=i-1;j>=0;j--)
			{
				this.updateInventory(product_ids.get(j), -quantities.get(j));
			}
			return product_ids.get(i);
		}
		else
			return 0;
	}




}
