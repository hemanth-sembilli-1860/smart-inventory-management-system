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
public class OrderItemOrderResponse {
	private Long OrderId;
	private LocalDateTime orderDate;
	private OrderStatus status;
}
