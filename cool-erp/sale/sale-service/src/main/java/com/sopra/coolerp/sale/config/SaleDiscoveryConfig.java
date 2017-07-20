package com.sopra.coolerp.sale.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient
@ConditionalOnProperty(name="services.mock", havingValue="false", matchIfMissing=true)
public class SaleDiscoveryConfig {

}
