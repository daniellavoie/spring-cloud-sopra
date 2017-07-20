package com.sopra.coolerp.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sopra.coolerp.inventory.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
