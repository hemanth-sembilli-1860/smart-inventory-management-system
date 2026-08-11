package com.smartinventory.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartinventory.dto.OrderItemOrderResponse;
import com.smartinventory.dto.OrderItemProductResponse;
import com.smartinventory.dto.OrderItemsRequest;
import com.smartinventory.dto.OrderItemsResponse;
import com.smartinventory.entity.Order;
import com.smartinventory.entity.OrderItem;
import com.smartinventory.entity.Product;
import com.smartinventory.exceptions.InsufficientStockException;
import com.smartinventory.exceptions.OrderItemsNotFoundException;
import com.smartinventory.exceptions.OrderNotFoundException;
import com.smartinventory.exceptions.ProductNotFoundException;
import com.smartinventory.repository.OrderItemRepository;
import com.smartinventory.repository.OrderRepository;
import com.smartinventory.repository.ProductRepository;
import com.smartinventory.service.OrderItemsService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderItemsServiceImpl implements OrderItemsService{
	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	private final OrderItemRepository orderItemRepository;
	public OrderItemsServiceImpl(OrderItemRepository orderItemRepository, ProductRepository productRepository, OrderRepository orderRepository) {
		this.orderItemRepository =  orderItemRepository;
		this.productRepository = productRepository;
		this.orderRepository = orderRepository;
	}
	private OrderItemsResponse convertToOrderItemResponse(OrderItem orderItem) {
		OrderItemsResponse orderItemsResponse = new OrderItemsResponse();
		OrderItemOrderResponse orderItemOrderResponse = new OrderItemOrderResponse();
		OrderItemProductResponse orderItemProductResponse  = new OrderItemProductResponse();
		orderItemsResponse.setOrderItemId(orderItem.getOrderItemId());
		orderItemsResponse.setQuantity(orderItem.getQuantity());
		orderItemsResponse.setSubTotal(orderItem.getSubTotal());
		orderItemsResponse.setUnitPrice(orderItem.getUnitPrice());
		orderItemOrderResponse.setOrderDate(orderItem.getOrder().getOrderDate());
		orderItemOrderResponse.setOrderId(orderItem.getOrder().getOrderId());
		orderItemOrderResponse.setStatus(orderItem.getOrder().getStatus());
		orderItemsResponse.setOrderItemOrderResponse(orderItemOrderResponse);
		orderItemProductResponse.setProductId(orderItem.getProduct().getProductId());
		orderItemProductResponse.setProductName(orderItem.getProduct().getProductName());
		orderItemProductResponse.setPrice(orderItem.getProduct().getPrice());
		orderItemsResponse.setOrderItemProductResponse(orderItemProductResponse);
		return orderItemsResponse;
	}
	
	private void updateOrderTotal(Order order) {
		Double total = orderItemRepository.calculateOrderTotal(order.getOrderId());
		order.setTotalAmount(total);
		orderRepository.save(order);
	}
	@Transactional
	@Override
	public OrderItemsResponse createOrderItems(OrderItemsRequest orderItemsRequest) {
		OrderItem orderItem  = new OrderItem();
		orderItem.setQuantity(orderItemsRequest.getQuantity());
		Product product = productRepository.findById(orderItemsRequest.getProductId())
				.orElseThrow(()-> new ProductNotFoundException("Product Not Found"));
		Order order = orderRepository.findById(orderItemsRequest.getOrderId())
				.orElseThrow(()-> new OrderNotFoundException("Order Not Found"));
		if (product.getQuantity()<orderItem.getQuantity()) {
			throw new InsufficientStockException("Insufficient stock for product");
		}
		orderItem.setProduct(product);
		orderItem.setOrder(order);
		Double unitPrice = product.getPrice();
		orderItem.setUnitPrice(unitPrice);
		orderItem.setSubTotal(orderItem.getQuantity()*orderItem.getUnitPrice());
		product.setQuantity(product.getQuantity()-orderItem.getQuantity());
		productRepository.save(product);
		OrderItem savedEntity = orderItemRepository.save(orderItem);
		updateOrderTotal(order);
		return convertToOrderItemResponse(savedEntity);
	}
	@Override
	public OrderItemsResponse findByOrderItemId(Long orderItemId) {
		OrderItem orderItem = orderItemRepository.findById(orderItemId)
				.orElseThrow(() -> new OrderItemsNotFoundException("Order Item Not Found"));
		return convertToOrderItemResponse(orderItem);
	}
	@Override
	public List<OrderItemsResponse> getAllOrderItems() {
		return orderItemRepository.findAll()
				.stream()
				.map(this::convertToOrderItemResponse)
				.toList();
	}
	@Override
	public List<OrderItemsResponse> findByOrderOrderId(Long orderId) {
		return orderItemRepository.findByOrderOrderId(orderId)
				.stream()
				.map(this::convertToOrderItemResponse)
				.toList();
	}
	@Override
	public List<OrderItemsResponse> findByProductProductId(Long productId) {
		return orderItemRepository.findByProductProductId(productId)
				.stream()
				.map(this::convertToOrderItemResponse)
				.toList();
	}
	@Override
	public List<OrderItemsResponse> findByProductProductName(String productName) {
		return orderItemRepository.findByProductProductName(productName)
				.stream()
				.map(this::convertToOrderItemResponse)
				.toList();
	}
	@Transactional
	@Override
	public OrderItemsResponse updateOrderItems(Long orderItemId, OrderItemsRequest orderItemsRequest) {
		OrderItem orderItem = orderItemRepository.findById(orderItemId)
				.orElseThrow(()-> new OrderItemsNotFoundException("Order Item Not Found"));
		int oldQuantity = orderItem.getQuantity();
		int newQuantity = orderItemsRequest.getQuantity();
		Product product = productRepository.findById(orderItemsRequest.getProductId())
				.orElseThrow(()-> new ProductNotFoundException("Product Not Found"));
		Order order = orderRepository.findById(orderItemsRequest.getOrderId())
				.orElseThrow(()-> new OrderNotFoundException("Order Not Found"));
		 int difference = newQuantity - oldQuantity;

		 if (difference > 0) {
		     if (product.getQuantity() < difference) {
		         throw new InsufficientStockException("Insufficient stock for product");
		     }
		 }
		 product.setQuantity(product.getQuantity() - difference);
		 productRepository.save(product);
		orderItem.setQuantity(newQuantity);
		orderItem.setProduct(product);
		orderItem.setOrder(order);
		Double unitPrice = product.getPrice();
		orderItem.setUnitPrice(unitPrice);
		orderItem.setSubTotal(orderItem.getQuantity()*orderItem.getUnitPrice());
		OrderItem savedEntity = orderItemRepository.save(orderItem);
		updateOrderTotal(order);
		return convertToOrderItemResponse(savedEntity);
	}
	@Transactional
	@Override
	public void deleteOrderItems(Long orderItemId) {
		OrderItem orderItem = orderItemRepository.findById(orderItemId)
				.orElseThrow(()-> new OrderItemsNotFoundException("Order Item Not Found"));
		Order order = orderItem.getOrder();
		 Product product = orderItem.getProduct();
		 product.setQuantity(product.getQuantity() + orderItem.getQuantity());
			productRepository.save(product);
		orderItemRepository.delete(orderItem);
		updateOrderTotal(order);
		log.info("Order Item with orderItem Id {} is Removed",orderItemId);
	}
}
