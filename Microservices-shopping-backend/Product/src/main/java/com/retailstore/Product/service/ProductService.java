package com.retailstore.Product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.retailstore.Product.entities.Product;
import com.retailstore.Product.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository prepo;

	public Product create(Product p) {
		// TODO Auto-generated method stub
		return prepo.save(p);
	}

	public Product searchproduct(int customerId) {
		// TODO Auto-generated method stub
		Product c1 = prepo.findByproductId(customerId);
		if(c1==null) {
			return null;
		}else {
			return c1;
		}
		
	}

	public String updateproduct(int productId, Product p) {
		// TODO Auto-generated method stub
		Product p1=prepo.findByproductId(productId);
		if(p1==null) {
			return "product doesnt exists";

		}
		p1.setProductName(p.getProductName());
		p1.setDescription(p.getDescription());
		p1.setPrice(p.getPrice());
		prepo.save(p1);
		
		return "product updated successfully";
	}

	public String deleteproduct(int productId) {
		// TODO Auto-generated method stub
		Product p1 = prepo.findByproductId(productId);
		if(p1==null) {
			return "product doesnt exists";
		}else {
			prepo.delete(p1);
			return "product deleted successfully";
		}
	}
}
