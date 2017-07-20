package com.sopra.shipping.service;

import com.sopra.shipping.model.Shipping;

public interface ShippingService {

	Shipping getShipping(int saleId);

	Shipping confirmShipping(int shippingId);

	Shipping createShipping(int productId, int saleId);

	Iterable<Shipping> listShippings();

}