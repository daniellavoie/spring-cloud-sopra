package com.sopra.shipping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sopra.shipping.model.Product;
import com.sopra.shipping.model.Shipping;
import com.sopra.shipping.repository.ShippingRepository;

@Service
public class ShippingServiceImpl implements ShippingService {

	@Autowired
	private ShippingRepository shippingRepository;
	
	public void test() {
		Shipping shipping = new Shipping();
		shippingRepository.save(shipping);
		
	}
	
	/* (non-Javadoc)
	 * @see com.sopra.shipping.service.ShippingService2#getShipping(int)
	 */
	@Override
	public Shipping getShipping(int saleId) {
	    Shipping shipping = shippingRepository.findBySaleId( saleId); // TODO
	    return shipping;
	}

	/* (non-Javadoc)
	 * @see com.sopra.shipping.service.ShippingService2#confirmShipping(int)
	 */
	@Override
	public Shipping confirmShipping(int shippingId) {
		
		Shipping shipping = shippingRepository.findById(shippingId);
		shipping.setStatus("CONFIRMED");
		
		return shippingRepository.save(shipping);
		
		//Décrémentation de l'inventaire d'un produit
		//POST /inventory/{productId}?ship
		
	}

	/* (non-Javadoc)
	 * @see com.sopra.shipping.service.ShippingService2#createShipping(int, int)
	 */
	@Override
	public Shipping createShipping(int productId, int saleId) {
		
        // get product
		new Product();
				
		Shipping shipping = new Shipping();
		shipping.setProductId(productId);
		shipping.setSaleId(saleId);
		shipping.setStatus("OPEN");
		
		return shippingRepository.save(shipping);
		
	}

	/* (non-Javadoc)
	 * @see com.sopra.shipping.service.ShippingService2#listShippings()
	 */
	@Override
	public Iterable<Shipping> listShippings() {
		return shippingRepository.findAll();
	}

	
	
}
