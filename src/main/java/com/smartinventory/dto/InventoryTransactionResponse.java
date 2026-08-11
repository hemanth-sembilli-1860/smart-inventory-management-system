package com.smartinventory.dto;

import java.time.LocalDateTime;

import com.smartinventory.entity.Order;
import com.smartinventory.enums.PaymentMethod;
import com.smartinventory.enums.PaymentStatus;

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
public class InventoryTransactionResponse {
	private Long transactionId;
	private String transactionReference;
	private OrderResponse orderResponse;
	private PaymentMethod paymentMethod;
	private Double totalAmount;
	private PaymentStatus paymentStatus;
	private LocalDateTime transactionTime;
}
