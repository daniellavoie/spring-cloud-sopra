package com.sopra.cloud.purchaseorder.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PurchaseOrder {

    public static final String CONFIRMED = "CONFIRMED";

    public static final String CLOSED = "CLOSED";

    private long id;
    private long productId;
    private Date date;
    private String status;

    public PurchaseOrder() {
        date = new Date();
        status = CONFIRMED;
    }

    public PurchaseOrder(long id, long productId, Date date, String status) {
        this.id = id;
        this.productId = productId;
        this.date = date;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
