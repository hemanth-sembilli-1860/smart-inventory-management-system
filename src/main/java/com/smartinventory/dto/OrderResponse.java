package com.smartinventory.dto;

import java.time.LocalDateTime;

import com.smartinventory.enums.OrderStatus;

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
public class OrderResponse {
	private Long orderId;
	private LocalDateTime orderDate;
	private double totalAmount;
	private OrderStatus status;
	private CustomerOrderResponse customerOrderResponse;
	private UserOrderResponse userOrderResponse;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
