package com.smartinventory.service;

import java.util.List;

import com.smartinventory.dto.CustomerRequest;
import com.smartinventory.dto.CustomerResponse;

public interface CustomerService {
	CustomerResponse createCustomer(CustomerRequest customerRequest);
	CustomerResponse findCustomerByCustomerId(Long customerId);
	CustomerResponse findCustomerByCustomerName(String customerName);
	List<CustomerResponse> getAllCustomers();
	CustomerResponse updateCustomer(Long customerId,CustomerRequest customerRequest);
	void deleteCustomer(Long customerId);
}
