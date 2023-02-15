
package com.retailstore.Cart.controller;

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

import com.retailstore.Cart.entites.Cart;
import com.retailstore.Cart.service.CartService;



@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartService cartservice;
	
	@PostMapping("/addcart")
	public ResponseEntity<Cart> createcart(@RequestBody Cart cart){
		System.out.println(cart);
		cartservice.createcart(cart);
		return new ResponseEntity<Cart>(cart,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getitems/{cartId}")
	public ResponseEntity<?> getcart(@PathVariable ("cartId") int cartId){
		Cart c=cartservice.getitem(cartId);
		if(c!=null)
			return new ResponseEntity<Cart>(c,HttpStatus.OK);
		return new ResponseEntity<String>("Cart not found",HttpStatus.NOT_FOUND);
		
	}
	
	@PutMapping("/updatecart/{cartId}")
	public ResponseEntity<?> updatecart(@PathVariable("cartId") int cartId, @RequestBody Cart cart ){
		System.out.println(cart);
		String s=cartservice.updatecart(cartId,cart);
		return new ResponseEntity<String>(s,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deletecart/{cartId}")
	public ResponseEntity<?> deletecart (@PathVariable("cartId") int cartId ){
		String s=cartservice.deletecart(cartId);
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
}
