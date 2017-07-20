package com.sopra.coolerp.sale.model;

public class Inventory {
	
	private long productId;
    private double onHand;
    private double onHold;
    public Inventory() {
	}
	public Inventory(long productId, double onHand, double onHold) {
		super();
		this.productId = productId;
		this.onHand = onHand;
		this.onHold = onHold;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public double getOnHand() {
		return onHand;
	}
	public void setOnHand(double onHand) {
		this.onHand = onHand;
	}
	public double getOnHold() {
		return onHold;
	}
	public void setOnHold(double onHold) {
		this.onHold = onHold;
	}
}
