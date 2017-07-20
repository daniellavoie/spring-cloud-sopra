package com.sopra.cloud.purchaseorder.service;

import org.springframework.stereotype.Service;

import com.sopra.cloud.purchaseorder.model.PurchaseOrder;
import com.sopra.cloud.purchaseorder.repository.PurchaseOrderRepository;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository pPurchaseOrderRepository) {
        purchaseOrderRepository = pPurchaseOrderRepository;
    }

    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrder pPurchaseOrder) {
        return purchaseOrderRepository.save(pPurchaseOrder);
    }

    @Override
    public PurchaseOrder updatePurchaseOrder(PurchaseOrder pPurchaseOrder) {
        return purchaseOrderRepository.save(pPurchaseOrder);
    }

    @Override
    public PurchaseOrder getPurchaseOrder(long pId) {
        return purchaseOrderRepository.findOne(pId);
    }

}
