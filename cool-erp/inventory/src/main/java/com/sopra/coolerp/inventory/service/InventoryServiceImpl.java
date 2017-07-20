package com.sopra.coolerp.inventory.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sopra.coolerp.inventory.model.Inventory;
import com.sopra.coolerp.inventory.repository.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {
	private InventoryRepository inventoryRepository;

	public InventoryServiceImpl(InventoryRepository inventoryRepository) {
		this.inventoryRepository = inventoryRepository;
	}

	@Override
	public Inventory decrementInventory(long productId) {
		Inventory inventory = inventoryRepository.findOne(productId);

		inventory.setOnHold(inventory.getOnHold() - 1);

		return inventoryRepository.save(inventory);
	}

	@Override
	public List<Inventory> findAll(List<Long> productIds) {
		return inventoryRepository.findAll(productIds);
	}

	@Override
	public Inventory incrementInventory(long productId) {
		Inventory inventory = inventoryRepository.findOne(productId);

		inventory.setOnHand(inventory.getOnHand() + 1);

		return inventoryRepository.save(inventory);
	}

	@Override
	public Inventory holdInventory(long productId) {
		Inventory inventory = inventoryRepository.findOne(productId);

		inventory.setOnHold(inventory.getOnHold() + 1);
		inventory.setOnHand(inventory.getOnHand() - 1);

		return inventoryRepository.save(inventory);
	}
}
