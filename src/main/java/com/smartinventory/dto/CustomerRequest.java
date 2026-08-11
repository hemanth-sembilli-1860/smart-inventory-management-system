package com.smartinventory.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class CustomerRequest {
	@NotBlank(message = "Customer name is Required")
	@Size(min = 1,max = 50,message = "Name length must be between 2 and 50")
	private String customerName;
	@Email(message = "Email is Required")
	private String emailId;
	@NotBlank(message = "Mobile Number is required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must contain exactly 10 digits")
	private String phone;
	@NotBlank(message = "Address is required")
	@Size(min = 10,max = 50,message = "Please Enter Your Address Along with Postal Area Code")
	private String address;
}
