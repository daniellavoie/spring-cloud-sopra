package com.sopra.coolerp.inventory.service;

import java.util.List;

import com.sopra.coolerp.inventory.model.Inventory;

public interface InventoryService {
	Inventory decrementInventory(long productId);

	Inventory incrementInventory(long productId);

	Inventory holdInventory(long productId);

	List<Inventory> findAll(List<Long> productIds);
}
