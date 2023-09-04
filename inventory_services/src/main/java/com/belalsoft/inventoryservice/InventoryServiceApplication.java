package com.belalsoft.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.belalsoft.inventoryservice.model.Inventory;
import com.belalsoft.inventoryservice.respository.InventoryRepository;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		
		return args->{
			Inventory inventory1 = new Inventory();
			inventory1.setQuantity(100);
			inventory1.setSkuCode("nokia");
			
			Inventory inventory2 = new Inventory();
			inventory2.setQuantity(100);
			inventory2.setSkuCode("iphone");
			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
		};
	}

}
