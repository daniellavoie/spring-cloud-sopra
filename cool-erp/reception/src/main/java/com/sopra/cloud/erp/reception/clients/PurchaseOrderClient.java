package com.sopra.cloud.erp.reception.clients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "reception")
public interface PurchaseOrderClient
{
    @RequestMapping(params = "purchaseOrderId", method = RequestMethod.POST)
    void notifyCommandReceived();
}
