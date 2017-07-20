package com.sopra.inventory.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InventoryClientJsonMock implements InventoryClient {

	private static final Logger log = LoggerFactory.getLogger(InventoryClientJsonMock.class);

	private int product = 1000;

	@Override
	public void decrement(int productId) {

		log.info("decrement " + productId + " - remaining " + (--product));

	}

}
