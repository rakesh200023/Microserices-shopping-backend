package com.retailstore.CompositeService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.retailstore.CompositeService.entites.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {
//	@Query("select * from CustomerOrder where customerId='")
	List<CustomerOrder> findBycustomerId(int customerId);
}
