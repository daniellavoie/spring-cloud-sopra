package com.sopra.shipping;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.sopra.shipping.model.Shipping;

import org.junit.Assert;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShippingApplication.class, webEnvironment=WebEnvironment.RANDOM_PORT)
public class ShippingApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testRepository() {
		
		Shipping shipModel = new Shipping();
		shipModel.setProductId(1);
		shipModel.setSaleId(1); 
		
		ResponseEntity<Shipping> postResponse = restTemplate.postForEntity("/shipping", shipModel,  Shipping.class);
		Assert.assertNotNull(postResponse.getBody().getId()); 
		
		Shipping getResponse = restTemplate.getForObject("/shipping"+shipModel.getSaleId(),  Shipping.class);
		Assert.assertNotNull(getResponse.getId()); 
		
	}



}
