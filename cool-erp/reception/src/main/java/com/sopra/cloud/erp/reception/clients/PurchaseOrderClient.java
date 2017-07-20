package com.sopra.cloud.erp.reception.clients;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "purchase-order-service")
public interface PurchaseOrderClient
{
    @RequestMapping(params = "purchaseOrderId", method = RequestMethod.POST, path = "/reception")
    void notifyCommandReceived(@RequestParam("purchaseOrderId") long purchaseOrderId);
}
