package com.sopra.shipping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sopra.shipping.model.Product;
import com.sopra.shipping.model.Shipping;
import com.sopra.shipping.repository.ShippingRepository;

@Service
public class ShippingService {

	@Autowired
	private ShippingRepository shippingRepository;
	
	public void test() {
		Shipping shipping = new Shipping();
		shippingRepository.save(shipping);
		
	}
	
	public Shipping getShipping(int saleId) {
	    Shipping shipping = shippingRepository.findBySaleId( saleId); // TODO
	    return shipping;
	}

	public Shipping confirmShipping(int shippingId) {
		
		Shipping shipping = shippingRepository.findById(shippingId);
		shipping.setStatus("CONFIRMED");
		
		return shippingRepository.save(shipping);
		
		//Décrémentation de l'inventaire d'un produit
		//POST /inventory/{productId}?ship
		
	}

	public Shipping createShipping(int productId, int saleId) {
		
        // get product
		new Product();
				
		Shipping shipping = new Shipping();
		shipping.setProductId(productId);
		shipping.setSaleId(saleId);
		shipping.setStatus("OPEN");
		
		return shippingRepository.save(shipping);
		
	}

	public Iterable<Shipping> listShippings() {
		return shippingRepository.findAll();
	}

	
	
}
