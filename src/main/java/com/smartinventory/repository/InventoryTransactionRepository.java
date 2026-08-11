package com.smartinventory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartinventory.entity.InventoryTransaction;
import com.smartinventory.enums.PaymentMethod;
import com.smartinventory.enums.PaymentStatus;

@Repository
public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction,Long>{
	List<InventoryTransaction> findByPaymentStatus(PaymentStatus paymentStatus);
	List<InventoryTransaction> findByPaymentMethod(PaymentMethod paymentMethod);
	List<InventoryTransaction> findByOrderOrderId(Long orderId);
	Optional<InventoryTransaction> findByTransactionReference(String transactionReference);
	
}
