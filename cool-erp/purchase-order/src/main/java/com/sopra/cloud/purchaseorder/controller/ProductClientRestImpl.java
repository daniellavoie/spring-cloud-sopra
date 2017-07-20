package com.sopra.cloud.purchaseorder.controller;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.sopra.cloud.purchaseorder.model.Product;

public class ProductClientRestImpl implements ProductClient {

    private RestTemplate restTemplate;

    public ProductClientRestImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProduct(long pProductId) {
        Product product = restTemplate
                .exchange("/product-service/product?productId=" + pProductId, HttpMethod.GET, null, Product.class)
                .getBody();

        return product;
    }

}
