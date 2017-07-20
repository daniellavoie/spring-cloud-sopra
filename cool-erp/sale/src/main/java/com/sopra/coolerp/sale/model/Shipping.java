package com.sopra.coolerp.sale.model;

import java.util.Date;

public class Shipping {
	
	 private int id;
	 private int purchaseOrderId;
	 private long productId;
	 private Date date;
	 private String status;
	 public Shipping() {
	}
	public Shipping(int id, int purchaseOrderId, long productId, Date date, String status) {
		super();
		this.id = id;
		this.purchaseOrderId = purchaseOrderId;
		this.productId = productId;
		this.date = date;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPurchaseOrderId() {
		return purchaseOrderId;
	}
	public void setPurchaseOrderId(int purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
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
