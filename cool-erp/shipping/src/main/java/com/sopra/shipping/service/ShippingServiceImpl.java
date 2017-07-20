package com.sopra.shipping.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sopra.inventory.client.InventoryClient;
import com.sopra.shipping.model.Product;
import com.sopra.shipping.model.Shipping;
import com.sopra.shipping.repository.ShippingRepository;

@Service
public class ShippingServiceImpl implements ShippingService {

	@Autowired
	private ShippingRepository shippingRepository;

	@Autowired
	private InventoryClient inventoryClient;

	public void test() {
		Shipping shipping = new Shipping();
		shippingRepository.save(shipping);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sopra.shipping.service.ShippingService2#getShipping(int)
	 */
	@Override
	public Shipping getShipping(int saleId) {
		Shipping shipping = shippingRepository.findBySaleId(saleId); // TODO
		return shipping;
	}

private static final Logger log = LoggerFactory.getLogger(ShippingServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sopra.shipping.service.ShippingService2#confirmShipping(int)
	 */
	@Override
	public Shipping confirmShipping(int shippingId) {

		Shipping shipping = shippingRepository.findById(shippingId);

		if ("CONFIRMED".equals(shipping.getStatus())) {
            log.warn("shipping already confirmed, ignored");
		} else {

			shipping.setStatus("CONFIRMED");

			shipping = shippingRepository.save(shipping);

			inventoryClient.decrement(shipping.getProductId());

		}
		
		return shipping;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sopra.shipping.service.ShippingService2#createShipping(int, int)
	 */
	@Override
	public Shipping createShipping(int productId, int saleId) {

		// get product TODO
		new Product();

		Shipping shipping = new Shipping();
		shipping.setProductId(productId);
		shipping.setSaleId(saleId);
		shipping.setStatus("OPEN");

		return shippingRepository.save(shipping);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sopra.shipping.service.ShippingService2#listShippings()
	 */
	@Override
	public Iterable<Shipping> listShippings() {
		return shippingRepository.findAll();
	}

}
