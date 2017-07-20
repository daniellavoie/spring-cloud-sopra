package com.sopra.cloud.purchaseorder.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.sopra.cloud.purchaseorder.controller.ProductClient;
import com.sopra.cloud.purchaseorder.controller.ProductClientJsonMock;
import com.sopra.cloud.purchaseorder.controller.ProductClientRestImpl;
import com.sopra.cloud.purchaseorder.controller.ReceptionClient;
import com.sopra.cloud.purchaseorder.controller.ReceptionClientJsonMock;
import com.sopra.cloud.purchaseorder.controller.ReceptionClientRestImpl;

@Configuration
public class ClientAutoConfig {
    @Bean
    // @LoadBalanced
    @ConditionalOnMissingBean(value = RestTemplate.class)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @ConditionalOnProperty(name = "service.mock", havingValue = "false", matchIfMissing = true)
    public ProductClient productClientRestImpl() {
        return new ProductClientRestImpl(restTemplate());
    }

    @Bean
    @ConditionalOnProperty(name = "service.mock", havingValue = "true", matchIfMissing = false)
    public ProductClient productClientMockImpl() {
        return new ProductClientJsonMock();
    }

    @Bean
    @ConditionalOnProperty(name = "service.mock", havingValue = "false", matchIfMissing = true)
    public ReceptionClient receptionClientRestImpl() {
        return new ReceptionClientRestImpl(restTemplate());
    }

    @Bean
    @ConditionalOnProperty(name = "service.mock", havingValue = "true", matchIfMissing = false)
    public ReceptionClient receptionClientMockImpl() {
        return new ReceptionClientJsonMock();
    }
}
