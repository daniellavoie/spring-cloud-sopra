package com.sopra.coolerp.sale.service;

import org.springframework.stereotype.Service;

import com.sopra.coolerp.sale.model.Sale;
import com.sopra.coolerp.sale.model.SaleRepository;

@Service
public class SaleServiceImpl implements SaleService {

	private SaleRepository saleRepository;

	public SaleServiceImpl(SaleRepository saleRepository) {
		this.saleRepository = saleRepository;
	}
	
	@Override
	public Sale save(Sale sale) {
		return saleRepository.save(sale);
	}

}
