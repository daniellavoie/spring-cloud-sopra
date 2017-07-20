package com.sopra.inventory.client.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import com.sopra.inventory.client.InventoryClient;
import com.sopra.inventory.client.InventoryClientJsonMock;
import com.sopra.inventory.client.InventoryClientRestImpl;

@Configuration
public class InventoryClientAutoConfiguration {
	@Bean
	@ConditionalOnMissingBean(value = RestTemplate.class)
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	@ConditionalOnProperty(name = "service.mock", havingValue = "false", matchIfMissing = true)
	public InventoryClient invoiceClientRestImpl() {
		return new InventoryClientRestImpl(restTemplate());
	}

	@Bean
	@ConditionalOnProperty(name = "service.mock", havingValue = "true", matchIfMissing = false)
	public InventoryClient invoiceClientMockImpl() {
		return new InventoryClientJsonMock();
	}
}
