package com.sopra.shipping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sopra.shipping.model.Shipping;
import com.sopra.shipping.service.ShippingService;

@Controller
@RequestMapping("/shipping")
public class ShippingController {
	
	@Autowired
	private ShippingService shippingService;

// POST /shipping?productId=productId&saleId=saleId
    @RequestMapping(method=RequestMethod.POST)
    public @ResponseBody Shipping create(@RequestParam(value="productId", required=true) int productId, @RequestParam(value="productId", required=true) int saleId) {
    	return shippingService.createShipping(productId, saleId);
    }
    
    // POST /shipping/shippingId?ship
    @RequestMapping(value="/{shippingId}", method = RequestMethod.POST)
    public @ResponseBody Shipping confirm(@PathVariable(value="shippingId", required=true) int shippingId) {
        return shippingService.confirmShipping(shippingId);
    }
    
    // GET /shipping?saleId=saleId
    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody Shipping get(@RequestParam(value="saleId", required=true) int saleId) {

        return shippingService.getShipping(saleId);
    }
    
    @RequestMapping(value="/bad", method=RequestMethod.GET)
    public @ResponseBody Iterable<Shipping> list() {

        return shippingService.listShippings();
    }
    
    @RequestMapping(value="/test", method=RequestMethod.GET)
    public void test() {
    	shippingService.test();
    }
}



