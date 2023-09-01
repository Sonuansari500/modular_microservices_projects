package com.belalsoft.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belalsoft.orderservice.dto.OrderLineItemDto;
import com.belalsoft.orderservice.dto.OrderRequest;
import com.belalsoft.orderservice.model.Order;
import com.belalsoft.orderservice.model.OrderLineItems;
import com.belalsoft.orderservice.respository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepositoryl;
	public void placeOrder(OrderRequest request)
	{
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItems> list = request.getOrderLineItemDtoList().stream().map(orderLineItem->mapToDto(orderLineItem)).toList();
	
		order.setOrderLineItemList(list);
		orderRepositoryl.save(order);
		
	}

	private OrderLineItems mapToDto(OrderLineItemDto dto) {
		OrderLineItems orderLineItem =new OrderLineItems();
		orderLineItem.setPrice(dto.getPrice());
		orderLineItem.setQuantity(dto.getQuantity());
		orderLineItem.setSkuCode(dto.getSkuCode());
		return orderLineItem;
		
	}
}
