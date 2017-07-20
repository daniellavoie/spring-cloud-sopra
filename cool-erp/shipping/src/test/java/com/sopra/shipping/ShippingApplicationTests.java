
package com.sopra.shipping;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.sopra.shipping.model.Shipping;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShippingApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class ShippingApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testRepository() {

		Shipping shipModel = new Shipping();
		shipModel.setProductId(1);
		shipModel.setSaleId(1);

		ResponseEntity<Shipping> postResponse = restTemplate.postForEntity(
				"/shipping?productId=" + shipModel.getProductId() + "&saleId=" + shipModel.getSaleId(), shipModel,
				Shipping.class);
		Assert.assertNotNull(postResponse.getBody().getId());
		assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(postResponse.getBody().getId()).isGreaterThan(0);
		assertThat(postResponse.getBody().getStatus()).isEqualTo("OPEN");

		ResponseEntity<Shipping> postResponseConfirm = restTemplate
				.postForEntity("/shipping/" + postResponse.getBody().getId() + "/?ship", shipModel, Shipping.class);
		Assert.assertNotNull(postResponseConfirm.getBody().getId());
		assertThat(postResponseConfirm.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(postResponseConfirm.getBody().getId()).isGreaterThan(0);
		assertThat(postResponseConfirm.getBody().getStatus()).isEqualTo("CONFIRMED");

		Shipping getResponse = restTemplate.getForObject("/shipping?saleId=" + shipModel.getSaleId(), Shipping.class);
		Assert.assertNotNull(getResponse.getId());
		assertThat(getResponse.getId()).isGreaterThan(0);
		assertThat(getResponse.getSaleId()).isEqualTo(shipModel.getSaleId());

	}

}
