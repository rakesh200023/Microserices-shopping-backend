package com.retailstore.Customer.controller;

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

import com.retailstore.Customer.entities.Customer;
import com.retailstore.Customer.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService cservice;

	@PostMapping("/addcustomer")
	public ResponseEntity<?> createcustomer(@RequestBody Customer c) {
			cservice.create(c);
			return new ResponseEntity<Customer>(c,HttpStatus.CREATED);
	}
	
	@GetMapping("/searchcustomer/{customerId}")
	public ResponseEntity<?> searchcustomer (@PathVariable("customerId") int customerId ){
		Customer c=cservice.searchcustomer(customerId);
		if(c!=null)
			return new ResponseEntity<Customer>(c,HttpStatus.OK);
		return new ResponseEntity<String>("customer not found",HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/updatecustomer/{customerId}")
	public ResponseEntity<?> updatecustomer (@PathVariable("customerId") int customerId, @RequestBody Customer c ){
		String s=cservice.updatecustomer(customerId,c);
		return new ResponseEntity<String>(s,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deletecustomer/{customerId}")
	public ResponseEntity<?> deletecustomer (@PathVariable("customerId") int customerId ){
		String s=cservice.deletecustomer(customerId);
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
	
	@GetMapping("/getc")
	public String message() {
		return "custommm";
	}
	

}
