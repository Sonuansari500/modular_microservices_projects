package com.belalsoft.inventoryservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belalsoft.inventoryservice.dto.InventoryResponse;
import com.belalsoft.inventoryservice.respository.InventoryRepository;

@Service
public class InventoryService {
	@Autowired
	private InventoryRepository inventoryRepository;

	public boolean isInStock(String skucode) {
		return inventoryRepository.findBySkuCode(skucode).isPresent();
		
	}
	public List<InventoryResponse> isAnyoneSKUOOS(List<String> skucode) {
		return inventoryRepository.findBySkuCodeIn(skucode).stream().map(inventory->InventoryResponse.builder().skuCode(inventory.getSkuCode())
				.isInStock(inventory.getQuantity()>0).build()).toList();
		
	}
	
}
