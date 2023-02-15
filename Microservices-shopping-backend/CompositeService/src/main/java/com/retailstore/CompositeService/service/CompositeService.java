package com.retailstore.CompositeService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailstore.CompositeService.Repository.CartCustomer;
import com.retailstore.CompositeService.Repository.CustomerOrderRepository;
import com.retailstore.CompositeService.entites.CustomerCart;
import com.retailstore.CompositeService.entites.CustomerOrder;

@Service
public class CompositeService {
	@Autowired
	CartCustomer crepo;
	
	@Autowired
	CustomerOrderRepository corepo;

	public CustomerCart create(CustomerCart cc) {
		// TODO Auto-generated method stub
		return crepo.save(cc);
	}

	public CustomerOrder createorder(CustomerOrder co) {
		// TODO Auto-generated method stub
		return corepo.save(co);
	}

	public List<CustomerOrder> findorders(int customerId) {
		// TODO Auto-generated method stub
		return corepo.findBycustomerId(customerId);
		
	}
	
}
