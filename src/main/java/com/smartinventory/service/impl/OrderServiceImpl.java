package com.smartinventory.service.impl;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smartinventory.dto.CustomerOrderResponse;
import com.smartinventory.dto.OrderRequest;
import com.smartinventory.dto.OrderResponse;
import com.smartinventory.dto.UserOrderResponse;
import com.smartinventory.entity.Customer;
import com.smartinventory.entity.Order;
import com.smartinventory.entity.User;
import com.smartinventory.enums.OrderStatus;
import com.smartinventory.exceptions.CustomerNotFoundException;
import com.smartinventory.exceptions.OrderNotFoundException;
import com.smartinventory.exceptions.UserNotFoundException;
import com.smartinventory.repository.CustomerRepository;
import com.smartinventory.repository.OrderRepository;
import com.smartinventory.repository.UserRepository;
import com.smartinventory.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService{
	private OrderRepository orderRepository;
	private CustomerRepository customerRepository;
	private UserRepository userRepository;
	public OrderServiceImpl(OrderRepository orderRepository,CustomerRepository customerRepository,UserRepository userRepository) {
		this.orderRepository = orderRepository;
		this.customerRepository = customerRepository;
		this.userRepository = userRepository;
	}
	public OrderResponse convertToOrderResponse(Order order) {
		CustomerOrderResponse customerOrderResponse = new CustomerOrderResponse();
		UserOrderResponse userOrderResponse = new UserOrderResponse();
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setOrderId(order.getOrderId());
		orderResponse.setStatus(order.getStatus());
		orderResponse.setTotalAmount(order.getTotalAmount());
		customerOrderResponse.setCustomerId(order.getCustomer().getCustomerId());
		customerOrderResponse.setCustomerName(order.getCustomer().getCustomerName());
		customerOrderResponse.setAddress(order.getCustomer().getAddress());
		customerOrderResponse.setEmailId(order.getCustomer().getEmailId());
		customerOrderResponse.setPhone(order.getCustomer().getPhone());
		orderResponse.setCustomerOrderResponse(customerOrderResponse);
		userOrderResponse.setUserId(order.getUser().getUserId());
		userOrderResponse.setUserName(order.getUser().getUserName());
		userOrderResponse.setRole(order.getUser().getRole());
		orderResponse.setUserOrderResponse(userOrderResponse);
		orderResponse.setCreatedAt(order.getCreatedAt());
		orderResponse.setUpdatedAt(order.getUpdatedAt());
		orderResponse.setOrderDate(order.getOrderDate());
		return orderResponse;
	}
	@Override
	public OrderResponse createOrder(OrderRequest orderRequest) {
		Customer customer = customerRepository.findById(orderRequest.getCustomerId())
				.orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
		User user = userRepository.findById(orderRequest.getUserId())
				.orElseThrow(() -> new UserNotFoundException("User Not Found"));
		Order order = new Order();
		order.setOrderDate(LocalDateTime.now());
		order.setStatus(orderRequest.getStatus());
		order.setCustomer(customer);
		order.setUser(user);
		Order savedEntity = orderRepository.save(order);
		return convertToOrderResponse(savedEntity);
	}
	@Override
	public OrderResponse findByOrderId(Long orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new OrderNotFoundException("Order not found exception"));
		return convertToOrderResponse(order);
	}
	@Override
	public List<OrderResponse> findByCustomerId(Long customerId) {
		return orderRepository.findByCustomerCustomerId(customerId)
				.stream()
				.map(this::convertToOrderResponse)
				.toList();
	}
	@Override
	public List<OrderResponse> findByUserId(Long userId) {
		return orderRepository.findByUserUserId(userId)
				.stream()
				.map(this::convertToOrderResponse)
				.toList();
	}
	@Override
	public List<OrderResponse> findByCustomerName(String customerName) {
		return orderRepository.findByCustomerCustomerName(customerName)
				.stream()
				.map(this::convertToOrderResponse)
				.toList();
	}
	@Override
	public List<OrderResponse> findByUserName(String userName) {
		return orderRepository.findByUserUserName(userName)
				.stream()
				.map(this::convertToOrderResponse)
				.toList();
	}
	@Override
	public List<OrderResponse> getAllOrders() {
		return orderRepository.findAll()
				.stream()
				.map(this::convertToOrderResponse)
				.toList();
	}
	@Override
	public List<OrderResponse> findByStatus(OrderStatus orderStatus) {
		return orderRepository.findOrderByStatus(orderStatus)
				.stream()
				.map(this::convertToOrderResponse)
				.toList();
	}
	@Override
	public OrderResponse updateOrder(Long orderId, OrderRequest orderRequest) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new OrderNotFoundException("Order Not Found"));
		Customer customer = customerRepository.findById(orderRequest.getCustomerId())
				.orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
		User user = userRepository.findById(orderRequest.getUserId())
				.orElseThrow(() -> new UserNotFoundException("User Not Found"));
		order.setStatus(orderRequest.getStatus());
		order.setCustomer(customer);
		order.setUser(user);
		Order savedEntity = orderRepository.save(order);
		return convertToOrderResponse(savedEntity);
	}
	@Override
	public void deleteOrder(Long orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new OrderNotFoundException("Order Not Found"));
		orderRepository.delete(order);
		log.info("Order with ID '{}' is deleted", order.getOrderId());
	}
}
