package com.smartinventory.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
	@NotBlank(message = "Product name cannot be empty")
	@Size(min = 1,max = 50,message = "Name length must be between 2 and 50")
	private String productName;
	@NotBlank(message = "Product description cannot be empty")
	@Size(min = 1,max = 100,message = "Description length must be between 1 and 100")
	private String productDesc;
	@NotNull(message = "Category is Required")
	private Long categoryId;
	@Min(value = 1,message = "Quantity cannot be less than 0")
	private Integer quantity;
	@DecimalMin(value = "0.01",message = "Price must be greater than 0")
	private Double price;
}
