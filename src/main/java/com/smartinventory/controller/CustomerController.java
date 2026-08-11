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

import com.smartinventory.dto.CustomerRequest;
import com.smartinventory.dto.CustomerResponse;
import com.smartinventory.service.CustomerService;

import jakarta.validation.Valid;

@RequestMapping("/customers")
@RestController
public class CustomerController {
	private CustomerService customerService;
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	@PostMapping
	public CustomerResponse createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
		return customerService.createCustomer(customerRequest);
	}

	@GetMapping("/{customerId}")
	public CustomerResponse findByCustomerId(@PathVariable Long customerId) {
		return customerService.findCustomerByCustomerId(customerId);
	}

	@GetMapping
	public List<CustomerResponse> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@PutMapping("/{customerId}")
	public CustomerResponse updateCustomer(@PathVariable Long customerId,@Valid @RequestBody CustomerRequest customerRequest) {
		return customerService.updateCustomer(customerId, customerRequest);
	}

	@DeleteMapping("/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
		customerService.deleteCustomer(customerId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/name/{customerName}")
	public CustomerResponse findByCustomerName(@PathVariable String customerName) {
		return customerService.findCustomerByCustomerName(customerName);
	}
	
}
