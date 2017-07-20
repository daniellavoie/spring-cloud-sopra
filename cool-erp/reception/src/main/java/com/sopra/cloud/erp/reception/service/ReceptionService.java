package com.sopra.cloud.erp.reception.service;

import com.sopra.cloud.erp.reception.clients.InventoryClient;
import com.sopra.cloud.erp.reception.clients.PurchaseOrderClient;
import com.sopra.cloud.erp.reception.model.Reception;
import com.sopra.cloud.erp.reception.model.ReceptionStatus;
import com.sopra.cloud.erp.reception.repository.ReceptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReceptionService {

    @Autowired
    private PurchaseOrderClient purchaseOrderClient;

    @Autowired
    private InventoryClient inventoryClient;

    private ReceptionRepository receptionRepository;

    public ReceptionService(ReceptionRepository receptionRepository) {
        this.receptionRepository = receptionRepository;
    }

    public Reception getReception(long receptionId) {
        return receptionRepository.findOne(receptionId);
    }

    public List<Reception> getAllReceptions() {
        return receptionRepository.findAll();
    }

    public Reception newReceptionForPurchase(long purchaseOrderId, long productId) {
        Reception reception = new Reception(purchaseOrderId, productId);
        reception.setStatus(ReceptionStatus.OPEN);
        reception.setDate(new Date());
        return receptionRepository.save(reception);
    }

    public Reception confirmReception(long receptionId) {
        Reception reception = this.getReception(receptionId);

        purchaseOrderClient.notifyCommandReceived(reception.getPurchaseOrderId());
        inventoryClient.incrementInventory(reception.getProductId());

        reception.setStatus(ReceptionStatus.RECEIVED);
        return receptionRepository.save(reception);
    }

}
