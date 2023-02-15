package com.retailstore.Cart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retailstore.Cart.entites.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	Cart findBycartId(int cartId);

}
