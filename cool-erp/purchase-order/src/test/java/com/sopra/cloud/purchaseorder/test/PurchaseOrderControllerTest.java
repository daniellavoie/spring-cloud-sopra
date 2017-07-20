package com.sopra.cloud.purchaseorder.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import com.sopra.cloud.purchaseorder.PurchaseOrderApplication;
import com.sopra.cloud.purchaseorder.model.PurchaseOrder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PurchaseOrderApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class PurchaseOrderControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testCreatePurchaseOrder() {
        PurchaseOrder purchaseOrder = testRestTemplate
                .exchange("/purchase-order?new&productId=1000", HttpMethod.POST, null, PurchaseOrder.class).getBody();

        Assert.assertNotNull(purchaseOrder);
        Assert.assertEquals(purchaseOrder.getStatus(), PurchaseOrder.CONFIRMED);

        purchaseOrder = testRestTemplate.exchange("/reception?purchaseOrderId=" + purchaseOrder.getId(),
                HttpMethod.POST, null, PurchaseOrder.class).getBody();

        Assert.assertNotNull(purchaseOrder);
        Assert.assertEquals(purchaseOrder.getStatus(), PurchaseOrder.CLOSED);

        purchaseOrder = testRestTemplate
                .exchange("/purchase-order/" + purchaseOrder.getId(), HttpMethod.GET, null, PurchaseOrder.class)
                .getBody();

        Assert.assertNotNull(purchaseOrder);
        Assert.assertEquals(purchaseOrder.getStatus(), PurchaseOrder.CLOSED);
    }

}
