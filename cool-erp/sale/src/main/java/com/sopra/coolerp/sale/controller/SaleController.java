package com.sopra.coolerp.sale.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sopra.coolerp.sale.model.Sale;
import com.sopra.coolerp.sale.rest.ShippingClient;
import com.sopra.coolerp.sale.service.SaleService;

@RestController
@RequestMapping("/sale")
public class SaleController {
	
	private Logger LOGGER = LoggerFactory.getLogger(SaleController.class);
	
	private SaleService saleService;
	@Autowired
	private ShippingClient shippingClient;
	
	public SaleController(SaleService saleService) {
		this.saleService = saleService;
	}

	@PostMapping
	public Sale newSale(@RequestParam("productId") long productId) {
		Sale sale = this.saleService.save(new Sale(0, productId, new Date(), "PAID"));
		shippingClient.createShipping(productId, sale.getId());
		LOGGER.info("Sold product " + productId);
		return sale;
	}
}
