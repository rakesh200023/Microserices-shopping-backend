package com.retailstore.Inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retailstore.Inventory.entities.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

	Inventory findByInventoryId(int inventoryId);
	Inventory findByproductId(int productId);

}
