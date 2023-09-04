package com.belalsoft.inventoryservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.belalsoft.inventoryservice.dto.InventoryResponse;
import com.belalsoft.inventoryservice.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
	private InventoryService inventoryService;
	@GetMapping("SKU/{sku-code}")
	@ResponseStatus(HttpStatus.OK)
	public boolean isInStock(@PathVariable("sku-code") String skucode) {
		return inventoryService.isInStock(skucode);
	}
	
	// https://locahost:8090/api/inventory/ipohone-13,iphone-red
	// https://locahost:8090/api/inventory/? sku-code = ipohone-13 & sku-code = iphone-red
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<InventoryResponse> isAnyoneSKUOOS(@RequestParam List<String> skuCode) {
		return inventoryService.isAnyoneSKUOOS(skuCode);
	}
}
