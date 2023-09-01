package com.belalsoft.orderservice.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belalsoft.orderservice.model.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
