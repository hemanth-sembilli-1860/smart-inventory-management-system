package com.smartinventory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsResponse {
	private Long orderItemId;
	private int quantity;
	private double unitPrice;
	private double subTotal;
	private OrderItemOrderResponse orderItemOrderResponse;
	private OrderItemProductResponse orderItemProductResponse;
}
