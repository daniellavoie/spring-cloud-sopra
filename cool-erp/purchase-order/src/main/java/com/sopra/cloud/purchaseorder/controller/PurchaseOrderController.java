package com.sopra.cloud.purchaseorder.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sopra.cloud.purchaseorder.model.Product;
import com.sopra.cloud.purchaseorder.model.PurchaseOrder;
import com.sopra.cloud.purchaseorder.service.PurchaseOrderService;

@RestController
@RequestMapping
public class PurchaseOrderController {

    private PurchaseOrderService purchaseOrderService;

    private ProductClient productClient;

    private ReceptionClient receptionClient;

    public PurchaseOrderController(PurchaseOrderService pPurchaseOrderReceived, ProductClient pProductClient,
            ReceptionClient pReceptionClient) {
        purchaseOrderService = pPurchaseOrderReceived;
        productClient = pProductClient;
        receptionClient = pReceptionClient;
    }

    @PostMapping(value = "/reception")
    public PurchaseOrder purchaseOrderReceived(@RequestParam("purchaseOrderId") String pPurchaseOrderId) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(Long.parseLong(pPurchaseOrderId));
        purchaseOrder.setStatus(PurchaseOrder.CLOSED);

        // TODO check order exist.

        return purchaseOrderService.updatePurchaseOrder(purchaseOrder);
    }

    @GetMapping(value = "/purchase-order/{purchaseOrderId}")
    public PurchaseOrder getPurchaseOrder(@PathVariable("purchaseOrderId") String pPurchaseOrderId) {
        return purchaseOrderService.getPurchaseOrder(Long.parseLong(pPurchaseOrderId));
    }

    @PostMapping(value = "/purchase-order")
    public PurchaseOrder createPurchaseOrder(@RequestParam("new") String pNew,
            @RequestParam("productId") String pProductId) {
        /* 1. Check product exists. */
        Product product = null;
        try {
            product = productClient.getProduct(Long.parseLong(pProductId));
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Exception during product retrieve");
            return null;
        }

        if (product == null) {
            System.out.println("Product not found");
            return null;
        }

        /* 2. Create purchase order. */
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductId(Long.parseLong(pProductId));
        PurchaseOrder createdPurchaseOrder = purchaseOrderService.createPurchaseOrder(purchaseOrder);

        /* 3. Inform reception purchase order created. */
        try {
            receptionClient.createReception(createdPurchaseOrder.getId(), Long.parseLong(pProductId));
        } catch (RuntimeException e) {
            System.out.println("Exception during reception");
        }

        return createdPurchaseOrder;
    }

}
