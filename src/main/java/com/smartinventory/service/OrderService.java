package com.smartinventory.service;

import java.util.List;

import com.smartinventory.dto.OrderRequest;
import com.smartinventory.dto.OrderResponse;
import com.smartinventory.enums.OrderStatus;

public interface OrderService {
	public OrderResponse createOrder(OrderRequest orderRequest);
	public OrderResponse findByOrderId(Long orderId);
	public List<OrderResponse> findByCustomerId(Long customerId);
	public List<OrderResponse> findByUserId(Long userId);
	public List<OrderResponse> findByCustomerName(String customerName);
	public List<OrderResponse> findByUserName(String userName);
	public List<OrderResponse> getAllOrders();
	public List<OrderResponse> findByStatus(OrderStatus orderStatus);
	public OrderResponse updateOrder(Long orderId,OrderRequest orderRequest);
	public void deleteOrder(Long orderId);
}
