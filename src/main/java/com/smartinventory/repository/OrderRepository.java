package com.smartinventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartinventory.entity.Order;
import com.smartinventory.enums.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{
	
	List<Order> findOrderByStatus(OrderStatus status);
	List<Order> findByCustomerCustomerName(String customerName);
	List<Order> findByUserUserName(String userName);
	List<Order> findByCustomerCustomerId(Long customerId);

	List<Order> findByUserUserId(Long userId);
}
