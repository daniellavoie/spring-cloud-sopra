package com.sopra.coolerp.sale;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import com.sopra.coolerp.sale.model.Sale;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SaleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SaleApplicationTest {
	
	@Autowired
	TestRestTemplate restTemplate;
	
	@Test
	public void contextLoads() {
		
	}
	
	@Test
	public void testSale() {
		Sale sale = restTemplate.exchange("/sale?productId=1", HttpMethod.POST, null, Sale.class).getBody();
		Assert.assertNotNull("The sale should not be empty",sale);
	}

}
