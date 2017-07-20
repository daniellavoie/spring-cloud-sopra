package com.sopra.cloud.erp.reception;

import com.sopra.cloud.erp.reception.model.Reception;
import com.sopra.cloud.erp.reception.model.ReceptionStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReceptionApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReceptionApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;


	@Test
	public void createReception() {
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("purchaseOrderId", "12345");
		queryParams.put("productId", "6789");
//		Reception reception = restTemplate.postForObject( "/reception",
//				new Reception(), Reception.class, queryParams);
		Reception reception = restTemplate.exchange("/reception?purchaseOrderId={purchaseOrderId}&productId={productId}",
				HttpMethod.POST,
				null, Reception.class, "12345", "6789").getBody();
		Assert.assertNotNull(reception);
		Assert.assertEquals(12345, reception.getPurchaseOrderId());
		Assert.assertEquals(6789, reception.getProductId());
		Assert.assertEquals(ReceptionStatus.OPEN.toString(), reception.getStatus());
		Assert.assertTrue(reception.getId() > 0);
	}

}
