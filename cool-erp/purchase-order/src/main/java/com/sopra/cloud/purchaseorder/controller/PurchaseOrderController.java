package com.sopra.cloud.purchaseorder.controller;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sopra.cloud.purchaseorder.model.Product;
import com.sopra.cloud.purchaseorder.model.PurchaseOrder;
import com.sopra.cloud.purchaseorder.service.PurchaseOrderService;

@RestController
@RequestMapping("/purchase-order")
public class PurchaseOrderController {

    private PurchaseOrderService purchaseOrderService;

    private RestTemplate restTemplate;

    public PurchaseOrderController(PurchaseOrderService pPurchaseOrderReceived, RestTemplate pRestTemplate) {
        purchaseOrderService = pPurchaseOrderReceived;
        restTemplate = pRestTemplate;
    }

    @PatchMapping(value = "/{purchaseOrderId}")
    public PurchaseOrder purchaseOrderReceived(@PathVariable("purchaseOrderId") long pPurchaseOrderId) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(pPurchaseOrderId);
        purchaseOrder.setStatus("CLOSED");

        // TODO check order exist.

        return purchaseOrderService.updatePurchaseOrder(purchaseOrder);
    }

    @PostMapping(value = "?new&productId={productId}")
    public PurchaseOrder createPurchaseOrder(@RequestParam("productId") long pProductId) {
        /* 1. Check product exists. */
        Product product = null;
        try {
            product = restTemplate.exchange("/product-service/" + pProductId, HttpMethod.GET, null, Product.class)
                    .getBody();
        } catch (RuntimeException e) {
            System.out.println("Exception during product retrieve");
            return null;
        }

        if (product == null) {
            System.out.println("Product not found");
            return null;
        }

        /* 2. Create purchase order. */
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductId(pProductId);
        PurchaseOrder createdPurchaseOrder = purchaseOrderService.createPurchaseOrder(purchaseOrder);

        /* 3. Inform reception purchase order created. */
        // TODO

        return createdPurchaseOrder;
    }

}
