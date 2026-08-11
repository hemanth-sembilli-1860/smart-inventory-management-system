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

import com.smartinventory.dto.InvoiceRequest;
import com.smartinventory.dto.InvoiceResponse;
import com.smartinventory.entity.Invoice;
import com.smartinventory.exceptions.InvoiceNotFoundException;
import com.smartinventory.service.InvoiceService;

import jakarta.validation.Valid;

@RequestMapping("/invoices")
@RestController
public class InvoiceController {
	public InvoiceService invoiceService;
	public InvoiceController(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}
	@PostMapping
	public InvoiceResponse createInvoice(@RequestBody @Valid InvoiceRequest invoiceRequest) {
		return invoiceService.createInvoice(invoiceRequest);
	}
	@GetMapping("/{invoiceId}")
	public InvoiceResponse findByInvoiceId(@PathVariable Long invoiceId) {
		return invoiceService.findByInvoiceId(invoiceId);
	}
	@GetMapping
	public List<InvoiceResponse> getAllInvoices() {
		return invoiceService.getAllInvoices();
	}
	@GetMapping("/order/{orderId}")
	public InvoiceResponse findInvoiceByOrderId(@PathVariable Long orderId) {
		return invoiceService.findInvoiceByOrderId(orderId);
	}
	@PutMapping("/{invoiceId}")
	public InvoiceResponse updateInvoice(@PathVariable Long invoiceId,@RequestBody @Valid InvoiceRequest invoiceRequest) {
		return invoiceService.updateInvoice(invoiceId, invoiceRequest);
	}
	@DeleteMapping("/{invoiceId}")
	public ResponseEntity<Void> deleteInvoice(@PathVariable Long invoiceId) {
		invoiceService.deleteInvoice(invoiceId);
		return ResponseEntity.noContent().build();
	}
}
