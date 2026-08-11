package com.smartinventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class CategoryRequest {
	@NotBlank(message = "Name cannot be empty")
	private String categoryName;
	@NotBlank(message = "Description cannot be empty")
	@Size(min = 2,max = 100,message = "Description length must be between 2 and 100")
	private String description;
}
