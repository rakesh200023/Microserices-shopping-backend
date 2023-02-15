package com.retailstore.Inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailstore.Inventory.entities.Inventory;
import com.retailstore.Inventory.repository.InventoryRepository;




@Service
public class InventoryService {
	
	@Autowired
	InventoryRepository irepo;

	public Inventory create(Inventory i) {
		// TODO Auto-generated method stub
		return irepo.save(i);
	}

	public Inventory getinventory(int inventoryId) {
		// TODO Auto-generated method stub
		
		Inventory c1 = irepo.findByInventoryId(inventoryId);
		if(c1==null) {
			return null;
		}else {
			return c1;
		}
		
	}

	public String updateinventory(int inventoryId, Inventory i) {
		// TODO Auto-generated method stub
		Inventory p1=irepo.findByInventoryId(inventoryId);
		if(p1==null) {
			return "inventory doesnt exists";

		}
		p1.setProductId(i.getProductId());
		p1.setQuantity(i.getQuantity());
		irepo.save(p1);
		
		return "inventory updated successfully";

	}

	public String deleteinventory(int inventoryId) {
		// TODO Auto-generated method stub
		
		Inventory p1 = irepo.findByInventoryId(inventoryId);
		if(p1==null) {
			return "inventory doesnt exists";
		}else {
			irepo.delete(p1);
			return "inventory deleted successfully";
		}
		
	}

	public Inventory getinventorybyproduct(int productId) {
		// TODO Auto-generated method stub
		Inventory c1 = irepo.findByproductId(productId);
		if(c1==null) {
			return null;
		}else {
			return c1;
		}
	}

}
