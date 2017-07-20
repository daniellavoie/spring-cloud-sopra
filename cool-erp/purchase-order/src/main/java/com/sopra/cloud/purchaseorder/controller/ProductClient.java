package com.sopra.cloud.purchaseorder.controller;

import com.sopra.cloud.purchaseorder.model.Product;

//@FeignClient(name = "product-service")
public interface ProductClient {

    // @RequestMapping(path = "/invoice", method = RequestMethod.POST)
    public Product getProduct(long pProductId);

}
