package com.sopra.cloud.erp.reception.controller;

import com.sopra.cloud.erp.reception.model.Reception;
import com.sopra.cloud.erp.reception.service.ReceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reception")
public class ReceptionController {

    @Autowired
    private ReceptionService service;

    @GetMapping("/{receptionId}")
    @ResponseBody
    public Reception getReception(@PathVariable long receptionId) {
        return service.getReception(receptionId);
    }

    @GetMapping
    @ResponseBody
    public List<Reception> getAllReceptions() {
        return service.getAllReceptions();
    }

    @PostMapping
    @ResponseBody
    public Reception newReception(@RequestParam("purchaseOrderId") long purchaseOrderId,
                                  @RequestParam("productId") long productId) {
        return service.newReceptionForPurchase(purchaseOrderId, productId);
    }
}
