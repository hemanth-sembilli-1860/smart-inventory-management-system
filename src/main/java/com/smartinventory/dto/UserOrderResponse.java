package com.smartinventory.dto;

import com.smartinventory.enums.Role;

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
public class UserOrderResponse {
	private Long userId;
	private String userName;
	private Role role;
}
