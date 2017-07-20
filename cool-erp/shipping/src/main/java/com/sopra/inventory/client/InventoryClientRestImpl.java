package com.sopra.inventory.client;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.sopra.inventory.client.model.Inventory;

public class InventoryClientRestImpl implements InventoryClient {
	
	private RestTemplate restTemplate;

	public InventoryClientRestImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	// POST /inventory/{productId}?ship
	public void decrement(int productId) {
		restTemplate.exchange("http://inventory-service/"+productId+"?ship" , HttpMethod.POST, null, Inventory.class);
	}

}
