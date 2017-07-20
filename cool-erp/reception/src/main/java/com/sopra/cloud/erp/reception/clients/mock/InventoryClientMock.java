package com.sopra.cloud.erp.reception.clients.mock;

import com.sopra.cloud.erp.reception.clients.InventoryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InventoryClientMock implements InventoryClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryClientMock.class);

    @Override
    public void incrementInventory(long productId) {
            LOGGER.info("Mock call to http://inventory-service/inventory/{}?increment", productId);
    }
}
