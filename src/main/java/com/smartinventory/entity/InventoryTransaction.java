package com.smartinventory.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.smartinventory.enums.PaymentMethod;
import com.smartinventory.enums.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class InventoryTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	@Column(name = "transaction_reference",nullable = false)
	private String transactionReference;
	@ManyToOne
	@JoinColumn(name = "order_id",nullable = false)
	private Order order;
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	private double totalamount;
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	@CreationTimestamp
	private LocalDateTime transactionTime;
}
