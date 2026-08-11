package com.smartinventory.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartinventory.dto.UserRequest;
import com.smartinventory.dto.UserResponse;
import com.smartinventory.entity.User;
import com.smartinventory.enums.Role;
import com.smartinventory.exceptions.UserNotFoundException;
import com.smartinventory.repository.UserRepository;
import com.smartinventory.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	@Override
	public UserResponse createUser(UserRequest userRequest) {
		User user = new User();
		user.setUserName(userRequest.getUserName());
		user.setEmailId(userRequest.getEmailId());
		user.setPassword(userRequest.getPassword());
		user.setRole(userRequest.getRole());
		User savedEntity = userRepository.save(user);
		return convertToUserResponse(savedEntity);
	}
	private UserResponse convertToUserResponse(User user) {
		UserResponse userResponse = new UserResponse();
		userResponse.setUserId(user.getUserId());
		userResponse.setUserName(user.getUserName());
		userResponse.setEmail(user.getEmailId());
		userResponse.setRole(user.getRole());
		return userResponse;
	}

	@Override
	public UserResponse findByUserId(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User Not Found"));
		return convertToUserResponse(user);
	}

	@Override
	public List<UserResponse> getAllUsers() {
		return userRepository.findAll()
				.stream()
				.map(this::convertToUserResponse)
				.toList();
	}

	@Override
	public UserResponse findByUserName(String userName) {
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new UserNotFoundException("User Not Found"));
		return convertToUserResponse(user);
	}

	@Override
	public UserResponse updateUser(Long userId, UserRequest userRequest) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User Not Found"));
		user.setUserName(userRequest.getUserName());
		user.setEmailId(userRequest.getEmailId());
		user.setPassword(userRequest.getPassword());
		user.setRole(userRequest.getRole());
		User savedEntity = userRepository.save(user);
		return convertToUserResponse(savedEntity);
	}

	@Override
	public List<UserResponse> findByRole(Role role) {
		return userRepository.findByRole(role)
				.stream()
				.map(this::convertToUserResponse)
				.toList();
	}

	@Override
	public void deleteUser(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User Not Found"));
		userRepository.delete(user);
		log.info("User '{}' is deleted", user.getUserName());
	}

}
