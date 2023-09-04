package com.belalsoft.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.belalsoft.orderservice.dto.InventoryResponse;
import com.belalsoft.orderservice.dto.OrderLineItemDto;
import com.belalsoft.orderservice.dto.OrderRequest;
import com.belalsoft.orderservice.model.Order;
import com.belalsoft.orderservice.model.OrderLineItems;
import com.belalsoft.orderservice.respository.OrderRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepositoryl;
	
	@Autowired
	private WebClient.Builder webClient;
	
	public void placeOrder(OrderRequest request)
	{
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItems> list = request.getOrderLineItemDtoList().stream().map(orderLineItem->mapToDto(orderLineItem)).toList();
	
		order.setOrderLineItemList(list);
		List<String> skuCodes = order.getOrderLineItemList().stream().map(orderLineiItem->orderLineiItem.getSkuCode()).toList();
		// call inventory service and place order if it is in stock
		InventoryResponse[] inventoryResponse = webClient.build().get().uri("http://inventory-service/api/inventory",uriBuilder->uriBuilder.queryParam("skuCode", skuCodes).build()).retrieve()
		.bodyToMono(InventoryResponse[].class).block();
		Arrays.stream(inventoryResponse).forEach(item->System.out.print(item));
		if (inventoryResponse.length < 1) {
			throw new IllegalArgumentException("Product is not in stock , Please try again later !");
		} else {
			boolean result = Arrays.stream(inventoryResponse)
					.allMatch(InventoryResponse -> InventoryResponse.getIsInStock());
			if (result) {
				orderRepositoryl.save(order);
			} else {
				throw new IllegalArgumentException("Product is not in stock , Please try again later !");
			}
		}
		
	}

	private OrderLineItems mapToDto(OrderLineItemDto dto) {
		OrderLineItems orderLineItem =new OrderLineItems();
		orderLineItem.setPrice(dto.getPrice());
		orderLineItem.setQuantity(dto.getQuantity());
		orderLineItem.setSkuCode(dto.getSkuCode());
		return orderLineItem;
		
	}
}
