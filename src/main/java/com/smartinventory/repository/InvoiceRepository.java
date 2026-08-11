package com.smartinventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartinventory.entity.Invoice;


@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long>{
	Optional<Invoice> findByOrderOrderId(Long orderId);
}
