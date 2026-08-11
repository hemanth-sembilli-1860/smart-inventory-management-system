package com.smartinventory.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartinventory.dto.CustomerOrderResponse;
import com.smartinventory.dto.InvoiceRequest;
import com.smartinventory.dto.InvoiceResponse;
import com.smartinventory.dto.OrderResponse;
import com.smartinventory.dto.UserOrderResponse;
import com.smartinventory.entity.Invoice;
import com.smartinventory.entity.Order;
import com.smartinventory.exceptions.InvoiceNotFoundException;
import com.smartinventory.exceptions.OrderNotFoundException;
import com.smartinventory.repository.InvoiceRepository;
import com.smartinventory.repository.OrderRepository;
import com.smartinventory.service.InvoiceService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService{
	private final OrderRepository orderRepository;
	private final InvoiceRepository invoiceRepository;
	public InvoiceServiceImpl(InvoiceRepository invoiceRepository, OrderRepository orderRepository) {
		this.invoiceRepository = invoiceRepository;
		this.orderRepository = orderRepository;
	}
	@Override
	public InvoiceResponse createInvoice(InvoiceRequest invoiceRequest) {
		Invoice invoice = new Invoice();
		invoice.setInvoiceNumber(invoiceRequest.getInvoiceNumber());
		Order order = orderRepository.findById(invoiceRequest.getOrderId())
				.orElseThrow(()->new OrderNotFoundException("Order Not Found"));
		invoice.setOrder(order);
		Invoice savedInvoice = invoiceRepository.save(invoice);
		return convertToInvoiceResponse(savedInvoice);
	}
	private OrderResponse convertToOrderResponse(Order order) {
		CustomerOrderResponse customerOrderResponse = new CustomerOrderResponse();
		UserOrderResponse userOrderResponse = new UserOrderResponse();
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setOrderId(order.getOrderId());
		orderResponse.setStatus(order.getStatus());
		orderResponse.setTotalAmount(order.getTotalAmount());
		customerOrderResponse.setCustomerId(order.getCustomer().getCustomerId());
		customerOrderResponse.setCustomerName(order.getCustomer().getCustomerName());
		customerOrderResponse.setAddress(order.getCustomer().getAddress());
		customerOrderResponse.setEmailId(order.getCustomer().getEmailId());
		customerOrderResponse.setPhone(order.getCustomer().getPhone());
		orderResponse.setCustomerOrderResponse(customerOrderResponse);
		userOrderResponse.setUserId(order.getUser().getUserId());
		userOrderResponse.setUserName(order.getUser().getUserName());
		userOrderResponse.setRole(order.getUser().getRole());
		orderResponse.setUserOrderResponse(userOrderResponse);
		orderResponse.setCreatedAt(order.getCreatedAt());
		orderResponse.setUpdatedAt(order.getUpdatedAt());
		orderResponse.setOrderDate(order.getOrderDate());
		return orderResponse;
	}
	private InvoiceResponse convertToInvoiceResponse(Invoice invoice) {
		InvoiceResponse invoiceResponse = new InvoiceResponse();
		invoiceResponse.setInvoiceId(invoice.getInvoiceId());
		invoiceResponse.setInvoiceNumber(invoice.getInvoiceNumber());
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setOrderId(invoice.getOrder().getOrderId());
		invoiceResponse.setOrderResponse(convertToOrderResponse(invoice.getOrder()));
		return invoiceResponse;
	}
	@Override
	public InvoiceResponse findByInvoiceId(Long invoiceId) {
		Invoice invoice = invoiceRepository.findById(invoiceId)
				.orElseThrow(()->new InvoiceNotFoundException("Invoice Not Found"));
		return convertToInvoiceResponse(invoice);
	}
	@Override
	public List<InvoiceResponse> getAllInvoices() {
		return invoiceRepository.findAll()
				.stream()
				.map(this::convertToInvoiceResponse)
				.toList();
	}
	@Override
	public InvoiceResponse findInvoiceByOrderId(Long orderId) {
		Invoice invoice = invoiceRepository.findByOrderOrderId(orderId)
				.orElseThrow(()->new InvoiceNotFoundException("Invoice Not Found"));
		return convertToInvoiceResponse(invoice);
	}
	@Override
	public InvoiceResponse updateInvoice(Long invoiceId, InvoiceRequest invoiceRequest) {
		Invoice invoice = invoiceRepository.findById(invoiceId)
				.orElseThrow(()->new InvoiceNotFoundException("Invoice Not Found"));
		invoice.setInvoiceNumber(invoiceRequest.getInvoiceNumber());
		Order order = orderRepository.findById(invoiceRequest.getOrderId())
				.orElseThrow(()->new OrderNotFoundException("Order Not Found"));
		invoice.setOrder(order);
		Invoice savedInvoice = invoiceRepository.save(invoice);
		return convertToInvoiceResponse(savedInvoice);
	}
	@Override
	public void deleteInvoice(Long invoiceId) {
		Invoice invoice = invoiceRepository.findById(invoiceId)
				.orElseThrow(()->new InvoiceNotFoundException("Invoice Not Found"));
		invoiceRepository.delete(invoice);
		log.info("Invoice with Invoice Id {} is deleted",invoiceId);
	}
}
