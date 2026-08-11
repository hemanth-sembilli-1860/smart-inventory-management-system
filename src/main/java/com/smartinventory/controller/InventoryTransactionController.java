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

import com.smartinventory.dto.InventoryTransactionRequest;
import com.smartinventory.dto.InventoryTransactionResponse;
import com.smartinventory.enums.PaymentMethod;
import com.smartinventory.enums.PaymentStatus;
import com.smartinventory.service.InventoryTransactionService;

import jakarta.validation.Valid;


@RequestMapping("/transactions")
@RestController
public class InventoryTransactionController {
	private InventoryTransactionService inventoryTransactionService;
	public InventoryTransactionController(InventoryTransactionService inventoryTransactionService) {
		this.inventoryTransactionService=inventoryTransactionService;
	}
	@PostMapping
	public InventoryTransactionResponse createTransaction(@RequestBody @Valid InventoryTransactionRequest request) {
		return inventoryTransactionService.createTransaction(request);
	}
	@GetMapping("/{transactionId}")
	public InventoryTransactionResponse findByTransactionId(@PathVariable Long transactionId) {
		return inventoryTransactionService.findByTransactionId(transactionId);
	}
	@GetMapping
	public List<InventoryTransactionResponse> getAllTransaction() {
		return inventoryTransactionService.getAllTransaction();
	}
	@GetMapping("/order/{orderId}")
	public List<InventoryTransactionResponse> findByOrderId(@PathVariable Long orderId) {
		return inventoryTransactionService.findByOrderId(orderId);
	}
	@GetMapping("/reference/{transactionReference}")
	public InventoryTransactionResponse findByTransactionReference(@PathVariable String transactionReference) {
		return inventoryTransactionService.findByTransactionReference(transactionReference);
	}
	@GetMapping("/method/{paymentMethod}")
	public List<InventoryTransactionResponse> findByPaymentMethod(@PathVariable PaymentMethod paymentMethod) {
		return inventoryTransactionService.findByPaymentMethod(paymentMethod);
	}
	@GetMapping("/status/{paymentStatus}")
	public List<InventoryTransactionResponse> findByPaymentStatus(@PathVariable PaymentStatus paymentStatus) {
		return inventoryTransactionService.findByPaymentStatus(paymentStatus);
	}
	@PutMapping("/{transactionId}")
	public InventoryTransactionResponse updateTransaction(@PathVariable Long transactionId,@RequestBody @Valid InventoryTransactionRequest request) {
		return inventoryTransactionService.updateTransaction(transactionId,request);
	}
	@DeleteMapping("/{transactionId}")
	public ResponseEntity<Void> deleteTransaction(@PathVariable Long transactionId) {
		inventoryTransactionService.deleteTransaction(transactionId);
		return ResponseEntity.noContent().build();
	}
}




