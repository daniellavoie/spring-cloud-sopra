package com.sopra.coolerp.inventory.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import com.sopra.coolerp.inventory.InventoryServiceApplication;
import com.sopra.coolerp.inventory.model.Inventory;
import com.sopra.coolerp.inventory.repository.InventoryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InventoryServiceApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class InventoryControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private InventoryRepository inventoryRepository;

	@Test
	public void testDecrementInventory() {
		Inventory inventory = inventoryRepository.save(new Inventory(1000l, 1.0d, 1.0d));

		double onHand = inventory.getOnHand();
		double onHold = inventory.getOnHand();

		inventory = restTemplate.exchange("/inventory/" + inventory.getProductId() + "?decrement", HttpMethod.POST,
				null, Inventory.class).getBody();

		Assert.assertEquals(onHand, inventory.getOnHand(), 0.001d);
		Assert.assertEquals(onHold - 1, inventory.getOnHold(), 0.001d);

	}

	@Test
	public void testFindAll() {
		Inventory inventory1 = new Inventory(1000l, 1.0d, 1.0d);
		Inventory inventory2 = new Inventory(1001l, 2.0d, 0.0d);
		Inventory inventory3 = new Inventory(1003l, 3.0d, 0.0d);

		inventoryRepository.save(Arrays.asList(inventory1, inventory2, inventory3));

		List<Inventory> inventories = restTemplate.exchange("/inventory/", HttpMethod.POST,
				new HttpEntity<List<Long>>(
						Arrays.asList(inventory1.getProductId(), inventory2.getProductId(), inventory3.getProductId())),
				new ParameterizedTypeReference<List<Inventory>>() {
				}).getBody();

		Map<Long, Inventory> inventoriesByProductId = inventories.stream()
				.collect(Collectors.toMap(Inventory::getProductId, inventory -> inventory));

		Assert.assertEquals(inventory1.getOnHand(), inventoriesByProductId.get(inventory1.getProductId()).getOnHand(),
				0.001d);
		Assert.assertEquals(inventory2.getOnHand(), inventoriesByProductId.get(inventory2.getProductId()).getOnHand(),
				0.001d);
		Assert.assertEquals(inventory3.getOnHand(), inventoriesByProductId.get(inventory3.getProductId()).getOnHand(),
				0.001d);
	}

	@Test
	public void testIncrementInventory() {
		long PRODUCT_ID = 10000;

		// Checks for the existing inventory;
		Inventory inventory = inventoryRepository.findOne(PRODUCT_ID);

		if (inventory == null)
			inventory = inventoryRepository.save(new Inventory(PRODUCT_ID, 10, 2));

		double onHand = inventory.getOnHand();

		inventory = restTemplate
				.exchange("/inventory/" + PRODUCT_ID + "?increment", HttpMethod.POST, null, Inventory.class).getBody();

		Assert.assertEquals(onHand + 1, inventory.getOnHand(), 0.001d);
		Assert.assertEquals(onHand + 1, inventoryRepository.findOne(PRODUCT_ID).getOnHand(), 0.001d);
	}

	@Test
	public void testHoldInventory() {
		long PRODUCT_ID = 10000;

		// Checks for the existing inventory;
		Inventory inventory = inventoryRepository.save(new Inventory(PRODUCT_ID, 10, 2));

		double onHold = inventory.getOnHold();
		double onHand = inventory.getOnHand();

		inventory = restTemplate.exchange("/inventory/" + PRODUCT_ID + "?hold", HttpMethod.POST, null, Inventory.class)
				.getBody();

		Assert.assertEquals(onHold + 1, inventory.getOnHold(), 0.001d);
		Assert.assertEquals(onHand - 1, inventory.getOnHand(), 0.001d);

		inventory = inventoryRepository.findOne(PRODUCT_ID);

		Assert.assertEquals(onHold + 1, inventory.getOnHold(), 0.001d);
		Assert.assertEquals(onHand - 1, inventory.getOnHand(), 0.001d);
	}
}
