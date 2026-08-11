package com.smartinventory.service;

import java.util.List;

import com.smartinventory.dto.UserRequest;
import com.smartinventory.dto.UserResponse;
import com.smartinventory.enums.Role;

public interface UserService {
	UserResponse createUser(UserRequest userRequest);
	UserResponse findByUserId(Long userId);
	List<UserResponse> getAllUsers();
	UserResponse findByUserName(String userName);
	UserResponse updateUser(Long userId,UserRequest userRequest);
	List<UserResponse> findByRole(Role role);
	void deleteUser(Long userId);
}
