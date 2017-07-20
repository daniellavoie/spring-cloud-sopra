package com.sopra.cloud.purchaseorder.controller;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.sopra.cloud.purchaseorder.model.Product;

public class ReceptionClientRestImpl implements ReceptionClient {

    private RestTemplate restTemplate;

    public ReceptionClientRestImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void createReception(long pPurchaseOrderId, long pProductId) {
        restTemplate.exchange(
                "/reception-service/reception?purchaseOrderId=" + pPurchaseOrderId + "&productId=" + pProductId,
                HttpMethod.POST, null, Product.class).getBody();
    }
}
