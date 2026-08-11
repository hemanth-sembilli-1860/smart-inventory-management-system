package com.smartinventory.dto;

import java.time.LocalDateTime;

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
public class ProductResponse {
	private Long productId;
	private String productName;
	private String productDesc;
	private CategoryProductResponse categoryProductResponse;
	private Integer quantity;
	private Double price;
	private String stockStatus;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
