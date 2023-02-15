package com.retailstore.Product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retailstore.Product.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByproductId(int customerId);

}
