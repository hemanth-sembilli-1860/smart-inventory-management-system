package com.smartinventory.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartinventory.dto.InventoryTransactionRequest;
import com.smartinventory.dto.InventoryTransactionResponse;
import com.smartinventory.dto.OrderResponse;
import com.smartinventory.entity.InventoryTransaction;
import com.smartinventory.entity.Order;
import com.smartinventory.enums.PaymentMethod;
import com.smartinventory.enums.PaymentStatus;
import com.smartinventory.exceptions.InventoryTransactionNotFoundException;
import com.smartinventory.exceptions.OrderNotFoundException;
import com.smartinventory.repository.InventoryTransactionRepository;
import com.smartinventory.repository.OrderRepository;
import com.smartinventory.service.InventoryTransactionService;
import com.smartinventory.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InventoryTransactionServiceImpl implements InventoryTransactionService {
	private InventoryTransactionRepository inventoryTransactionRepository;
	private OrderRepository orderRepository;
	private OrderService orderService;
	public InventoryTransactionServiceImpl(InventoryTransactionRepository inventoryTransactionRepository,OrderRepository orderRepository,OrderService orderService) {
		this.inventoryTransactionRepository = inventoryTransactionRepository;
		this.orderRepository = orderRepository;
		this.orderService = orderService;
	}
	@Override
	public InventoryTransactionResponse createTransaction(InventoryTransactionRequest request) {
		InventoryTransaction inventoryTransaction = new InventoryTransaction();
		inventoryTransaction.setTransactionReference(request.getTransactionReference());
		inventoryTransaction.setPaymentMethod(request.getPaymentMethod());
		Order order = orderRepository.findById(request.getOrderId())
				.orElseThrow(()->new OrderNotFoundException("Order Not Found"));
		inventoryTransaction.setOrder(order);
		inventoryTransaction.setTotalamount(order.getTotalAmount());
	    inventoryTransaction.setPaymentStatus(PaymentStatus.PENDING);
	    InventoryTransaction savedEntity = inventoryTransactionRepository.save(inventoryTransaction);
		return convertToTransactionResponse(savedEntity);
	}
	private InventoryTransactionResponse convertToTransactionResponse(InventoryTransaction inventoryTransaction) {
		InventoryTransactionResponse transactionResponse  = new InventoryTransactionResponse();
		transactionResponse.setTransactionId(inventoryTransaction.getTransactionId());
		transactionResponse.setTransactionReference(inventoryTransaction.getTransactionReference());
		transactionResponse.setTotalAmount(inventoryTransaction.getTotalamount());
		transactionResponse.setPaymentMethod(inventoryTransaction.getPaymentMethod());
		transactionResponse.setPaymentStatus(inventoryTransaction.getPaymentStatus());
		OrderResponse orderResponse =
		        orderService.findByOrderId(inventoryTransaction.getOrder().getOrderId());
		transactionResponse.setOrderResponse(orderResponse);
		transactionResponse.setTransactionTime(inventoryTransaction.getTransactionTime());
		return transactionResponse;	
	}
	@Override
	public InventoryTransactionResponse findByTransactionId(Long transactionId) {
		InventoryTransaction invTransaction = inventoryTransactionRepository.findById(transactionId)
				.orElseThrow(() -> new InventoryTransactionNotFoundException("Inventory Transaction Not Found"));
		return convertToTransactionResponse(invTransaction);
	}
	@Override
	public List<InventoryTransactionResponse> getAllTransaction() {
		return inventoryTransactionRepository.findAll()
				.stream()
				.map(this::convertToTransactionResponse)
				.toList();
	}
	@Override
	public List<InventoryTransactionResponse> findByOrderId(Long orderId) {
		 return inventoryTransactionRepository.findByOrderOrderId(orderId)
		            .stream()
		            .map(this::convertToTransactionResponse)
		            .toList();
	}
	@Override
	public InventoryTransactionResponse findByTransactionReference(String transactionReference) {
		InventoryTransaction inventoryTransaction = inventoryTransactionRepository.findByTransactionReference(transactionReference)
				.orElseThrow(()->new InventoryTransactionNotFoundException("Inventory Transaction Not Found"));
		return convertToTransactionResponse(inventoryTransaction);
	}
	@Override
	public List<InventoryTransactionResponse> findByPaymentMethod(PaymentMethod paymentMethod) {
		return inventoryTransactionRepository.findByPaymentMethod(paymentMethod)
				.stream()
				.map(this::convertToTransactionResponse)
				.toList();
	}
	@Override
	public List<InventoryTransactionResponse> findByPaymentStatus(PaymentStatus paymentStatus) {
		return inventoryTransactionRepository.findByPaymentStatus(paymentStatus)
				.stream()
				.map(this::convertToTransactionResponse)
				.toList();
	}
	@Override
	public InventoryTransactionResponse updateTransaction(Long transactionId, InventoryTransactionRequest request) {
		InventoryTransaction inventoryTransaction = inventoryTransactionRepository.findById(transactionId)
				.orElseThrow(()->new InventoryTransactionNotFoundException("Inventory Transaction Not Found"));
		inventoryTransaction.setTransactionReference(request.getTransactionReference());
		inventoryTransaction.setPaymentMethod(request.getPaymentMethod());
		Order order = orderRepository.findById(request.getOrderId())
				.orElseThrow(()->new OrderNotFoundException("Order Not Found"));
		inventoryTransaction.setOrder(order);
		inventoryTransaction.setTotalamount(order.getTotalAmount());
	    inventoryTransaction.setPaymentStatus(PaymentStatus.PENDING);
	    InventoryTransaction savedEntity = inventoryTransactionRepository.save(inventoryTransaction);
		return convertToTransactionResponse(savedEntity);
	}
	@Override
	public void deleteTransaction(Long transactionId) {
		InventoryTransaction inventoryTransaction = inventoryTransactionRepository.findById(transactionId)
				.orElseThrow(()-> new InventoryTransactionNotFoundException("Inventory Transaction Not Found"));
		inventoryTransactionRepository.delete(inventoryTransaction);
		log.info("Inventory Transaction with ID {} is deleted",inventoryTransaction.getTransactionId());
	}
}
