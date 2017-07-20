package com.sopra.cloud.purchaseorder.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sopra.cloud.purchaseorder.model.PurchaseOrder;
import com.sopra.cloud.purchaseorder.service.PurchaseOrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseOrderApplicationTests {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Test
    public void createPurchaseOrder() {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductId(1000);
        PurchaseOrder createdPurchaseOrder = purchaseOrderService.createPurchaseOrder(purchaseOrder);

        Assert.assertEquals(createdPurchaseOrder.getProductId(), 1000);
    }

}
