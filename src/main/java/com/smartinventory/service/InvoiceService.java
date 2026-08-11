package com.smartinventory.service;

import java.util.List;

import com.smartinventory.dto.InvoiceRequest;
import com.smartinventory.dto.InvoiceResponse;

public interface InvoiceService {
	public InvoiceResponse createInvoice(InvoiceRequest invoiceRequest);
	public InvoiceResponse findByInvoiceId(Long invoiceId);
	public List<InvoiceResponse> getAllInvoices();
	public InvoiceResponse updateInvoice(Long invoiceId,InvoiceRequest invoiceRequest);
	public void deleteInvoice(Long invoiceId);
	public InvoiceResponse findInvoiceByOrderId(Long orderId);
}
