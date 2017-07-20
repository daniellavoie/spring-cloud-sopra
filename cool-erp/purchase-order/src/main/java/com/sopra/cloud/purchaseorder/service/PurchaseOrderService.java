package com.sopra.cloud.purchaseorder.service;

import com.sopra.cloud.purchaseorder.model.PurchaseOrder;

public interface PurchaseOrderService {

    public PurchaseOrder createPurchaseOrder(PurchaseOrder pPurchaseOrder);

    public PurchaseOrder updatePurchaseOrder(PurchaseOrder pPurchaseOrder);

    public PurchaseOrder getPurchaseOrder(long pId);

}
