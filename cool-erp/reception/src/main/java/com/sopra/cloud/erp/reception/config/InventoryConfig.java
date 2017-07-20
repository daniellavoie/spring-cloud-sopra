package com.sopra.cloud.erp.reception.config;

import com.sopra.cloud.erp.reception.clients.mock.InventoryClientMock;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import com.sopra.cloud.erp.reception.clients.PurchaseOrderClient;
import com.sopra.cloud.erp.reception.clients.mock.PurchaseOrderClientMock;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class InventoryConfig {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    @ConditionalOnProperty(name = "inventory.service.mock", havingValue = "true", matchIfMissing = false)
    public InventoryClientMock getInventoryClientMock() {
        return new InventoryClientMock();
    }

    @Bean
    @ConditionalOnProperty(name = "purchaseorder.service.mock", havingValue = "true", matchIfMissing = false)
    public PurchaseOrderClient purchaseOrderClientMock()
    {
        return new PurchaseOrderClientMock();
    }

}
