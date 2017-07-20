package com.sopra.cloud.purchaseorder.controller;

import com.sopra.cloud.purchaseorder.model.Product;

public class ProductClientJsonMock implements ProductClient {

    @Override
    public Product getProduct(long pProductId) {
        return new Product();
    }

}
