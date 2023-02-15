package com.retailstore.Cart.service;

import javax.persistence.Entity;

import org.apache.catalina.startup.Catalina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailstore.Cart.entites.Cart;
import com.retailstore.Cart.repositories.CartRepository;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Service
public class CartService {
	@Autowired
	CartRepository cartrepo;

	public Cart createcart(Cart cart) {
		// TODO Auto-generated method stub
		System.out.println("cart service"+cart);
		return cartrepo.save(cart);
		
	}

	public Cart getitem(int cartId) {
		// TODO Auto-generated method stub
		Cart c1=cartrepo.findBycartId(cartId);
		if(c1==null) {
			return null;
		}else {
			return c1;
		}
		
	}

	public String updatecart(int cartId, Cart cart) {
		// TODO Auto-generated method stub
		System.out.println(cart);
		Cart c1 = cartrepo.findBycartId(cartId);
		if(c1==null) {
			return "Cart doesnt exists";
		}
		c1.setItems(cart.getItems());
		cartrepo.save(c1);
		return "updated successfully";
	}

	public String deletecart(int cartId) {
		// TODO Auto-generated method stub
		Cart c1 = cartrepo.findBycartId(cartId);
		if(c1==null) {
			return "Cart doesnt exists";
		}else {
			cartrepo.delete(c1);
			return "deleted successfully";
		}
	}
	
	
	
}
