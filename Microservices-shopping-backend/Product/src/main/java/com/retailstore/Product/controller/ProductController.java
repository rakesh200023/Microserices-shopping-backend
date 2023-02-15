package com.retailstore.Product.controller;

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


import com.retailstore.Product.entities.Product;
import com.retailstore.Product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService pservice;
	
	@PostMapping("/addproduct")
	public ResponseEntity<?> addproduct(@RequestBody Product p){
		pservice.create(p);
		return new ResponseEntity<Product>(p,HttpStatus.CREATED);
	}
	
	@GetMapping("/searchproduct/{productId}")
	public ResponseEntity<?> searchproduct (@PathVariable("productId") int productId ){
		Product p=pservice.searchproduct(productId);
		if(p!=null)
			return new ResponseEntity<Product>(p,HttpStatus.OK);
		return new ResponseEntity<String>("Product not found",HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/updateproduct/{productId}")
	public ResponseEntity<?> updatecustomer (@PathVariable("productId") int productId, @RequestBody Product p ){
		String s=pservice.updateproduct(productId,p);
		return new ResponseEntity<String>(s,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteproduct/{productId}")
	public ResponseEntity<?> deleteproduct (@PathVariable("productId") int productId ){
		String s=pservice.deleteproduct(productId);
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}

}
