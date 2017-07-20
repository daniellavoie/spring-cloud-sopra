package com.sopra.coolerp.inventory.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sopra.coolerp.inventory.model.Inventory;
import com.sopra.coolerp.inventory.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
	private InventoryService inventoryService;

	public InventoryController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	@PostMapping(path = "/{productId}", params = "decrement")
	public Inventory decrementInventory(@PathVariable long productId) {
		return inventoryService.decrementInventory(productId);
	}

	@PostMapping
	public List<Inventory> findAll(@RequestBody List<Long> productIds) {
		return inventoryService.findAll(productIds);
	}

	@PostMapping(path = "/{productId}", params = "increment")
	public Inventory incrementInventory(@PathVariable long productId) {
		return inventoryService.incrementInventory(productId);
	}

	@PostMapping(path = "/{productId}", params = "hold")
	public Inventory holdInventory(@PathVariable long productId) {
		return inventoryService.holdInventory(productId);
	}
}
