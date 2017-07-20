package com.sopra.cloud.erp.reception;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ReceptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceptionApplication.class, args);
	}
}
