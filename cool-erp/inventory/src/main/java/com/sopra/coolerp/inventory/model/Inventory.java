package com.sopra.coolerp.inventory.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Inventory {
	private long productId;
	private double onHand;
	private double onHold;

	public Inventory() {

	}

	public Inventory(long productId, double onHand, double onHold) {
		this.productId = productId;
		this.onHand = onHand;
		this.onHold = onHold;
	}

	@Id
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
