package com.retailstore.Inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailstore.Inventory.entities.Inventory;
import com.retailstore.Inventory.service.InventoryService;



@RestController
@RequestMapping("/inventory")
class InventoryController {
	
	@Autowired
	InventoryService iservice;
	
	@PostMapping("/addinventory")
	public ResponseEntity<?> addinventory(@RequestBody Inventory i){
		iservice.create(i);
		return new ResponseEntity<Inventory>(i,HttpStatus.CREATED);
	}
	
	@GetMapping("/getinventory/{inventoryId}")
	public ResponseEntity<?> getinventory (@PathVariable("inventoryId") int inventoryId ){
		Inventory p=iservice.getinventory(inventoryId);
		if(p!=null)
			return new ResponseEntity<Inventory>(p,HttpStatus.OK);
		return new ResponseEntity<String>("inventory not found",HttpStatus.NOT_FOUND);
	}
	@GetMapping("/getinventorybyproduct/{productId}")
	public ResponseEntity<?> getinventorybyproduct (@PathVariable("productId") int productId ){
		Inventory p=iservice.getinventorybyproduct(productId);
		if(p!=null)
			return new ResponseEntity<Inventory>(p,HttpStatus.OK);
		return new ResponseEntity<String>("product not in inventory",HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/updateinventory/{inventoryId}")
	public ResponseEntity<?> updateinventory (@PathVariable("inventoryId") int inventoryId, @RequestBody Inventory i ){
		String s=iservice.updateinventory(inventoryId,i);
		return new ResponseEntity<String>(s,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteinventory/{inventoryId}")
	public ResponseEntity<?> deleteinventory (@PathVariable("inventoryId") int inventoryId ){
		String s=iservice.deleteinventory(inventoryId);
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}

}
