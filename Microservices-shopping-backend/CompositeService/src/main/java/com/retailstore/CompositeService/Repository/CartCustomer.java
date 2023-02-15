package com.retailstore.CompositeService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retailstore.CompositeService.entites.CustomerCart;

public interface CartCustomer extends JpaRepository<CustomerCart, Integer>{

	CustomerCart findBycustomerId(int customerId);

}
