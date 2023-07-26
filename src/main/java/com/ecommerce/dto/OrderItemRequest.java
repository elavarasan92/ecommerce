package com.ecommerce.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderItemRequest {
	private String name;
	private BigDecimal price;
}
