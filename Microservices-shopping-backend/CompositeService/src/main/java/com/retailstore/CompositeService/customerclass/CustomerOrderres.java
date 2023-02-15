package com.retailstore.CompositeService.customerclass;

import java.util.List;

import com.retailstore.CompositeService.entites.CustomerOrder;
import com.retailstore.CompositeService.orderclass.Orders;

public class CustomerOrderres {
	Customer customer;
	List<Orders> ods;
	public CustomerOrderres() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Orders> getOds() {
		return ods;
	}
	public void setOds(List<Orders> ods) {
		this.ods = ods;
	}
	
	
}
