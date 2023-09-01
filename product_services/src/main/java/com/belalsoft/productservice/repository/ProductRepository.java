package com.belalsoft.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.belalsoft.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

}
