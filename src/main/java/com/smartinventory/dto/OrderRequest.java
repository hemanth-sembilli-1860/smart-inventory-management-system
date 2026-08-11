package com.smartinventory.dto;

import com.smartinventory.enums.OrderStatus;

import jakarta.validation.constraints.DecimalMin;
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
public class OrderRequest {
	private OrderStatus status;
	private Long customerId;
	private Long userId;
}
