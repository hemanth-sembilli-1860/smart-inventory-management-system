package com.smartinventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smartinventory.entity.OrderItem;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{
	List<OrderItem> findByOrderOrderId(Long orderId);
	 @Query("SELECT COALESCE(SUM(oi.subTotal), 0) FROM OrderItem oi WHERE oi.order.orderId = :orderId")
	    Double calculateOrderTotal(@Param("orderId") Long orderId);
	List<OrderItem> findByProductProductId(Long productId);
	List<OrderItem> findByProductProductName(String productName);
}
