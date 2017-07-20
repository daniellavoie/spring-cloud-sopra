package com.sopra.coolerp.sale.rest;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.sopra.coolerp.sale.model.Shipping;

public class ShippingRestImpl implements ShippingClient{

	private RestTemplate restTemplate;

	public ShippingRestImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public void createShipping(long productId, int saleId) {
		restTemplate.exchange("http://shipping-service/shipping?productId="+productId+"&saleId="+saleId, HttpMethod.POST, null, Shipping.class);
	}
	
	
}
