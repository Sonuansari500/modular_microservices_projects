package com.belalsoft.inventoryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belalsoft.inventoryservice.respository.InventoryRepository;

@Service
public class InventoryService {
	@Autowired
	private InventoryRepository inventoryRepository;

	public boolean isInStock(String skucode) {
		return inventoryRepository.findBySkuCode(skucode).isPresent();
		
	}

}
