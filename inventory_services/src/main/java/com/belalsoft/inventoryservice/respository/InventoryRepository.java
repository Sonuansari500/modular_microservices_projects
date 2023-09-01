package com.belalsoft.inventoryservice.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belalsoft.inventoryservice.model.Inventory;
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{

	public Optional<Inventory> findBySkuCode(String skucode);

}
