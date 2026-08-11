package com.smartinventory.dto;

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
public class InventoryTransactionRequest {
	private String transactionReference;
	private Long orderId;
	private PaymentMethod paymentMethod;
}
