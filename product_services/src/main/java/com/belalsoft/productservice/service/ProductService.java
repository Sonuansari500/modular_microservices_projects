package com.belalsoft.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belalsoft.productservice.dto.ProductRequest;
import com.belalsoft.productservice.dto.ProductResponse;
import com.belalsoft.productservice.model.Product;
import com.belalsoft.productservice.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public void createProduct(ProductRequest request) {
		Product product = Product.builder()
		.name(request.getName())
		.price(request.getPrice())
		.description(request.getDescription()).build();
		productRepository.save(product);
		log.info("Product is {} saved ." , product.getId());
	}

	public List<ProductResponse> getProducts() {
		List<Product> findAll = productRepository.findAll();
		return findAll.stream().map(product->mapToProductResponse(product)).toList();
		
	}

	private ProductResponse mapToProductResponse(Product product) {
		return ProductResponse.builder().id(product.getId())
				.name(product.getName())
				.price(product.getPrice())
				.description(product.getDescription()).build();
	}

}
