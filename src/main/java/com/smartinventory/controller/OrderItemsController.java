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

import com.smartinventory.dto.OrderItemsRequest;
import com.smartinventory.dto.OrderItemsResponse;
import com.smartinventory.service.OrderItemsService;

import jakarta.validation.Valid;

@RequestMapping("/orderItems")
@RestController
public class OrderItemsController {
	private OrderItemsService orderItemsService;
	public OrderItemsController(OrderItemsService orderItemsService) {
		this.orderItemsService  = orderItemsService;
	}
	@PostMapping
	public OrderItemsResponse createOrderItems(@RequestBody @Valid OrderItemsRequest orderItemsRequest) {
		return orderItemsService.createOrderItems(orderItemsRequest);
	}
	@GetMapping("/{orderItemId}")
	public OrderItemsResponse findByOrderItemId(@PathVariable Long orderItemId) {
		return orderItemsService.findByOrderItemId(orderItemId);
	}
	@GetMapping
	public List<OrderItemsResponse> getAllOrderItems() {
		return orderItemsService.getAllOrderItems();
	}
	@GetMapping("/order/{orderId}")
	public List<OrderItemsResponse> findByOrderOrderId(@PathVariable Long orderId) {
		return orderItemsService.findByOrderOrderId(orderId);
	}
	@GetMapping("/product/{productId}")
	public List<OrderItemsResponse> findByProductProductId(@PathVariable Long productId) {
		return orderItemsService.findByProductProductId(productId);
	}
	@GetMapping("/product/name/{productName}")
	public List<OrderItemsResponse> findByProductProductName(@PathVariable String productName) {
		return orderItemsService.findByProductProductName(productName);
	}
	@PutMapping("/{orderItemId}")
	public OrderItemsResponse updateOrderItems(@PathVariable Long orderItemId,@RequestBody @Valid OrderItemsRequest orderItemsRequest) {
		return orderItemsService.updateOrderItems(orderItemId, orderItemsRequest);
	}
	@DeleteMapping("/{orderItemId}")
	public ResponseEntity<Void> deleteOrderItems(@PathVariable Long orderItemId) {
		orderItemsService.deleteOrderItems(orderItemId);
		return ResponseEntity.noContent().build();
	}
}
