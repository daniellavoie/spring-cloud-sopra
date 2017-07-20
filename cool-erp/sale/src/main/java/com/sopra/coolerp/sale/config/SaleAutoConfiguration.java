package com.sopra.coolerp.sale.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import com.sopra.coolerp.sale.rest.ShippingClient;
import com.sopra.coolerp.sale.rest.ShippingJsonMock;
import com.sopra.coolerp.sale.rest.ShippingRestImpl;

@Configuration
@Import({ SaleDiscoveryConfig.class })
public class SaleAutoConfiguration {
	
	
	@Bean
	@LoadBalanced
	@ConditionalOnMissingBean(value=RestTemplate.class)
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	@ConditionalOnProperty(name="services.mock", havingValue="false", matchIfMissing=true)
	public ShippingClient shippingRestImpl() {
		return new ShippingRestImpl(restTemplate());
	}
	
	@Bean
	@ConditionalOnProperty(name="services.mock", havingValue="true", matchIfMissing=false)
	public ShippingClient shippingMockImpl() {
		return new ShippingJsonMock();
	}

}
