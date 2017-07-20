package com.sopra.shipping.repository;
import org.springframework.data.repository.CrudRepository;

import com.sopra.shipping.model.Shipping;

public interface ShippingRepository extends CrudRepository<Shipping, Integer> {

	public Shipping findById(int shippingId);

	public Shipping findBySaleId(int saleId);


}