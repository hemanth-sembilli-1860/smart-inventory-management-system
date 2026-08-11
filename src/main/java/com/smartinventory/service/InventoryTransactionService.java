package com.smartinventory.service;

import java.util.List;

import com.smartinventory.dto.InventoryTransactionRequest;
import com.smartinventory.dto.InventoryTransactionResponse;
import com.smartinventory.enums.PaymentMethod;
import com.smartinventory.enums.PaymentStatus;

public interface InventoryTransactionService {
	public InventoryTransactionResponse createTransaction(InventoryTransactionRequest request);
	public InventoryTransactionResponse findByTransactionId(Long transactionId);
	public List<InventoryTransactionResponse> getAllTransaction();
	public List<InventoryTransactionResponse> findByOrderId(Long orderId);
	public InventoryTransactionResponse findByTransactionReference(String transactionReference);
	public List<InventoryTransactionResponse> findByPaymentMethod(PaymentMethod paymentMethod);
	public List<InventoryTransactionResponse> findByPaymentStatus(PaymentStatus paymentStatus);
	public InventoryTransactionResponse updateTransaction(Long transactionId,InventoryTransactionRequest request);
	public void deleteTransaction(Long transactionId);
	
}
