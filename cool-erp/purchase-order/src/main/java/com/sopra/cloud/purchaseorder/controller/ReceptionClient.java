package com.sopra.cloud.purchaseorder.controller;

//@FeignClient(name = "reception-service")
public interface ReceptionClient {

    // @RequestMapping(path = "/invoice", method = RequestMethod.POST)
    public void createReception(long pPurchaseOrderId, long pProductId);

}
