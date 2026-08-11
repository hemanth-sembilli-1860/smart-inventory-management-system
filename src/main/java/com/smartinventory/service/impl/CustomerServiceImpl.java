package com.smartinventory.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartinventory.dto.CustomerRequest;
import com.smartinventory.dto.CustomerResponse;
import com.smartinventory.entity.Customer;
import com.smartinventory.exceptions.CustomerNotFoundException;
import com.smartinventory.repository.CustomerRepository;
import com.smartinventory.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{
	private CustomerRepository customerRepository;
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository=customerRepository;
	}
	@Override
	public CustomerResponse createCustomer(CustomerRequest customerRequest) {
		Customer customer = new Customer();
		customer.setCustomerName(customerRequest.getCustomerName());
		customer.setEmailId(customerRequest.getEmailId());
		customer.setPhone(customerRequest.getPhone());
		customer.setAddress(customerRequest.getAddress());
		Customer savedEntity = customerRepository.save(customer);
		return convertToCustomerResponse(savedEntity);
	}
	private CustomerResponse convertToCustomerResponse(Customer customer) {
		CustomerResponse customerResponse = new CustomerResponse();
		customerResponse.setCustomerId(customer.getCustomerId());
		customerResponse.setCustomerName(customer.getCustomerName());
		customerResponse.setEmailId(customer.getEmailId());
		customerResponse.setPhone(customer.getPhone());
		customerResponse.setAddress(customer.getAddress());
		return customerResponse;
	}
	@Override
	public CustomerResponse findCustomerByCustomerId(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
		return convertToCustomerResponse(customer);
	}
	@Override
	public CustomerResponse findCustomerByCustomerName(String customerName) {
		Customer customer = customerRepository.findByCustomerName(customerName)
				.orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
		return convertToCustomerResponse(customer);
	}
	@Override
	public List<CustomerResponse> getAllCustomers() {
		return customerRepository.findAll()
				.stream()
				.map(this::convertToCustomerResponse)
				.toList();
	}
	@Override
	public CustomerResponse updateCustomer(Long customerId, CustomerRequest customerRequest) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
		customer.setCustomerName(customerRequest.getCustomerName());
		customer.setEmailId(customerRequest.getEmailId());
		customer.setPhone(customerRequest.getPhone());
		customer.setAddress(customerRequest.getAddress());
		Customer savedEntity = customerRepository.save(customer);
		return convertToCustomerResponse(savedEntity);
		
	}
	@Override
	public void deleteCustomer(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
		customerRepository.delete(customer);
		log.info("Customer {} is deleted ",customer.getCustomerName());
	}
	
}
