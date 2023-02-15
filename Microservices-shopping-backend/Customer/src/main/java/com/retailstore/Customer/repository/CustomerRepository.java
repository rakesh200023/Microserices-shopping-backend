package com.retailstore.Customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retailstore.Customer.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findBycustomerId(int customerId);
	
}
