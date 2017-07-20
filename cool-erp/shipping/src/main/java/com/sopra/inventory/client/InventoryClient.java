package com.sopra.inventory.client;

//@FeignClient(name = "inventory-service")
public interface InventoryClient {
	
	// POST /inventory/{productId}?ship
	//@RequestMapping(path = "/inventory/{productId}?ship", method = RequestMethod.POST)
	void decrement(int productId);
	
}