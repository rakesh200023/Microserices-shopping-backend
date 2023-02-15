package com.retailstore.Customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailstore.Customer.entities.Customer;
import com.retailstore.Customer.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository crepo;

	public Customer create(Customer c) {
		// TODO Auto-generated method stub
		return crepo.save(c);
	}
	
	public String updatecustomer(int customerId,Customer c) {
		Customer c1 = crepo.findBycustomerId(customerId);
		if(c1==null) {
			return "user doesnt exists";
		}
		c1.setCustomerName(c.getCustomerName());
		c1.setEmail(c.getEmail());
		c1.setBillingAddress(c.getBillingAddress());
		c1.setShippingAddress(c.getShippingAddress());
		System.out.println("updated" + "  " + c1);
		crepo.save(c1);
		return "updated successfully";
	}
	
	public String deletecustomer(int customerId) {
		Customer c1 = crepo.findBycustomerId(customerId);
		if(c1==null) {
			return "user doesnt exists";
		}else {
			crepo.delete(c1);
			return "deleted successfully";
		}
		
	}

	public Customer searchcustomer(int customerId) {
		// TODO Auto-generated method stub
		Customer c1 = crepo.findBycustomerId(customerId);
		if(c1==null) {
			return null;
		}else {
			return c1;
		}
		
	}
}
