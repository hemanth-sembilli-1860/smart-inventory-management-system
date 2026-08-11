package com.smartinventory.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartinventory.dto.UserRequest;
import com.smartinventory.dto.UserResponse;
import com.smartinventory.enums.Role;
import com.smartinventory.service.UserService;

import jakarta.validation.Valid;

@RequestMapping("/users")
@RestController
public class UserController {
	private UserService userService;
	public UserController(UserService userService) {
		this.userService = userService;
	}
	@PostMapping
	public UserResponse createUser(@Valid @RequestBody UserRequest userRequest) {
		return userService.createUser(userRequest);
	}

	@GetMapping("/{userId}")
	public UserResponse findByUserId(@PathVariable Long userId) {
		return userService.findByUserId(userId);
	}

	@GetMapping
	public List<UserResponse> getAllUsers() {
		return userService.getAllUsers();
	}

	@PutMapping("/{userId}")
	public UserResponse updateUser(@PathVariable Long userId,@Valid @RequestBody UserRequest userRequest) {
		return userService.updateUser(userId, userRequest);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/name/{userName}")
	public UserResponse findByUserName(@PathVariable String userName) {
		return userService.findByUserName(userName);
	}
	
	@GetMapping("/role/{role}")
	public List<UserResponse> findByRole(@PathVariable Role role){
		return userService.findByRole(role);
	}

	
}
