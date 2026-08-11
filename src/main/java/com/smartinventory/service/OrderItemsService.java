package com.smartinventory.service;

import java.util.List;

import com.smartinventory.dto.OrderItemsRequest;
import com.smartinventory.dto.OrderItemsResponse;

public interface OrderItemsService {
	OrderItemsResponse createOrderItems(OrderItemsRequest orderItemRequest);
	OrderItemsResponse findByOrderItemId(Long orderItemId);
	List<OrderItemsResponse> getAllOrderItems();
	List<OrderItemsResponse> findByOrderOrderId(Long orderId);
	List<OrderItemsResponse> findByProductProductId(Long productId);
	List<OrderItemsResponse> findByProductProductName(String productName);
	OrderItemsResponse updateOrderItems(Long orderItemId,OrderItemsRequest orderItemRequest);
	void deleteOrderItems(Long orderItemId);
}
