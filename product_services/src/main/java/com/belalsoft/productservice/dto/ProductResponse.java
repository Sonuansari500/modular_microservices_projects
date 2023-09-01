package com.belalsoft.productservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
	private String id;
	private String name;
	private String description;
	private BigDecimal price;
}
