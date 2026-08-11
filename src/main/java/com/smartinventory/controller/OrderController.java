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

import com.smartinventory.dto.OrderRequest;
import com.smartinventory.dto.OrderResponse;
import com.smartinventory.enums.OrderStatus;
import com.smartinventory.service.OrderService;

import jakarta.validation.Valid;

@RequestMapping("/orders")
@RestController
public class OrderController{
	private OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	@PostMapping
	public OrderResponse createOrder(@Valid @RequestBody OrderRequest orderRequest) {
		return orderService.createOrder(orderRequest);
	}
	@GetMapping("/{orderId}")
	public OrderResponse findByOrderId(@PathVariable Long orderId) {
		return orderService.findByOrderId(orderId);
	}
	@GetMapping("/customer/{customerId}")
	public List<OrderResponse> findByCustomerId(@PathVariable Long customerId) {
		return orderService.findByCustomerId(customerId);
	}
	@GetMapping("/user/{userId}")
	public List<OrderResponse> findByUserId(@PathVariable Long userId) {
		return orderService.findByUserId(userId);
	}
	@GetMapping("/customer/name/{customerName}")
	public List<OrderResponse> findByCustomerName(@PathVariable String customerName) {
		return orderService.findByCustomerName(customerName);
	}
	@GetMapping("/user/name/{userName}")
	public List<OrderResponse> findByUserName(@PathVariable String userName) {
		return orderService.findByUserName(userName);
	}
	@GetMapping
	public List<OrderResponse> getAllOrders() {
		return orderService.getAllOrders();
	}
	@GetMapping("/status/{orderStatus}")
	public List<OrderResponse> findByStatus(@PathVariable OrderStatus orderStatus) {
		return orderService.findByStatus(orderStatus);
	}
	@PutMapping("/{orderId}")
	public OrderResponse updateOrder(@PathVariable Long orderId,@Valid @RequestBody OrderRequest orderRequest) {
		return orderService.updateOrder(orderId, orderRequest);
	}
	@DeleteMapping("/{orderId}")
	public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
		orderService.deleteOrder(orderId);
		return ResponseEntity.noContent().build();
	}
}
