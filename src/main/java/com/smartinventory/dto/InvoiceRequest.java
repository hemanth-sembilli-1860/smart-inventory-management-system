package com.smartinventory.dto;

import java.time.LocalDateTime;

import com.smartinventory.entity.Order;

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
public class InvoiceRequest {
	private String invoiceNumber;
	private Long orderId;
}
