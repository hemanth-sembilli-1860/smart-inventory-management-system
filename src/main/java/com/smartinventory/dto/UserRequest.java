package com.smartinventory.dto;

import com.smartinventory.enums.Role;

import jakarta.validation.constraints.Email;
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
public class UserRequest {
	@NotBlank(message = "Username is Required")
	@Size(min = 1,max = 50,message = "Name length must be between 2 and 50")
	private String userName;
	@Email(message = "Email is Required")
	private String emailId;
	@NotBlank(message = "Password is required")
	@Size(min = 8,message = "Password must be at least 8 characters")
	private String password;
	private Role role;
}
