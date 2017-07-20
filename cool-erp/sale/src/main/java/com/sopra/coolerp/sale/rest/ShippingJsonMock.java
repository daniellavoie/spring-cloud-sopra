package com.sopra.coolerp.sale.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sopra.coolerp.sale.model.Shipping;

public class ShippingJsonMock implements ShippingClient{

	List<Shipping> shippings = new ArrayList<Shipping>();
	@Override
	public void createShipping(long productId, int saleId) {
		shippings.add(new Shipping(saleId, 123, productId, new Date(), "NEW"));
	}

}
